package me.deamonet.nar.advice;

import lombok.extern.slf4j.Slf4j;
import me.deamonet.nar.exception.UserNotFoundException;
import me.deamonet.nar.exception.UsernameOccupiedException;
import me.deamonet.nar.transmit.GenericResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * Controller 统一异常处理器
 */

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ControllerExceptionAdvice {
    /**
     * 全局异常处理
     */

    @ExceptionHandler(value = Exception.class)
    public GenericResponse<String> handler(Exception e) {
        return GenericResponse.failed(e.getMessage());
    }

    /**
     * 参数校验异常异常处理
     */

    @ExceptionHandler(value = ConstraintViolationException.class)
    public GenericResponse<String> handlerConstraintViolationException(Exception exception) {
        return GenericResponse.failed(exception.getMessage());
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public GenericResponse<String> handlerMethodArgumentNotValidException(Exception exception) {
        return GenericResponse.failed(exception.getMessage());
    }


    @ExceptionHandler(value = BindException.class)
    public GenericResponse<String> handlerBindException(Exception e) {
        BindException bindException = (BindException) e;
        String msg = StringUtils.collectionToCommaDelimitedString(
                bindException.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()));
        return GenericResponse.failed(msg);
    }


    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public GenericResponse<String> handlerMissingServletRequestParameterException(Exception e) {
        return GenericResponse.missingArguments();
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public GenericResponse<String> handlerHttpMessageNotReadableException(Exception e) {
        return GenericResponse.validateFailed();
    }


    @ExceptionHandler(value = UserNotFoundException.class)
    public GenericResponse<String> userNotFoundExceptionHandler(Exception e) {
        UserNotFoundException userNotFoundException = (UserNotFoundException) e;
        return GenericResponse.failed(userNotFoundException.getCode(), userNotFoundException.getMessage());
    }


    @ExceptionHandler(value = UsernameOccupiedException.class)
    public GenericResponse<String> usernameOccupiedExceptionHandler(Exception e) {
        UsernameOccupiedException exception = (UsernameOccupiedException) e;
        return GenericResponse.failed(exception.getCode(), exception.getMessage());
    }
}