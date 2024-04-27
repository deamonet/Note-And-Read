package me.deamonet.nar.constant;


/**
 * 枚举了一些常用API操作码
 * Created by macro on 2019/4/19.
 */
public enum ResponseAttribute {
    SUCCESS(200, "操作成功"),
    FAILED(500, "服务内部错误"),
    VALIDATE_FAILED(404, "参数检验失败"),
    MISSING_ARGUMENTS(400, "缺少必填参数"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private final int code;
    private final String message;

    ResponseAttribute(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}