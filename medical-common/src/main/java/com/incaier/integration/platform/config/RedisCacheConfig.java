package com.incaier.integration.platform.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Redis缓存配置
 *
 * @author caiweijie
 * @date 2024/06/20
 */
@Configuration
public class RedisCacheConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws Exception {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //使用 Jackson2JsonRedisSerializer 来序列化和反序列化 redis 的 value 值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer<Object> jacksonSeial = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jacksonSeial.setObjectMapper(om);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jacksonSeial);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public KeyGenerator simpleKeyGenerator() {
        return (o, method, objects) -> o.getClass().getSimpleName() + "#" + method.getName() + "(" + JSON.toJSONString(objects) + ")";
    }

    @Primary
    @Bean
    public RedisCacheManager ttlCacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new TtlRedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                // 默认缓存配置
                this.getRedisCacheConfigurationWithTtl(60), this.getRedisCacheConfigurationMap());
    }

    /**
     * 获取自定义redis缓存配置映射
     *
     * @return {@link Map}<{@link String}, {@link RedisCacheConfiguration}>
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("xxx", this.getRedisCacheConfigurationWithTtl(3600));
        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer)).entryTtl(Duration.ofSeconds(seconds));
        return redisCacheConfiguration;
    }

    /**
     * ttl-redis缓存管理器
     *
     * @author caiweijie
     * @date 2024/06/20
     */
    public static class TtlRedisCacheManager extends RedisCacheManager {
        public TtlRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations) {
            super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
        }

        @NotNull
        @Override
        protected RedisCache createRedisCache(@NotNull String name, RedisCacheConfiguration cacheConfig) {
            String[] cells = StringUtils.delimitedListToStringArray(name, "=");
            name = cells[0];
            if (cells.length > 1) {
                long ttl = Long.parseLong(cells[1]);
                // 根据传参设置缓存失效时间
                cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(ttl));
            }
            return super.createRedisCache(name, cacheConfig);
        }
    }

    /**
     * 对hash类型的数据操作
     *
     * @param redisTemplate redis模板
     * @return {@link HashOperations}<{@link String}, {@link String}, {@link Object}>
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate redis模板
     * @return {@link ValueOperations}<{@link String}, {@link Object}>
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate redis模板
     * @return {@link ListOperations}<{@link String}, {@link Object}>
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate redis模板
     * @return {@link SetOperations}<{@link String}, {@link Object}>
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate redis模板
     * @return {@link ZSetOperations}<{@link String}, {@link Object}>
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}

