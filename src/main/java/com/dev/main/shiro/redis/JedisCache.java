package com.dev.main.shiro.redis;

import com.dev.main.redis.util.JedisUtil;
import com.dev.main.shiro.util.ShiroConstant;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.*;

public class JedisCache<K,V> implements Cache<K, V> ,Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisCache.class);

    private byte[] getByteKey(K k){
        if(k instanceof String){
            String key = ShiroConstant.PREFIX+k;
            return key.getBytes();
        }else {
            return SerializationUtils.serialize((Serializable) k);
        }
    }

    @Override
    public int size() {
        Jedis jedis = JedisUtil.getJedis();
        Long size = jedis.dbSize();
        return size.intValue();
    }

    @Override
    public Set<K> keys() {
        Jedis jedis = JedisUtil.getJedis();

        Set<byte[]> bytes = jedis.keys( (ShiroConstant.PREFIX + new String("*")).getBytes());
        Set<K>  keys = new HashSet<>();
        if(bytes!=null){
            for (byte[] b: bytes) {
                keys.add(SerializationUtils.deserialize(b));
            }
        }
        JedisUtil.closeJedis(jedis);
        return  keys;
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = this.keys();
        Jedis jedis = JedisUtil.getJedis();
        List<V> lists = new ArrayList<>();
        for (K k:keys) {
            byte[] bytes = jedis.get(getByteKey(k));
            lists.add(SerializationUtils.deserialize(bytes));
        }
        JedisUtil.closeJedis(jedis);
        return lists;
    }

    @Override
    public void clear() {
        JedisUtil.getJedis().flushDB();
    }

    @Override
    public V put(K k, V v) {
        LOGGER.info("key---->"+k+"value---->"+v);
        Jedis jedis = JedisUtil.getJedis();
        jedis.set(getByteKey(k), SerializationUtils.serialize((Serializable) v));
        jedis.expire(getByteKey(k),10000);
        byte[] bytes = jedis.get(SerializationUtils.serialize(getByteKey(k)));
        JedisUtil.closeJedis(jedis);
        if(bytes==null){
            return null;
        }
        return SerializationUtils.deserialize(bytes);
    }

    @Override
    public V get(K k) {
        LOGGER.info("get------>key="+k);
        if(k==null){
            return null;
        }
        //System.out.println(k);
        Jedis jedis = JedisUtil.getJedis();
        byte[] bytes = jedis.get(getByteKey(k));
        JedisUtil.closeJedis(jedis);
        if(bytes==null){
            return null;
        }
        return SerializationUtils.deserialize(bytes);
    }

    @Override
    public V remove(K k) {
        Jedis jedis = JedisUtil.getJedis();
        byte[] bytes = jedis.get(getByteKey(k));
        jedis.del(getByteKey(k));
        JedisUtil.closeJedis(jedis);
        if(bytes==null){
            return null;
        }
        return SerializationUtils.deserialize(bytes);
    }
}
