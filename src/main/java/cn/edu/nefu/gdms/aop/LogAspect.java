package cn.edu.nefu.gdms.aop;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * Created by sigh on 2015/6/25.
 */
@Service
@Aspect
@Order(1)
public class LogAspect {
    private static Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(cn.edu.nefu.gdms.aop.Log) || @within(cn.edu.nefu.gdms.aop.Log)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object doSurround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            Object result = proceedingJoinPoint.proceed();
            LOGGER.info("class: {}, method: {}, params: {}, result: {}, cost: {}ms",
                    proceedingJoinPoint.getTarget().getClass().getName(),
                    proceedingJoinPoint.getSignature().getName(),
                    proceedingJoinPoint.getArgs(),
                    result,
                    System.currentTimeMillis() - startTime);
            return result;
        } catch (Exception e) {
            LOGGER.info("class: {}, method: {}, params: {}, exception: {}, cost: {}ms",
                    proceedingJoinPoint.getTarget().getClass().getName(),
                    proceedingJoinPoint.getSignature().getName(),
                    proceedingJoinPoint.getArgs(),
                    ExceptionUtils.getStackTrace(e),
                    System.currentTimeMillis() - startTime);
            throw e;
        }
    }

}
