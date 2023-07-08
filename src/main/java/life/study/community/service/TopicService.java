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
     *
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO<TopicDTO> list(Integer page, Integer size) {
        PaginationDTO<TopicDTO> paginationDTO = new PaginationDTO<>();
        Integer totalCount = topicMapper.getCount();
        if (totalCount < 1) {
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
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO<TopicDTO> listById(Integer userId, Integer page, Integer size) {
        PaginationDTO<TopicDTO> paginationDTO = new PaginationDTO<>();
        Integer totalCount = topicMapper.getCountById(userId);
        if (totalCount < 1) {
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
        List<Topic> topicList = topicMapper.pageById(userId, offset, size);
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
     * 根据id查询返回对应TopicDTO
     *
     * @param id
     * @return
     */
    public TopicDTO selectById(Integer id) {
        Topic topic = topicMapper.selectById(id);
        // 将Topic对象转换成TopicDTO对象
        TopicDTO topicDTO = new TopicDTO();
        BeanUtils.copyProperties(topic, topicDTO);
        User user = userMapper.selectUserById(topic.getPublishBy());
        topicDTO.setUser(user);
        return topicDTO;
    }

    /**
     * 根据用户id与帖子发布者，验证该用户是否可以修改该帖子
     *
     * @param userId
     * @param publishBy
     * @return
     */
    public boolean checkTopicById(Integer userId, Integer publishBy) {
        return userId.equals(publishBy);
    }

    /**
     * 修改帖子
     *
     * @param id
     * @param topic
     */
    public void updateTopicById(Topic topic,Integer id) {
        topicMapper.updateTopicById(topic, id);
    }

    /**
     * 插入一条帖子记录
     *
     * @param topic
     */
    public void insertTopic(Topic topic) {
        topicMapper.insertTopic(topic);
    }
}
