package com.livgo.boot.data.redis;

import com.livgo.boot.common.util.prop.PropUtil;
import com.alibaba.druid.util.StringUtils;
import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Description:Redis配置
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
public class RedisConfig {

    public static String REDIS_HOST_IP;
    public static int REDIS_HOST_PORT;
    public static String REDIS_PASSWORD;
    public static int REDIS_MAX_TOTAL;
    public static int REDIS_MAX_IDLE;
    public static int REDIS_MAX_WAIT_MILLS;
    public static boolean REDIS_TEST_ON_BORROW;

    static {
        Properties prop = PropUtil.loadFile(System.getProperty("config.path.redis"));
        if (null != prop) {
            REDIS_HOST_IP = prop.getProperty("redis.config.host.ip");
            REDIS_HOST_PORT = Integer.parseInt(prop.getProperty("redis.config.host.port"));
            REDIS_PASSWORD = prop.getProperty("redis.config.password");
            REDIS_MAX_TOTAL = Integer.parseInt(prop.getProperty("redis.config.maxtotal"));
            REDIS_MAX_IDLE = Integer.parseInt(prop.getProperty("redis.config.maxidle"));
            REDIS_MAX_WAIT_MILLS = Integer.parseInt(prop.getProperty("redis.config.maxwaitmills"));
            REDIS_TEST_ON_BORROW = Boolean.parseBoolean(prop.getProperty("redis.config.testonborrow"));
        }
    }

}
