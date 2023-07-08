package life.study.community.mapper;

import life.study.community.model.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/6/20 17:41
 */
@Mapper
public interface TopicMapper {

    /**
     * 插入一条帖子
     *
     * @param topic
     * @return
     */
    @Insert("insert into `topic` (`title`,`content`,`publish_by`,`create_time`,`modified_time`,`tag`) values " +
            "(#{title},#{content},#{publishBy},#{createTime},#{modifiedTime},#{tag})")
    int insertTopic(Topic topic);

    /**
     * 得到topic表的总记录数
     *
     * @return
     */
    @Select("select count(*) from `topic`")
    Integer getCount();

    /**
     * 分页查询
     *
     * @param offset
     * @param size
     * @return
     */
    @Select("select * from `topic` limit #{offset},#{size}")
    List<Topic> page(Integer offset, Integer size);

    /**
     * 根据用户id查询他的帖子数
     *
     * @param userId
     * @return
     */
    @Select("select count(*) from `topic` where `publish_by` = #{id}")
    Integer getCountById(@Param(value = "id") Integer userId);

    /**
     * 根据用户id分页查询返回帖子
     *
     * @param userId
     * @param offset
     * @param size
     * @return
     */
    @Select("select * from `topic` where `publish_by` = #{id} limit #{offset},#{size}")
    List<Topic> pageById(@Param(value = "id") Integer userId, Integer offset, Integer size);
}
