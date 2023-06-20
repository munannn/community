package life.study.community.model;

import lombok.Data;

import java.util.Date;

/**
 * 帖子表对应的实体类
 * @author 木南
 * @version 1.0
 * @date 2023/6/20 17:53
 */
@Data
public class Topic {
    private Integer id;
    private String title;
    private String content;
    private Integer publishBy;
    private Date createTime;
    private Date modifiedTime;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private String tag;
}
