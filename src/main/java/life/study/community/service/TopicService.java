package life.study.community.service;

import life.study.community.dto.PaginationDTO;
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


    public PaginationDTO<TopicDTO> list(Integer page, Integer size) {
        PaginationDTO<TopicDTO> paginationDTO = new PaginationDTO<>();
        Integer totalCount = topicMapper.getCount();
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }

        // 分页偏移量
        Integer offset = (page - 1) * size;
        // 分页数据
        List<Topic> topicList = topicMapper.page(offset, size);
        List<TopicDTO> topicDTOList = new ArrayList<>();

        for (Topic topic : topicList) {
            User user = userMapper.selectUserById(topic.getPublishBy());
            TopicDTO topicDTO = new TopicDTO();
            BeanUtils.copyProperties(topic, topicDTO);
            topicDTO.setUser(user);
            topicDTOList.add(topicDTO);
        }
        paginationDTO.setData(topicDTOList);
        return paginationDTO;
    }

    /**
     * 所有帖子，包含帖子内容与用户头像
     * 关联UserMapper与TopicMapper转化为目标对象TopicDTO
     *
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
