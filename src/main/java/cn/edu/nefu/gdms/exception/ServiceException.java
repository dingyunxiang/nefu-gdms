package cn.edu.nefu.gdms.exception;

import cn.edu.nefu.gdms.common.ErrorCodeEnum;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public class ServiceException extends RuntimeException {

    private int errorCode;
    private String error;
    private String errorText;

    public ServiceException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.toString());
        this.errorCode = errorCodeEnum.errorCode;
        this.error = errorCodeEnum.error;
        this.errorText = errorCodeEnum.errorText;
    }

    public ServiceException(ErrorCodeEnum errorCodeEnum, String error) {
        super(errorCodeEnum.toString() + ",  " + error);
        this.errorCode = errorCodeEnum.errorCode;
        this.error = error;
        this.errorText = errorCodeEnum.errorText;
    }

    public ServiceException(ErrorCodeEnum errorCodeEnum, String error, String errorText) {
        this.errorCode = errorCodeEnum.errorCode;
        this.error = error;
        this.errorText = errorText;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
