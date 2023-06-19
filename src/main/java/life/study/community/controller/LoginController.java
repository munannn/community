package life.study.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/6/8 16:54
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "signin";
    }
}
