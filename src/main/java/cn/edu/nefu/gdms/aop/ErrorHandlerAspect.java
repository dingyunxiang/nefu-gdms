package cn.edu.nefu.gdms.aop;

import cn.edu.nefu.gdms.dto.Result;
import cn.edu.nefu.gdms.exception.ServiceException;
import cn.edu.nefu.gdms.util.ResultUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Created by sigh on 2015/6/25.
 */
@Service
@Aspect
@Order(5)
public class ErrorHandlerAspect {
    private static Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerAspect.class);

    @Pointcut("@within(cn.edu.nefu.gdms.aop.ErrorHandler) && execution(public * *(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object doSurround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            return proceedingJoinPoint.proceed();
        } catch (Exception e) {
            if (!(e instanceof ServiceException)) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            }

            return exception2Response(proceedingJoinPoint, e);
        }
    }

    /**
     * 将异常转换为对应返回值
     */
    private Object exception2Response(ProceedingJoinPoint proceedingJoinPoint, Exception e) throws InstantiationException, IllegalAccessException {
        Class returnType = ((MethodSignature) proceedingJoinPoint.getSignature()).getReturnType();
        if (returnType.isAssignableFrom(Void.TYPE)) {
            return Void.TYPE;
        } else if (returnType.isAssignableFrom(boolean.class) || returnType.isAssignableFrom(Boolean.class)) {
            return false;
        } else if (returnType.isAssignableFrom(Result.class)) {
            return ResultUtils.getResult(e);
        } else {
            Object result = returnType.newInstance();
            for (Field field : result.getClass().getDeclaredFields()) {
                if (field.getType().isAssignableFrom(Result.class)) {
                    field.set(result, ResultUtils.getResult(e));
                    break;
                }
            }
            return result;
        }
    }
}
