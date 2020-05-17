package com.cslg.dto;

/**
 * <h3>schoolmall</h3>
 * <p></p>
 *
 * @author:MIKU
 * @date : 2020-05-16 12:41
 **/
public class Result<T> {
    private Boolean success;
    private T data;
    private String errorMsg;
    private Integer errCode;

    public Result() {
    }

    public Result(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Result(Boolean success, Integer errCode, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.errCode = errCode;
    }

    public boolean isSuccess(){
        return success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }
}
