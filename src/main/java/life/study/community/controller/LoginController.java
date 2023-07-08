package life.study.community.controller;

import life.study.community.mapper.UserMapper;
import life.study.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/6/8 16:54
 */
@Controller
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/loginPage")
    public String goLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpServletResponse response) {
        User userObj = userMapper.selectUserByAccountAndPassword(account, password);
        System.out.println(account + "  " + password);
        if (userObj != null && userObj.getAccount() != null) {
            // 每次登录后更新用户token
            // 使用UUID生成token
            String token = UUID.randomUUID().toString();
            userObj.setToken(token);
            userMapper.updateUserToken(userObj);
            // 写入cookie到浏览器
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            cookie.setMaxAge(8*60*60+10);
            response.addCookie(cookie);
        } else {
            System.out.println("用户不存在！");
        }
        // 重定向回首页
        return "redirect:/";
    }
}
