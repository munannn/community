package life.study.community.controller;

import life.study.community.dto.PaginationDTO;
import life.study.community.dto.TopicDTO;
import life.study.community.model.User;
import life.study.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/7/7 15:08
 */
@Controller
public class ProfileController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {

        User user = (User) request.getSession().getAttribute("user");

        switch (action) {
            case "myTopics":
                model.addAttribute("section", "myTopics");
                model.addAttribute("sectionName", "我的帖子");
                break;
            case "latestReplies":
                model.addAttribute("section", "latestReplies");
                model.addAttribute("sectionName", "最新回复");
                break;
            default:
                break;
        }
        PaginationDTO<TopicDTO> pageData = topicService.listById(user.getId(), page, size);
        model.addAttribute("pageData", pageData);
        return "profile";
    }
}
