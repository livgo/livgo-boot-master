package com.livgo.boot.data.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;


/**
 * Description:Redis连接池
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
public class RedisPool {

    private static JedisPool jedisPool = null;

    /**
     * 获取Jedis实例
     *
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool == null) {
                createJedisPool();
            }
            return jedisPool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建jedispool
     */
    public synchronized static void createJedisPool() {
        try {
            if (jedisPool == null) {
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxTotal(RedisConfig.REDIS_MAX_TOTAL);
                config.setMaxIdle(RedisConfig.REDIS_MAX_IDLE);
                config.setMaxWaitMillis(RedisConfig.REDIS_MAX_WAIT_MILLS);
                config.setTestOnBorrow(RedisConfig.REDIS_TEST_ON_BORROW);
                if (org.apache.commons.lang3.StringUtils.isEmpty(RedisConfig.REDIS_PASSWORD)) {
                    jedisPool = new JedisPool(config, RedisConfig.REDIS_HOST_IP, RedisConfig.REDIS_HOST_PORT);
                } else {
                    jedisPool = new JedisPool(config, RedisConfig.REDIS_HOST_IP, RedisConfig.REDIS_HOST_PORT,
                            Protocol.DEFAULT_TIMEOUT, RedisConfig.REDIS_PASSWORD);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
