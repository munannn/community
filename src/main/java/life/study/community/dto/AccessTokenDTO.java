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
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectId;
}
