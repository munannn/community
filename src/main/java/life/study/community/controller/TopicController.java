package life.study.community.controller;

import life.study.community.dto.TopicDTO;
import life.study.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/7/8 13:28
 */
@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/topics/{id}")
    public String topic(@PathVariable(value = "id") Integer id,
                        Model model) {
        TopicDTO topicDTO = topicService.selectById(id);
        model.addAttribute("topicDTO", topicDTO);
        return "topic";
    }
}
