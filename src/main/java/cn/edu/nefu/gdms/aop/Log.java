package cn.edu.nefu.gdms.aop;

import java.lang.annotation.*;

/**
 * Created by sigh on 2015/6/25.
 */

/**
 * 拦截器定义
 *
 * @see LogAspect
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
}
