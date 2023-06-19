package life.study.community.model;

import lombok.Data;

import java.util.Date;

/**
 * user表对应的实体类
 *
 * @author 木南
 * @version 1.0
 * @date 2023/6/19 14:05
 */
@Data
public class User {
    private Integer id;
    private String account;
    private String password;
    private Integer role;
    private String token;
    private String createBy;
    private Date createTime;
    private Date modifiedTime;
    private Integer isDeleted;
    private Date deletedTime;
}
