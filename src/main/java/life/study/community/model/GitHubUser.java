package life.study.community.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/6/8 11:35
 */
@Data
public class GitHubUser {
    private Integer id;
    private String accountId;
    private String name;
    private String email;
    private String token;
    private Date createTime;
    private Date modifiedTime;
    private Integer role;
}
