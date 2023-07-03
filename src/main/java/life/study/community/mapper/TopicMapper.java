package life.study.community.mapper;

import life.study.community.model.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @author 木南
 * @version 1.0
 * @date 2023/6/20 17:41
 */
@Mapper
public interface TopicMapper{

    /**
     * 插入一条帖子
     * @param topic
     * @return
     */
    @Insert("insert into `topic` (`title`,`content`,`publish_by`,`create_time`,`modified_time`,`tag`) values " +
            "(#{title},#{content},#{publishBy},#{createTime},#{modifiedTime},#{tag})")
    int insertTopic(Topic topic);

    /**
     * 获取帖子列表
     * @return
     */
    @Select("select * from `topic`")
    List<Topic> getTopicList();

    /**
     * 得到topic表的总记录数
     * @return
     */
    @Select("select count(*) from `topic`")
    Integer getCount();

    /**
     * 分页查询
     * @param offset
     * @param size
     * @return
     */
    @Select("select * from `topic` limit #{offset},#{size}")
    List<Topic> page(Integer offset, Integer size);
}
