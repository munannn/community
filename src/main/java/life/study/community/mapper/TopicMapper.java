package life.study.community.mapper;

import life.study.community.model.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author 木南
 * @version 1.0
 * @date 2023/6/20 17:41
 */
@Mapper
public interface TopicMapper {

    /**
     * 插入一条帖子
     * @param topic
     * @return
     */
    @Insert("insert into `topic` (`title`,`content`,`publish_by`,`create_time`,`modified_time`,`tag`) values " +
            "(#{title},#{content},#{publishBy},#{createTime},#{modifiedTime},#{tag})")
    int insertTopic(Topic topic);
}
