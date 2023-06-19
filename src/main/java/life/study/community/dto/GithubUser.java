package life.study.community.dto;

import lombok.Data;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/6/7 13:29
 */
@Data
public class GithubUser {
    private String name;
    private String id;
    private String email;
    private String bio;
    private String avatar_url;
}
