package com.incaier.integration.platform.filter;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.incaier.integration.platform.config.UserHolder;
import com.incaier.integration.platform.constant.ErrorCodeConstant;
import com.incaier.integration.platform.entity.SysUser;
import com.incaier.integration.platform.response.Result;
import com.incaier.integration.platform.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 登录过滤器
 *
 * @author weijie.cai
 * @date 2024/06/12
 */
@WebFilter(filterName = "msLoginFilter", urlPatterns = "/*")
@Component
@Slf4j
public class LoginFilter implements Filter {

    private static final String ACCESS_TOKEN = "saToken";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserHolder.remove();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getServletPath();
        String token = request.getHeader(ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSONUtil.parse(Result.error(ErrorCodeConstant.TOKEN_NOT_FOUND, "请先登录！")));
            return;
        }
        String key;
        // 包含"_"的token是老版门户产生的 token
        if (token.contains("_")) {
            key = token;
        } else {
            key = "saToken:login:token:" + token;
        }
        Object loginIdByToken = RedisUtils.get(key);
        if (loginIdByToken == null) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSONUtil.parse(Result.error(ErrorCodeConstant.TOKEN_INVALID, "未获取到有效token!")));
            return;
        }

        try {
            SaSession saSession = StpUtil.getSessionByLoginId(loginIdByToken);
            Map<String, Object> dataMap = saSession.getDataMap();
            JSONObject jsonObject = JSONUtil.parseObj(dataMap.get("userInfo"));
            SysUser userInfo = jsonObject.get("user", SysUser.class);
            UserHolder.setUserInfo(userInfo);
        } catch (Exception e) {
            String message = e.getMessage();
            log.error("请求:{},{}, token 为 {}", url, "token 验证失败，错误信息为：" + message, token);
        }
        filterChain.doFilter(request, response);
    }
}
