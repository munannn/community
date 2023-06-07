package life.study.community.controller;

import life.study.community.dto.AccessTokenDTO;
import life.study.community.dto.GithubUser;
import life.study.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用于接收调用githubAPI(https://github.com/login/oauth/authorize)回传的code
 * 并携带参数再次调用githubAPI(https://github.com/login/oauth/access_token)验证登录
 *
 * @author 木南
 * @version 1.0
 * @date 2023/6/7 12:26
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.id}")
    private String redirectId;


    @GetMapping("/callback")
    public String callbacks(@RequestParam(name = "code") String code) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_id(redirectId);
        // 得到token
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        // 通过携带token，调用API登录并获取用户信息
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user);
        return "index";
    }
}
