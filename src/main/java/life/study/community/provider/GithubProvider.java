package life.study.community.provider;

import com.alibaba.fastjson.JSON;
import life.study.community.dto.AccessTokenDTO;
import life.study.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 提供者，支持对第三方提供的能力
 * 用于支持github授权登录
 *
 * @author 木南
 * @version 1.0
 * @date 2023/6/7 12:41
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        // 将javaDTO转换为json
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
//            String scope = string.split("&")[1].split("=")[1];
//            String token_type = string.split("&")[2].split("=")[1];
            return token;
        } catch (IOException ignored) {
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //github新版请求方式
                .url("https://api.github.com/user")
                .header("Authorization", "token " + accessToken)
//               .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            // 通过fastjson自动将json对象转换为javaDTO
            return JSON.parseObject(string, GithubUser.class);
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
        return null;
    }
}

