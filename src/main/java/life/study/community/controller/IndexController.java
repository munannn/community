package life.study.community.controller;

import life.study.community.mapper.GitHubUserMapper;
import life.study.community.model.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/5/31 17:12
 */
@Controller
public class IndexController {

    @Autowired
    private GitHubUserMapper gitHubUserMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    GitHubUser gitHubUser = gitHubUserMapper.selectUserByToken(token);
                    if (gitHubUser != null) {
                        request.getSession().setAttribute("gitHubUser", gitHubUser);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}
