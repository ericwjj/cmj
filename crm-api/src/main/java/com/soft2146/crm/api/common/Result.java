package com.soft2146.crm.api.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tao
 * @version 1.0
 * @ClassName Result
 * @Description TODO
 * @date 2021-04-04 10:55
 **/
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = -3948389268046368059L;

    private Integer code;

    private String msg;

    private Object data;

    private Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success() {
        Result result = new Result();
        result.setResultCode(com.soft2146.crm.api.common.ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(com.soft2146.crm.api.common.ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result failure(com.soft2146.crm.api.common.ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result failure(com.soft2146.crm.api.common.ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public void setResultCode(com.soft2146.crm.api.common.ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }
}
