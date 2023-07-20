package life.study.community.exception;

/**
 * 自定义状态
 *
 * @author 木南
 * @version 1.0
 * @date 2023/7/17 15:58
 */
public interface CustomizeStatus {
    /**
     * 实现类重写，得到状态信息
     * @return
     */
    String getMessages();
}
