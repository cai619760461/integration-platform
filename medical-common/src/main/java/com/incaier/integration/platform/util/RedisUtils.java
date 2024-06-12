package com.incaier.integration.platform.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存操作类
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 维护一个本类的静态变量
    private static RedisUtils cacheUtils;

    @PostConstruct
    public void init() {
        cacheUtils = this;
        cacheUtils.redisTemplate = this.redisTemplate;
    }

    public void increase(String key) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            entityIdCounter.getAndIncrement();
        } else {
            redisTemplate.opsForValue().set(key, "1");
        }
    }

    public boolean compareKey(String key, int i) {
        if (Boolean.TRUE.equals(cacheUtils.redisTemplate.hasKey(key))) {
            String value = cacheUtils.redisTemplate.opsForValue().get(key);
            System.out.println("key当前值为：----------" + value);
            int i1 = Integer.parseInt(value);
            return i1 >= i;
        }
        return false;
    }

    /**
     * 将参数中的字符串值设置为键的值，不设置过期时间
     *
     * @param value 必须要实现 Serializable 接口
     * @param key   钥匙
     */
    public static void set(String key, String value) {
        cacheUtils.redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将参数中的字符串值设置为键的值，设置过期时间
     *
     * @param value   必须要实现 Serializable 接口
     * @param key     钥匙
     * @param timeout 超时
     */
    public static void set(String key, String value, Long timeout) {
        cacheUtils.redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取与指定键相关的值
     *
     * @param key 钥匙
     * @return {@link String}
     */
    public static String get(String key) {
        return cacheUtils.redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置某个键的过期时间
     *
     * @param key 键值
     * @param ttl 过期秒数
     * @return boolean
     */
    public static boolean expire(String key, Long ttl) {
        return Boolean.TRUE.equals(cacheUtils.redisTemplate.expire(key, ttl, TimeUnit.SECONDS));
    }

    /**
     * 判断某个键是否存在
     *
     * @param key 键值
     */
    public static boolean hasKey(String key) {
        return Boolean.TRUE.equals(cacheUtils.redisTemplate.hasKey(key));
    }

    /**
     * 向集合添加元素
     *
     * @param key   钥匙
     * @param value 价值
     * @return 返回值为设置成功的value数
     */
    public static Long sAdd(String key, String... value) {
        return cacheUtils.redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取集合中的某个元素
     *
     * @param key 钥匙
     * @return 返回值为redis中键值为key的value的Set集合
     */
    public static Set<String> sGetMembers(String key) {
        return cacheUtils.redisTemplate.opsForSet().members(key);
    }

    /**
     * 将给定分数的指定成员添加到键中存储的排序集合中
     *
     * @param key   钥匙
     * @param value 价值
     * @param score 分数
     * @return {@link Boolean}
     */
    public static Boolean zAdd(String key, String value, double score) {
        return cacheUtils.redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 返回指定排序集中给定成员的分数
     *
     * @param key   钥匙
     * @param value 价值
     * @return {@link Double}
     */
    public static Double zScore(String key, String value) {
        return cacheUtils.redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 删除指定的键
     *
     * @param key 钥匙
     * @return {@link Boolean}
     */
    public static Boolean delete(String key) {
        return cacheUtils.redisTemplate.delete(key);
    }

    /**
     * 删除多个键
     *
     * @param keys 钥匙
     * @return {@link Long}
     */
    public static Long delete(Collection<String> keys) {
        return cacheUtils.redisTemplate.delete(keys);
    }
}