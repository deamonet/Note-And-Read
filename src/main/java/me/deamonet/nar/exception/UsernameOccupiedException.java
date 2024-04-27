package me.deamonet.nar.exception;


import lombok.Getter;

/**
 * 用户名被占用
 */
@Getter
public class UsernameOccupiedException extends RuntimeException {
    private int code;
    private String message;

    public UsernameOccupiedException(){
        this.code = 400;
        this.message = "用户名已被占用";
    }
}
