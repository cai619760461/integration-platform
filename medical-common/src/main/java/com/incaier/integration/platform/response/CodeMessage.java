package com.incaier.integration.platform.response;

/**
 * @Author Wangm
 */
public enum CodeMessage {
    SYSTEM_SUCCESS(200, "操作成功"),
    SYSTEM_CREATED(201, "对象创建成功"),
    SYSTEM_ACCEPTED(202, "请求已经被接受"),
    SYSTEM_NO_CONTENT(204, "操作已经执行成功，但是没有返回数据"),
    SYSTEM_MOVED_PERM(301, "资源已被移除"),
    SYSTEM_SEE_OTHER(303, "重定向"),
    SYSTEM_NOT_MODIFIED(304, "资源没有被修改"),
    SYSTEM_PARAMS_ERROR(400, "参数有误"),
    SYSTEM_UNAUTHORIZED(401, "未授权"),
    SYSTEM_FORBIDDEN(403, "访问受限或授权过期"),
    SYSTEM_NOTFOUND(404, "找不到资源"),
    SYSTEM_BAD_METHOD(405, "不允许的http方法"),
    SYSTEM_CONFLICT(409, "资源冲突或资源被锁"),
    SYSTEM_UNSUPPORTED_TYPE(415, "不支持的数据，媒体类型"),
    SYSTEM_ERROR(500, "系统异常"),
    SYSTEM_NOT_IMPLEMENTED(501, "接口未实现"),
    LONGDONG_SUCCESS(0, "操作成功");        //琅东接口成功响应码

    private final int code;
    private final String message;

    private CodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}