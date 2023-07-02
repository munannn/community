package life.study.community.controller;

import life.study.community.dto.AccessTokenDTO;
import life.study.community.dto.GithubUser;
import life.study.community.mapper.GitHubUserMapper;
import life.study.community.model.GitHubUser;
import life.study.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

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
    @Autowired
    private GitHubUserMapper gitHubUserMapper;


    @GetMapping("/callback")
    public String callbacks(@RequestParam(name = "code") String code, HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClientId(clientId);
        accessTokenDTO.setClientSecret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirectId(redirectId);
        // 调用API得到github传来的token
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        // 通过携带该token，调用API登录并获取用户信息
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null && githubUser.getId() != null) {
            // 当是新的github账号登录时转换为User对象保存到数据库
            if (gitHubUserMapper.selectUserByAccountId(githubUser.getId()) == null) {
                GitHubUser gitHubUser = new GitHubUser();
                gitHubUser.setAccountId(githubUser.getId());
                gitHubUser.setName(githubUser.getName());
                gitHubUser.setEmail(githubUser.getEmail());
                // 使用UUID生成token
                String token = UUID.randomUUID().toString();
                gitHubUser.setToken(token);
                Date date = new Date();
                gitHubUser.setCreateTime(date);
                gitHubUser.setModifiedTime(date);
                gitHubUser.setRole(0);
                gitHubUserMapper.addUser(gitHubUser);
            }
            // 写入cookie到浏览器
            GitHubUser gitHubUser = gitHubUserMapper.selectUserByAccountId(githubUser.getId());
            Cookie cookie = new Cookie("token", gitHubUser.getToken());
            cookie.setPath("/");
            response.addCookie(cookie);
            // 重定向回首页
        } else {
            // 登录失败https://
        }
        return "redirect:/";
    }
}
