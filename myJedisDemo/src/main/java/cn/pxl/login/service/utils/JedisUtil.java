package cn.pxl.login.service.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtil {
    private static JedisPool jedisPool;
    static {
        ResourceBundle redisProperties = ResourceBundle.getBundle("redis");
        String host = redisProperties.getString("redis.host");
        Integer port = Integer.parseInt(redisProperties.getString("redis.port"));
        Integer maxTol = Integer.parseInt(redisProperties.getString("redis.maxTol"));
        Integer del = Integer.parseInt(redisProperties.getString("redis.maxTol"));
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTol);
        jedisPoolConfig.setMaxIdle(del);
        jedisPool = new JedisPool(jedisPoolConfig,host,port);
    }

    public static Jedis newInstance(){
        return jedisPool.getResource();
    }

}
