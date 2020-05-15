package com.mtons.mblog.base.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存自定义注解
 *
 * @author xiaomi
 * @date 2020/04/17
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisCache {

    /**
     * 业务的名称
     */
    String value() default "";

    /**
     * redis缓存的Key（默认类名-方法名-自定义key）
     */
    String key() default "";

    /**
     * 是否刷新缓存，默认false
     */
    boolean flush() default false;

    /**
     * 缓存失效时间，默认5分钟
     */
    long expire() default 5L;

    /**
     * 缓存时间单位，默认分钟
     */
    TimeUnit unit() default TimeUnit.MINUTES;

    /**
     * 是否启用缓存，默认启用
     */
    boolean enable() default true;
}
