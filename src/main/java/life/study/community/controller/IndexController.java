package life.study.community.controller;

import life.study.community.mapper.GitHubUserMapper;
import life.study.community.mapper.UserMapper;
import life.study.community.model.GitHubUser;
import life.study.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/5/31 17:12
 */
@Controller
public class IndexController {

    @Autowired
    private GitHubUserMapper gitHubUserMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                //验证登录状态，当浏览器中存在token即为已登录，反之未登录
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.selectUserByToken(token);
                    if(user != null) {
                        session.setAttribute("user", user);
                    }
                    break;
                }else {
                    session.setAttribute("user",null);
                }
            }
        }
        return "index";
    }
}
