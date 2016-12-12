package cn.edu.nefu.gdms.util;

import cn.edu.nefu.gdms.common.ErrorCodeEnum;
import cn.edu.nefu.gdms.dto.Result;
import cn.edu.nefu.gdms.exception.ServiceException;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public class ResultUtils {
    public static Result getResult(int errorCode) {
        return getResult(ErrorCodeEnum.getErrorCodeEnumFromId(errorCode));
    }

    public static Result getResult(ErrorCodeEnum errorCodeEnum) {
        Result result = new Result();
        if (errorCodeEnum == ErrorCodeEnum.OK) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }


        result.setError(errorCodeEnum.getErrorText());
        return result;
    }

    public static Result getResult(ServiceException e) {
        Result result = getResult(e.getErrorCode());
        result.setError(e.getErrorText());

        return result;
    }

    public static Result getResult(Exception e) {
        Result result;
        if (e instanceof ServiceException) {
            result = getResult((ServiceException) e);
        } else {
            result = getResult(ErrorCodeEnum.SERVER_ERROR);
        }
        return result;
    }


    public static Result getSuccessResult() {
        return getResult(ErrorCodeEnum.OK);
    }
}
