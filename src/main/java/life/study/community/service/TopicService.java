package life.study.community.service;

import life.study.community.dto.TopicDTO;
import life.study.community.mapper.TopicMapper;
import life.study.community.mapper.UserMapper;
import life.study.community.model.Topic;
import life.study.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/7/2 16:53
 */
@Service
public class TopicService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TopicMapper topicMapper;

    /**
     * 页面展示帖子，包含帖子内容与用户头像
     * 关联UserMapper与TopicMapper转化为目标对象TopicDTO
     * @return
     */
    public List<TopicDTO> getTopicList() {
        List<TopicDTO> topicDTOList = new ArrayList<>();
        List<Topic> topicList = topicMapper.getTopicList();
        //遍历topicList，查找对应的User，转换成DTO
        for (Topic topic : topicList) {
            User user = userMapper.selectUserById(topic.getPublishBy());
            TopicDTO topicDTO = new TopicDTO();
            //使用BeanUtils将topic中的属性复制到topicDTO中
            BeanUtils.copyProperties(topic, topicDTO);
            topicDTO.setUser(user);
            topicDTOList.add(topicDTO);
        }
        return topicDTOList;
    }
}
