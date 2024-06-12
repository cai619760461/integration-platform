package com.incaier.integration.platform.config;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.incaier.integration.platform.entity.SysUser;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * 用户信息
 *
 * @author caiweijie
 * @date 2024/06/12
 */
@Component
public class UserHolder {

    public static InheritableThreadLocal<SysUser> userInfoBoThreadLocal = new InheritableThreadLocal<>();

    public static void setUserInfo(SysUser userInfo) {
        userInfoBoThreadLocal.remove();
        userInfoBoThreadLocal.set(userInfo);
    }

    public static SysUser getUserInfo() {
        SysUser sysUser = userInfoBoThreadLocal.get();
        if (Objects.isNull(sysUser)) {
            SaSession saSession = StpUtil.getSessionByLoginId(StpUtil.getLoginId());
            Map<String, Object> dataMap = saSession.getDataMap();
            sysUser = JSON.toJavaObject(dataMap.get("userInfo"), SysUser.class);
        }
        return sysUser;
    }

    public static Long getUserId() {
        if (Objects.isNull(getUserInfo())) {
            return null;
        }
        return getUserInfo().getId();
    }

    public static String getUserName() {
        if (Objects.isNull(getUserInfo())) {
            return null;
        }
        return getUserInfo().getUserName();
    }

    public static void remove() {
        userInfoBoThreadLocal.remove();
    }
}
