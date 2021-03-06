package com.mtons.mblog.modules.aspect;

import com.mtons.mblog.base.annotation.RedisCache;
import com.mtons.mblog.base.utils.AspectUtil;
import com.mtons.mblog.base.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName: RedisCacheAspect
 * @Auther: Jerry
 * @Date: 2020/4/17 10:24
 * @Desctiption: Redis业务层数据缓存
 * @Version: 1.0
 */
@Slf4j
@Aspect
@Component
public class RedisCacheAspect {

    private static final String BIZ_CACHE_PREFIX = "mblog_cache_";

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut(value = "@annotation(com.mtons.mblog.base.annotation.RedisCache)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint point) throws Throwable {
        Method currentMethod = AspectUtil.INSTANCE.getMethod(point);
        //获取操作名称
        RedisCache cache = currentMethod.getAnnotation(RedisCache.class);
        boolean enable = cache.enable();

        if (!enable) {
            return point.proceed();
        }
        boolean flush = cache.flush();
        if (flush) {
            String classPrefix = AspectUtil.INSTANCE.getKey(point, BIZ_CACHE_PREFIX);
            log.info("清空缓存 - {}*", classPrefix);
            redisUtil.del(classPrefix);
            return point.proceed();
        }
        String key = AspectUtil.INSTANCE.getKey(point, cache.key(), BIZ_CACHE_PREFIX);
        boolean hasKey = redisUtil.hasKey(key);
        if (hasKey) {
            try {
                log.info("{}从缓存中获取数据", key);
                return redisUtil.get(key);
            } catch (Exception e) {
                log.error("从缓存中获取数据失败！", e);
            }
        }
        //先执行业务
        Object result = point.proceed();

        redisUtil.set(key, result, cache.expire(), cache.unit());
        log.info("{}从数据库中获取数据", key);
        return result;
    }

}
