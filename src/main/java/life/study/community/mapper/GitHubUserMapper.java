package life.study.community.mapper;

import life.study.community.model.GitHubUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/6/8 11:33
 */
@Mapper
public interface GitHubUserMapper {
    /**
     * 插入一条用户记录
     *
     * @param gitHubUser
     * @return
     */
    @Insert("insert into `github_user` (account_id,name,email,token,create_time,modified_time,role) values " +
            "(#{accountId},#{name}," +
            "#{email},#{token},#{createTime},#{modifiedTime},#{role})")
    int addUser(GitHubUser gitHubUser);

    /**
     * 根据token查找用户
     *
     * @param token
     * @return
     */
    @Select("select * from `github_user` where token = #{token}")
    GitHubUser selectUserByToken(String token);

    /**
     * 根据account_id查找用户
     *
     * @param accountId
     * @return
     */
    @Select("select * from `github_user` where account_id = #{accountId}")
    GitHubUser selectUserByAccountId(String accountId);
}
