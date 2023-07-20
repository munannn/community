package life.study.community.exception;

/**
 * 自定义异常
 *
 * @author 木南
 * @version 1.0
 * @date 2023/7/17 15:30
 */
public class CustomizeException extends RuntimeException {
    private final String message;

    public CustomizeException(CustomizeStatus status) {
        this.message = status.getMessages();
    }

    @Override
    public String getMessage() {
        return message;
    }
}

