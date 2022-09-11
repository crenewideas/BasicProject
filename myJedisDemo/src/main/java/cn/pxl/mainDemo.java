package cn.pxl;

import redis.clients.jedis.Jedis;

import java.util.List;

public class mainDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.211.55.6", 6379);
//        jedis.set("aaa","哈哈哈");
//        System.out.println(jedis.get("aaa"));

//        jedis.lpush("list1", "a", "b", "c");
//        jedis.rpush("list1","x");
//        List<String> list1 = jedis.lrange("list1", 0, -1);
//        for (String s : list1) {
//            System.out.println(s);
//        }

//        jedis.hset("hash","field1","aaa");
//        jedis.hset("hash","field2","bbb");
        String hgetVal = jedis.hget("hash", "field1");
        System.out.println(hgetVal);
        //查看有多少数据。
        System.out.println(jedis.hlen("hash"));


    }
}
