package com.example.demo.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 操作结果集封装
 *
 * @author zealon
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private T data;
    private Long ts =System.currentTimeMillis();


    public static <T> Response<T> ok() {
        return buildResponse(ResponseCode.OK.getCode(), ResponseCode.OK.getMsg(), null);
    }

    public static <T> Response<T> ok(T data) {
        return buildResponse(ResponseCode.OK.getCode(), ResponseCode.OK.getMsg(), data);
    }

    public static <T> Response<T> error(ResponseCode responseCode, T data) {
        return buildResponse(responseCode.getCode(), responseCode.getMsg(), data);
    }
    public static <T> Response<T> error(ResponseCode responseCode) {
        return buildResponse(responseCode.getCode(), responseCode.getMsg(), null);
    }
    public static <T> Response<T> buildResponse(int code, String msg, T data) {
        return new Response<>(code, msg, data);
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
