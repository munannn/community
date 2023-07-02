package life.study.community.controller;

import life.study.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/6/28 17:13
 */
@Controller
public class LoginOutController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/logout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response) {
        // 获取当前用户的token
        String token = getCurrentUserToken(request);
        if (token != null) {
            // 清除用户的token
            userMapper.clearUserToken(token);
            // 从浏览器中移除cookie
            removeTokenCookie(response);
        }
        // 重定向回首页或登录页
        return "redirect:/";
    }

    private String getCurrentUserToken(HttpServletRequest request) {
        // 从请求的cookie中获取token
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void removeTokenCookie(HttpServletResponse response) {
        // 从浏览器中移除token的cookie
        Cookie cookie = new Cookie("token", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
