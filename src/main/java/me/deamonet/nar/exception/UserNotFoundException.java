package me.deamonet.nar.exception;

import lombok.Getter;

/**
 * 用户找不到异常
 */
@Getter
public class UserNotFoundException extends RuntimeException {
    private int code;
    private String message;

    public UserNotFoundException() {
        this.message = "找不到该用户";
        this.code = 404;
    }
}
