package life.study.community.controller;

import life.study.community.mapper.InvitationMapper;
import life.study.community.mapper.UserMapper;
import life.study.community.model.Invitation;
import life.study.community.model.User;
import life.study.community.util.InvitationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 邀请模块
 *
 * @author 木南
 * @version 1.0
 * @date 2023/6/19 15:28
 */
@Controller
public class InviteController {
    @Resource
    private JavaMailSender javaMailSender;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InvitationMapper invitationMapper;

    /**
     * 邮件的发送方，即邀请人
     */
    private String from;

    @RequestMapping("/invitePage")
    public String invitePage() {
        return "inviteUser";
    }

    @PostMapping("/invite")
    public String doInvite(@RequestParam("invitee") String invitee,
                         @RequestParam("email") String email,
                         @RequestParam("role") Integer role,
                         HttpServletRequest request) {
        // 获取cookie，通过cookie得到当前登录用户
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                User user = userMapper.selectUserByToken(token);
                if (user != null) {
                    from = user.getAccount();
                }
                break;
            }
        }
        // 发送邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 邮件发送方
        mailMessage.setFrom(from);
        // 生成邀请码
        String invitationCode = InvitationCodeGenerator.generateInvitationCode();
        // 邮件接收方
        mailMessage.setTo(email);
        // 邮件标题
        mailMessage.setSubject("邀请加入");
        // 邮件内容
        String content = "亲爱的 " + invitee + ", 用户：" + from + " 邀请你加入灯塔，注册邀请码为：" + invitationCode + "，请使用你的QQ邮箱赶快加入吧！";
        mailMessage.setText(content);
        javaMailSender.send(mailMessage);

        // 插入邀请记录
        Invitation invitation = new Invitation();
        invitation.setInviter(from);
        invitation.setInvitee(email);
        invitation.setRole(role);
        invitation.setInviteCode(invitationCode);
        invitation.setIsUse(0);
        Date date = new Date();
        invitation.setInviteTime(date);
        invitationMapper.addInvitationRecord(invitation);

        System.out.println("邀请成功！");

        return "redirect:/";
    }
}
