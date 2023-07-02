package life.study.community.controller;

import life.study.community.mapper.InvitationMapper;
import life.study.community.mapper.UserMapper;
import life.study.community.model.Invitation;
import life.study.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/6/8 16:53
 */
@Controller
public class RegisterController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private InvitationMapper invitationMapper;

    @RequestMapping("/registerPage")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam("account") String account,
                           @RequestParam("password") String password,
                           @RequestParam("invitationCode") String invitationCode) {
        System.out.println(account + " " + password + " " + invitationCode);
        // 查看用户是否已存在
        User user = userMapper.selectUserByAccount(account);
        System.out.println("user:" + user);
        if (user == null) {
            // 通过邀请码注册
            Invitation invitee = invitationMapper.selectInvitationByInviteeAndInvitationCode(account, invitationCode);
            System.out.println("invitee:" + invitee);
            if (invitee != null && invitee.getIsUse() == 0) {
                // 插入用户记录
                User newUser = new User();
                newUser.setAccount(account);
                newUser.setPassword(password);
                newUser.setRole(invitee.getRole());
                String token = UUID.randomUUID().toString();
                newUser.setToken(token);
                newUser.setCreateBy(invitee.getInviter());
                Date date = new Date();
                newUser.setCreateTime(date);
                newUser.setModifiedTime(date);
                newUser.setIsDeleted(0);
                userMapper.insertUser(newUser);

                // 将邀请码作废
                invitationMapper.updateIsUsed(invitee.getInviter(), account, invitationCode);
                System.out.println("注册成功！");
            }else {
                System.out.println("您暂时没有注册资格，请联系管理员授权");
                return "redirect:/registerPage";
            }
        }else {
            System.out.println("用户已存在，去登录吧！");
            return "redirect:/loginPage";
        }
        return "redirect:/";
    }
}
