package life.study.community.dto;

import life.study.community.model.User;
import lombok.Data;

import java.util.Date;

/**
 * 展示帖子信息的DTO
 * @author 木南
 * @version 1.0
 * @date 2023/7/2 16:52
 */
@Data
public class TopicDTO {
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
    private User user;
}
