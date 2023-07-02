package life.study.community.mapper;

import life.study.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @Mapper注解修饰的接口会被MyBatis框架扫描并生成代理对象，这个代理对象会被Spring容器管理，因此会创建实例到IOC容器中。
 * @author 木南
 * @version 1.0
 * @date 2023/6/19 14:13
 */
@Mapper
public interface UserMapper {

    /**
     * 根据账号查用户
     * @param account
     * @return
     */
    @Select("select * from `user` where account = #{account}")
    User selectUserByAccount(String account);

    /**
     * 根据账号密码查询用户
     * @param account
     * @param password
     * @return
     */
    @Select("select * from `user` where account = #{account} and password = #{password} and is_deleted = 0")
    User selectUserByAccountAndPassword(String account, String password);

    /**
     * 更新用户的token
     * @param user
     * @return
     */
    @Update("update `user` set token = #{token} where account = #{account} and is_deleted = 0")
    int updateUserToken(User user);

    /**
     * 根据token查用户
     * @param token
     * @return
     */
    @Select("select * from `user` where token = #{token} and is_deleted = 0")
    User selectUserByToken(String token);

    /**
     * 插入一条用户记录
     * @param user
     * @return
     */
    @Insert("insert into `user` (`account`,`password`,`role`,`token`,`create_by`,`create_time`,`modified_time`," +
            "`is_deleted`,`deleted_time`) values (#{account},#{password},#{role},#{token},#{createBy},#{createTime}," +
            "#{modifiedTime},#{isDeleted},#{deletedTime})")
    int insertUser(User user);

    /**
     * 删除用户的token
     * @param token
     * @return
     */
    @Update("update `user` set `token` = '' where `token` = #{token}")
    int clearUserToken(String token);
}
