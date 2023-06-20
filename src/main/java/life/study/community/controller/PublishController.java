package life.study.community.controller;

import life.study.community.mapper.TopicMapper;
import life.study.community.mapper.UserMapper;
import life.study.community.model.Topic;
import life.study.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 发帖
 *
 * @author 木南
 * @version 1.0
 * @date 2023/6/20 16:36
 */
@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TopicMapper topicMapper;

    @RequestMapping("/publishPage")
    public String publishPage() {
        return "publish";
    }

    @PostMapping("/publish")
    public String publish(@RequestParam(name = "title") String title,
                          @RequestParam(name = "content") String content,
                          @RequestParam(name = "tag") String tag,
                          HttpServletRequest request,
                          Model model) {
        //用model保存帖子信息，错误时通过model回显数据
        model.addAttribute("title", title);
        model.addAttribute("content",content);
        model.addAttribute("tag",tag);

        int userId = 0;
        // 获取cookie，通过cookie得到当前登录用户
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.selectUserByToken(token);
                if (user != null) {
                    userId = user.getId();
                }
                break;
            }
        }
        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setContent(content);
        topic.setPublishBy(userId);
        Date date = new Date();
        topic.setCreateTime(date);
        topic.setModifiedTime(date);
        topic.setTag(tag);
        // 插入帖子记录
        topicMapper.insertTopic(topic);
        model.addAttribute("msg","发布成功！");
        return "publish";
    }
}