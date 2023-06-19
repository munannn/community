package life.study.community.model;

import lombok.Data;

import java.util.Date;

/**
 * invitation表对应的实体类
 * @author 木南
 * @version 1.0
 * @date 2023/6/19 16:46
 */
@Data
public class Invitation {
    private Integer id;
    private String inviter;
    private String invitee;
    private Integer role;
    private String inviteCode;
    private Integer isUse;
    private Date inviteTime;
}
