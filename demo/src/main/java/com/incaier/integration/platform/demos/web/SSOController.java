package com.incaier.integration.platform.demos.web;

import com.incaier.integration.platform.util.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 关联控制器
 *
 * @author caiweijie
 * @date 2024/05/31
 */
@Controller
public class SSOController {

    @GetMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        String cookieName = "custom_global_session_id";
        String encodeString = "UTF-8";

        String cookieVaule = CookieUtils.getCookieValue(request, cookieName, encodeString);
        if (cookieVaule == null || cookieVaule.isEmpty()) {
            System.out.println("无cookie，生成新的cookie");
            cookieVaule = UUID.randomUUID().toString();
        }
        CookieUtils.setCookie(request, response, cookieName, cookieVaule, 0, encodeString);
        return "/index.html";
    }

}
