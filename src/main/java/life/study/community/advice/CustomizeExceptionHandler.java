package life.study.community.advice;

import life.study.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/7/11 16:29
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        if(e instanceof CustomizeException) {
            model.addAttribute("message", e.getMessage());
        }else {
            model.addAttribute("message", "服务器冒烟了！！！请稍后再试^_^");
        }
        return new ModelAndView("error");
    }
}
