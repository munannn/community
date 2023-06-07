package life.study.community.dto;

import lombok.Data;

/**
 * github授权的dto
 * @author 木南
 * @version 1.0
 * @date 2023/6/7 12:43
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_id;
}
