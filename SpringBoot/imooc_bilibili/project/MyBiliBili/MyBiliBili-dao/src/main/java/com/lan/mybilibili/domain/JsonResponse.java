package com.lan.mybilibili.domain;

/**
 * 共同返回json数据
 * @param <T>
 */
public class JsonResponse<T> {

    /**
     * 状态码
     */
    private String code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public JsonResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResponse(T data) {
        this.data = data;
        this.code = "0";
        this.msg = "成功";
    }

    /**
     * 不需要返回信息的成功
     * @return
     */
    public static JsonResponse<String> success() {
        return new JsonResponse<String>(null);
    }

    /**
     * 需要返回信息（字符串）的成功
     * 比如令牌
     * @return
     */
    public static JsonResponse<String> success(String data) {
        return new JsonResponse<String>(data);
    }

    /**
     * 失败
     * @return
     */
    public static JsonResponse<String> fail() {
        return new JsonResponse<>("1", "失败");
    }

    /**
     * 失败
     * @param code
     * @param msg
     * @return
     */
    public static JsonResponse<String> fail(String code, String msg) {
        return new JsonResponse<>(code, msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
