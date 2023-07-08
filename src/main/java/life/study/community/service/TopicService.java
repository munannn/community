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


    /**
     * 分页查询返回帖子
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO<TopicDTO> list(Integer page, Integer size) {
        PaginationDTO<TopicDTO> paginationDTO = new PaginationDTO<>();
        Integer totalCount = topicMapper.getCount();
        if(totalCount < 1) {
            return null;
        }
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
     * 根据用户id分页查询返回帖子
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO<TopicDTO> listById(Integer userId, Integer page, Integer size) {
        PaginationDTO<TopicDTO> paginationDTO = new PaginationDTO<>();
        Integer totalCount = topicMapper.getCountById(userId);
        if(totalCount < 1) {
            return null;
        }
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
        List<Topic> topicList = topicMapper.pageById(userId,offset, size);
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
}
