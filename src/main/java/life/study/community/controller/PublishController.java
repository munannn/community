package life.study.community.controller;

import life.study.community.dto.TopicDTO;
import life.study.community.model.Topic;
import life.study.community.model.User;
import life.study.community.service.TopicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private TopicService topicService;

    @RequestMapping("/publishPage")
    public String toPublishPage() {
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String editPublish(@PathVariable(name = "id") Integer id,
                              Model model) {
        TopicDTO topicDTO = topicService.selectById(id);
        model.addAttribute("id", topicDTO.getId());
        model.addAttribute("title", topicDTO.getTitle());
        model.addAttribute("content", topicDTO.getContent());
        model.addAttribute("tag", topicDTO.getTag());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "id", required = false) Integer id,
                            @RequestParam(name = "title") String title,
                            @RequestParam(name = "content") String content,
                            @RequestParam(name = "tag") String tag,
                            HttpServletRequest request,
                            Model model) {
        //用model保存帖子信息，错误时通过model回显数据
        model.addAttribute("id", id);
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("tag", tag);

        // 校验内容
        if (title == null || "".equals(title)) {
            model.addAttribute("error", "帖子标题不能为空");
            return "publish";
        }
        if (content == null || "".equals(content)) {
            model.addAttribute("error", "帖子内容不能为空");
            return "publish";
        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        // 在session中获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");

        // 通过帖子id查找是否有该帖子，进行插入或修改操作
        TopicDTO topicDTO = null;
        if(id != null){
            topicDTO = topicService.selectById(id);
        }
        if (topicDTO != null) {
            // 验证当前用户是否有权限修改该帖子
            boolean canUpdate = topicService.checkTopicById(user.getId(), topicDTO.getPublishBy());
            if (canUpdate) {
                Topic topic = new Topic();
                topic.setTitle(title);
                topic.setContent(content);
                Date date = new Date();
                topic.setModifiedTime(date);
                topic.setTag(tag);
                topicService.updateTopicById(topic, id);
            } else {
                model.addAttribute("error", "您只能编辑您发布的帖子！");
                return "publish";
            }
        } else {
            Topic topic = new Topic();
            topic.setTitle(title);
            topic.setContent(content);
            topic.setPublishBy(user.getId());
            Date date = new Date();
            topic.setCreateTime(date);
            topic.setModifiedTime(date);
            topic.setTag(tag);
            // 插入帖子记录
            topicService.insertTopic(topic);
        }
        model.addAttribute("msg", "发布成功！");
        return "publish";
    }
}
