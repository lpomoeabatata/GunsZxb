package com.stylefeng.guns.config;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * ehcache配置
 *
 * @author fengshuonan
 * @date 2017-05-20 23:11
 */
@Configuration
@EnableCaching
public class EhCacheConfig {

    /**
     * EhCache的配置
     */
    @Bean
    public EhCacheCacheManager cacheManager(CacheManager cacheManager) {
        return new EhCacheCacheManager(cacheManager);
    }

    /**
     * EhCache的配置
     */
    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }

//    /**
//     * Redis的配置
//     */
//    @Bean
//    public RedisCacheManager cacheManage(RedisTemplate redisTemplate){
//        return new RedisCacheManager(redisTemplate);
//    }
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(){
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.afterPropertiesSet();
//        return jedisConnectionFactory;
//    }
//    @Bean
//    public RedisTemplate<String,String> redisTemplate(JedisConnectionFactory jedisConnectionFactory){
//        RedisTemplate<String,String> redisTemplate=new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
}
