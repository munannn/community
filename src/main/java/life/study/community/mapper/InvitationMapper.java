package life.study.community.mapper;

import life.study.community.model.Invitation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/6/19 17:07
 */
@Mapper
public interface InvitationMapper {

    /**
     * 插入一条邀请记录
     *
     * @param invitation
     * @return
     */
    @Insert("insert into `invitation` (`inviter`,`invitee`,`role`,`invite_code`,`is_use`,`invite_time`) values " +
            "(#{inviter}," +
            "#{invitee},#{role},#{inviteCode},#{isUse},#{inviteTime})")
    int addInvitationRecord(Invitation invitation);

    /**
     * 根据被邀请人账号以及对应邀请码验证是否有注册资格
     *
     * @param invitee
     * @param invitationCode
     * @return
     */
    @Select("select * from `invitation` where `invitee` = #{invitee} and `invite_code` = #{invitationCode} and " +
            "`is_use` = 0")
    Invitation selectInvitationByInviteeAndInvitationCode(String invitee, String invitationCode);

    /**
     * 邀请码使用一次后作废，不可再次使用
     *
     * @param inviter
     * @param invitee
     * @param invitationCode
     * @return
     */
    @Update("update `invitation` set `is_use` = 1 where `inviter` = #{inviter} and `invitee` = #{invitee} and " +
            "`invite_code` = #{invitationCode}")
    int updateIsUsed(String inviter, String invitee, String invitationCode);
}
