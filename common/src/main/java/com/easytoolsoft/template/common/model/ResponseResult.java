package com.easytoolsoft.template.common.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

/**
 * json 结果返回对象
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Data
public class ResponseResult<T> implements Serializable {
    private String requestId = UUID.randomUUID().toString();
    private int code = 0;
    private String msg = "";
    private String detailMsg = "";
    private boolean success;
    private T data;

    public ResponseResult() {
        this(true, "");
    }

    public ResponseResult(final T data) {
        this(true, "");
        this.data = data;
    }

    public ResponseResult(final boolean success, final String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ResponseResult success(final T data) {
        this.success = true;
        this.msg = "ok";
        this.data = data;
        return this;
    }

    public ResponseResult failure(String msg, String detailMsg) {
        return this.failure(-1, msg, detailMsg);
    }

    public ResponseResult failure(int code, String msg, String detailMsg) {
        this.success = false;
        this.code = code;
        this.msg = msg;
        this.detailMsg = detailMsg;
        return this;
    }
}
