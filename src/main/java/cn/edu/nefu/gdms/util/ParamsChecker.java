package cn.edu.nefu.gdms.util;

import cn.edu.nefu.gdms.common.ErrorCodeEnum;
import cn.edu.nefu.gdms.exception.ServiceException;
import cn.edu.nefu.gdms.model.TimePeriod;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by dingyunxiang on 16/12/15.
 */
public class ParamsChecker {
    public static void check(TimePeriod timePeriod) {
        try {
            Preconditions.checkArgument(timePeriod != null, "organization must not be null");
            Preconditions.checkArgument(StringUtils.isNotBlank(timePeriod.getName()), "name must not be null or blank");
            Preconditions.checkArgument(timePeriod.getBeginTime() > timePeriod.getEndTime(), "beginTime must < endTime");
            Preconditions.checkArgument(timePeriod.getBeginTime() > 0, "beginTime must > 0");
        } catch (IllegalArgumentException e) {
            throw transferToServiceException(e);
        }
    }

    public static ServiceException transferToServiceException(IllegalArgumentException e) {
        ServiceException serviceException = new ServiceException(ErrorCodeEnum.PARAMS_ERROR);
        serviceException.setError(e.getMessage());
        serviceException.setErrorText(e.getMessage());
        return serviceException;
    }

}
