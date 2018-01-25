package com.livgo.boot.data.redis;

import com.livgo.boot.common.util.valid.ValidUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:Redis客户端
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
public class RedisClient {


    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.get(key) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 通过key删除
     *
     * @param key
     */
    public static void del(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (jedis != null) {
                jedis.del(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 添加key value
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (!ValidUtil.isEmpty(value)) {
                if (jedis != null) {
                    jedis.set(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 添加key value 并且设置存活时间
     *
     * @param key
     * @param value
     * @param liveTime
     */
    public static void set(String key, String value, int liveTime) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (!ValidUtil.isEmpty(value)) {
                if (jedis != null) {
                    jedis.set(key, value);
                    jedis.expire(key, liveTime);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean exists(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.exists(key) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 设置过期时间
     *
     * @param key
     * @param liveTime 秒
     */
    public static void expire(String key, int liveTime) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (jedis != null) {
                jedis.expire(key, liveTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 指定具体的过期时间点
     * 例：2020年02月02日02点02分02秒
     *
     * @param key
     * @param timestamp
     */
    public static void expireAt(String key, long timestamp) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (jedis != null) {
                jedis.expireAt(key, timestamp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 控制 同一个key 一秒钟只能保存一次
     *
     * @param key
     * @param value
     * @param liveTime
     * @return
     */
    public static boolean setNxt(String key, String value, int liveTime) {
        boolean flag = true;
        Jedis jedis = RedisPool.getJedis();
        try {
            if (!ValidUtil.isEmpty(value)) {
                if ((jedis != null ? jedis.setnx(key, value).intValue() : 0) == 1) {
                    jedis.expire(key, liveTime);
                    flag = true;
                } else {
                    flag = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = true;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return flag;
    }


    /**
     * 增量+1
     *
     * @param key
     * @return
     */
    public static int incrby(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.incrBy(key, 1).intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 通过正则匹配keys
     *
     * @param pattern
     * @return
     */
    public static Set<String> pattern(String pattern) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.keys(pattern) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 追加字符
     *
     * @param key
     * @param value
     */
    public static void append(String key, String value) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (jedis != null) {
                jedis.append(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 排序(适用set,sortset,list这3种数据类型)
     *
     * @param key
     * @param sortingParams
     */
    public static List<String> sort(String key, SortingParams sortingParams) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.sort(key, sortingParams) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * Map:
     * 添加map
     *
     * @param key
     * @param map
     */
    public static void mapSets(String key, Map<String, String> map) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (jedis != null) {
                jedis.hmset(key, map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Map:
     * 添加
     *
     * @param key
     * @param mapKey
     * @param mapValue
     */
    public static void mapSet(String key, String mapKey, String mapValue) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (jedis != null) {
                jedis.hset(key, mapKey, mapValue);
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Map:
     * 删除指定map集合内的数据
     *
     * @param key
     * @param mapKeys
     */
    public static void mapDels(String key, String... mapKeys) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (jedis != null) {
                jedis.hdel(key, mapKeys);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Map:
     * 遍历集合
     *
     * @param key
     * @return
     */
    public static List<String> mapGets(String key, String... mapKeys) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.hmget(key, mapKeys) : null;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Map:
     * 获取某个map的value
     *
     * @param key
     * @param mapKey
     * @return
     */
    public static String mapGet(String key, String mapKey) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.hget(key, mapKey) : null;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Map:
     * 遍历key下所有的hash数据
     *
     * @param key
     * @return
     */
    public static List<String> mapGetValueAll(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.hvals(key) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Map:
     * 遍历key下所有的hash数据(包含map的key与value值)
     *
     * @param key
     * @return
     */
    public static Map<String, String> mapGetAll(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.hgetAll(key) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Map:
     * 遍历key下所有的map key 值
     *
     * @param key
     * @return
     */
    public static Set<String> mapGetKeys(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.hkeys(key) : null;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Map:
     * 获取集合大小
     *
     * @param key
     * @return
     */
    public static Long mapSize(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.hlen(key) : null;
        } catch (Exception e) {

            e.printStackTrace();
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Map:
     * 增量 increment
     *
     * @param key
     * @param mapKey
     * @param increment
     * @return
     */
    public static Long mapIncrby(String key, String mapKey, int increment) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.hincrBy(key, mapKey, increment) : null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0L;
    }

    /**
     * List:
     * list入对列
     *
     * @param key
     * @param value
     */
    public static void lstPush(String key, String value) {
        // 本地缓存后入队列
        Jedis jedis = RedisPool.getJedis();
        try {
            if (!ValidUtil.isEmpty(value)) {
                if (jedis != null) {
                    jedis.rpush(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * List:
     * 入对列
     *
     * @param key
     * @param value 一次多条
     */
    public static void lstPushs(String key, String... value) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (jedis != null) {
                jedis.rpush(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * List:
     * 出对列
     *
     * @param key
     */
    public static String lstPop(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.lpop(key) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * List:
     * 移除指定key下size个value元素
     * size=2为从顶部移除值为value的2个元素
     * size=-2为从底部移除值为value的2个元素
     *
     * @param key
     * @param size
     * @param value
     */
    public static void lstRemove(String key, long size, String value) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (jedis != null) {
                jedis.lrem(key, size, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * List:
     * 获取list列表
     *
     * @param key
     * @param start 0
     * @param end   -1 可查全部
     * @return
     */
    public static List<String> lstAll(String key, long start, long end) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.lrange(key, start, end) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * List:
     * list size
     *
     * @param key
     * @return
     */
    public static Long lstSize(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.llen(key) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * List:
     * 返回列表 key 中，下标为 index 的元素
     *
     * @param key
     * @param index
     * @return
     */
    public static String lstIndex(String key, int index) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.lindex(key, index) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * List:
     * 裁剪指定范围的list,也就是只保留这个范围内的数据
     *
     * @param key
     * @param start
     * @param end
     */
    public static void lstTrim(String key, long start, long end) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (jedis != null) {
                jedis.ltrim(key, start, end);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Set:
     * 添加元素
     *
     * @param key
     * @param values 可多个value
     */
    public static void setAdds(String key, String... values) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (jedis != null) {
                jedis.sadd(key, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Set:
     * 遍历集合
     *
     * @param key
     * @return
     */
    public static Set<String> setGet(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.smembers(key) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * Set:
     * 删除集合里的元素
     *
     * @param key
     * @param value
     */
    public static void setRemove(String key, String value) {
        Jedis jedis = RedisPool.getJedis();
        try {
            if (jedis != null) {
                jedis.srem(key, value);
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Set:
     * 集合大小
     *
     * @param key
     * @return
     */
    public static Long setSize(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.scard(key) : null;
        } catch (Exception e) {

            e.printStackTrace();
            return 0L;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Set:
     * 判断集合中某元素是否存在
     *
     * @param key
     * @param value
     * @return
     */
    public static Boolean setExist(String key, String value) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.sismember(key, value) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 发布消息
     *
     * @param channel
     * @param message
     * @return
     */
    public static Long pub(String channel, String message) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.publish(channel, message) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * @param key
     * @return
     */
    public static Long getKeyLiveTime(String key) {
        Jedis jedis = RedisPool.getJedis();
        try {
            return jedis != null ? jedis.ttl(key) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
