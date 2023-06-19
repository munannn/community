package life.study.community.util;

import java.util.Random;

/**
 * 生成6位邀请码的工具类
 * @author 木南
 * @version 1.0
 * @date 2023/6/19 16:54
 */
public class InvitationCodeGenerator {
    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateInvitationCode() {
        Random random = new Random();
        int length = 6;
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }
        return codeBuilder.toString();
    }
}
