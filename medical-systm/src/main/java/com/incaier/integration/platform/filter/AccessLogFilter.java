package com.incaier.integration.platform.filter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.incaier.integration.platform.constant.CommonConstant;
import com.incaier.integration.platform.entity.RequestLog;
import com.incaier.integration.platform.entity.ResponseLog;
import com.incaier.integration.platform.exception.PayloadTooLargeException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 请求响应日志拦截打印
 *
 * @author caiweijie
 * @date 2024/06/25
 */
@Component
@WebFilter(filterName = "accessLogFilter", urlPatterns = "/*")
@Order(-9999)
public class AccessLogFilter extends HttpFilter {

	private static final Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);

	private static final long serialVersionUID = 2350819720019396278L;

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 限制30个字节
        ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper(req) {
            @Override
            protected void handleContentOverflow(int contentCacheLimit) {
                throw new PayloadTooLargeException(contentCacheLimit);
            }
        };
        ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper(res);
        long start = System.currentTimeMillis();
		// 执行请求链
		super.doFilter(cachingRequestWrapper, cachingResponseWrapper, chain);
        String requestId = UUID.randomUUID().toString();
        // 生成唯一的请求ID
        cachingResponseWrapper.setHeader("x-request-id", requestId);
        String queryParam = req.getQueryString(), queryBody = new String(cachingRequestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);;
        JsonObject jsonObject = new JsonObject();
        if (StringUtils.isNotEmpty(queryBody)) {
            jsonObject = JsonParser.parseString(queryBody).getAsJsonObject();
        }
        String contentType = req.getContentType();
        if (StringUtils.isNotBlank(contentType) && contentType.contains("multipart/form-data")) {
            queryParam = "文件上传";
            jsonObject.addProperty("contentType", contentType);
        }
        RequestLog request = RequestLog.builder()
                .requestId(requestId)
                .method(req.getMethod())
                .uri(req.getRequestURI())
                .ip(req.getRemoteAddr())
                .token(req.getHeader(CommonConstant.ACCESS_TOKEN))
                .queryParam(queryParam)
                .queryBody(jsonObject)
                .build();
        logger.info("request: {}", GSON.toJson(request));
		long end = System.currentTimeMillis();
        ResponseLog response = ResponseLog.builder()
                .requestId(requestId)
                .status(cachingResponseWrapper.getStatus())
                .ms(end - start)
//                .responseBody(new String(cachingResponseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8))
                .build();
        logger.info("response: {}", GSON.toJson(response));
        // 将缓存的响应内容，输出到客户端
		cachingResponseWrapper.copyBodyToResponse();
	}
}

