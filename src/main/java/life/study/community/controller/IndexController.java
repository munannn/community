package life.study.community.controller;

import life.study.community.dto.PaginationDTO;
import life.study.community.dto.TopicDTO;
import life.study.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/5/31 17:12
 */
@Controller
public class IndexController {
    @Autowired
    private TopicService topicService;

    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PaginationDTO<TopicDTO> pageData = topicService.list(page, size);
        model.addAttribute("pageData", pageData);
        return "index";
    }
}
