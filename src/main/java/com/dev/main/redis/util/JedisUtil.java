package com.dev.main.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(100);
        jedisPool = new JedisPool(jedisPoolConfig,"119.29.94.246",6379, 10000, "redis272243");
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static void closeJedis(Jedis jedis){
        jedis.close();
    }
}
