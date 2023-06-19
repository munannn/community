package life.study.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/6/8 16:53
 */
@Controller
public class RegisterController {

    @RequestMapping("/register")
    public String register() {
        return "signup";
    }
}
