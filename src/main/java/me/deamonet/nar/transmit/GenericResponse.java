package me.deamonet.nar.transmit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.deamonet.nar.constant.ResponseAttribute;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
    private int code;
    private String message;
    private Date timestamp;
    private T data;

    /**
     * if data is a boolean object
     *
     * @param data
     * @param <T>
     * @return GenericResponse<Boolean>
     */
    public static <T> GenericResponse<Boolean> builder(boolean data) {
        if (data) {
            return success(Boolean.TRUE);
        } else {
            return failed();
        }
    }

    /**
     * 成功，没有数据
     * @return
     * @param <T>
     */
    public static <T> GenericResponse<T> success() {
        return new GenericResponse<T>(ResponseAttribute.SUCCESS.getCode(), ResponseAttribute.SUCCESS.getMessage(), new Date(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse<T>(ResponseAttribute.SUCCESS.getCode(), ResponseAttribute.SUCCESS.getMessage(), new Date(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> GenericResponse<T> success(T data, String message) {
        return new GenericResponse<T>(ResponseAttribute.SUCCESS.getCode(), message, new Date(), data);
    }
    /**
     * 无说明失败返回结果
     */
    public static <T> GenericResponse<T> failed() {
        return failed(ResponseAttribute.FAILED);
    }
    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> GenericResponse<T> failed(ResponseAttribute errorCode) {
        return new GenericResponse<T>(errorCode.getCode(), errorCode.getMessage(), new Date(), null);
    }

    /**
     * 失败返回结果，自定义消息
     *
     * @param message 提示信息
     */
    public static <T> GenericResponse<T> failed(String message) {
        return new GenericResponse<T>(ResponseAttribute.FAILED.getCode(), message, new Date(), null);
    }

    /**
     * 失败，自定义失败状态码和消息
     * @param code
     * @param message
     * @return
     * @param <T>
     */
    public static <T> GenericResponse<T> failed(int code, String message) {
        return new GenericResponse<>(code, message, new Date(), null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> GenericResponse<T> validateFailed() {
        return failed(ResponseAttribute.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> GenericResponse<T> validateFailed(String message) {
        return new GenericResponse<T>(ResponseAttribute.VALIDATE_FAILED.getCode(), message, new Date(), null);
    }
    /**
     * 未登录返回结果
     */
    public static <T> GenericResponse<T> unauthorized() {
        return new GenericResponse<T>(ResponseAttribute.UNAUTHORIZED.getCode(), ResponseAttribute.UNAUTHORIZED.getMessage(), new Date(), null);
    }
    /**
     * 未登录返回结果
     */
    public static <T> GenericResponse<T> unauthorized(T data) {
        return new GenericResponse<T>(ResponseAttribute.UNAUTHORIZED.getCode(), ResponseAttribute.UNAUTHORIZED.getMessage(), new Date(), data);
    }

    /**
     * 未知用户名
     */
    public static <T> GenericResponse<T> usernameNotFound(String username) {
        return failed("username not found:  " + username);
    }

    /**
     * 未授权返回结果
     */
    public static <T> GenericResponse<T> forbidden(T data) {
        return new GenericResponse<T>(ResponseAttribute.FORBIDDEN.getCode(), ResponseAttribute.FORBIDDEN.getMessage(), new Date(), data);
    }


    public static <T> GenericResponse<T> missingArguments(){
        return new GenericResponse<T>(ResponseAttribute.MISSING_ARGUMENTS.getCode(), ResponseAttribute.MISSING_ARGUMENTS.getMessage(), new Date(), null);
    }
}
