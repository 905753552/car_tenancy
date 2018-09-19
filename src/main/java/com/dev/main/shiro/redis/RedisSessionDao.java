package com.dev.main.shiro.redis;

import com.dev.main.redis.util.JedisUtil;
import com.dev.main.shiro.util.ShiroConstant;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;

@Component
public class RedisSessionDao extends CachingSessionDAO {

    @Autowired
    private JedisPool jedisPool;

    private static int EXPRIE = 10000;

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionDao.class);


    @Override
    protected Serializable doCreate(Session session) {
        LOGGER.info("--------doCreate-----");
        Serializable serializable = this.generateSessionId(session);
        assignSessionId(session, serializable);
        Jedis jedis = jedisPool.getResource();
        session.setTimeout(EXPRIE * 1000);
        jedis.setex(getByteKey(serializable), EXPRIE, SerializationUtils.serialize((Serializable) session));
        JedisUtil.closeJedis(jedis);
        return serializable;
    }


    @Override
    protected Session doReadSession(Serializable serializable) {
        LOGGER.info("--------doReadSession-----");
        Jedis jedis = jedisPool.getResource();
        Session session = null;
        byte[] s = jedis.get(getByteKey(serializable));
        if (s != null) {
            session = SerializationUtils.deserialize(s);
            jedis.expire((ShiroConstant.PREFIX + serializable).getBytes(), EXPRIE);
        }
        //判断是否有会话  没有返回NULL
        if (session == null) {
            return null;
        }
        JedisUtil.closeJedis(jedis);
        return session;
    }

    private byte[] getByteKey(Object k) {
        if (k instanceof String) {
            String key = ShiroConstant.PREFIX + k;
            return key.getBytes();
        } else {
            return SerializationUtils.serialize((Serializable) k);
        }
    }

    @Override
    protected void doUpdate(Session session) {
        LOGGER.info("--------doUpdate-----");
        if (session == null) {
            return;
        }
        Jedis jedis = jedisPool.getResource();
        session.setTimeout(EXPRIE * 1000);
       /*jedis.set(getByteKey(session.getId()),SerializationUtils.serialize((Serializable)session));
       jedis.expire(SerializationUtils.serialize((ShiroConstant.PREFIX+session.getId())),EXPRIE);*/
        jedis.setex(getByteKey(session.getId()), EXPRIE, SerializationUtils.serialize((Serializable) session));


    }


    @Override
    protected void doDelete(Session session) {
        LOGGER.info("--------doDelete-----");
        Jedis jedis = jedisPool.getResource();
        jedis.del(getByteKey(session.getId()));
        JedisUtil.closeJedis(jedis);

    }


}
