package life.study.community.exception;

/**
 * 状态码
 *
 * @author 木南
 * @version 1.0
 * @date 2023/7/17 15:59
 */
public enum StatusCode implements CustomizeStatus{
    /**
     * 帖子不存在
     */
    TOPIC_NOT_FOUND("帖子不存在或已被删除！");

    private String message;

    StatusCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessages() {
        return message;
    }
}
