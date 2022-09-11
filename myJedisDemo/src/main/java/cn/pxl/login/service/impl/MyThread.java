package cn.pxl.login.service.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

class ServiceImpl {

    //控制单元
    public void redisService(Jedis jedis,String id){

        String value = jedis.get("compid:" + id);
        try {
            if(value == null){
                jedis.setex("compid:" + id,20,String.valueOf(Long.MAX_VALUE -10));
                System.out.println("未创建id");
            } else {
                jedis.incr("compid:" + id);
                System.out.println("已创建id");
            }
        } catch (JedisDataException e) {
            System.out.println("使用已达次数限制，请先升级会员！");
            return;
        } finally {
            jedis.close();
        }
    }

    public void business(){
        System.out.println("业务操作执行！");
    }
}

public class MyThread extends Thread{

    public void run(){

        Jedis jedis = new Jedis("10.211.55.6", 6379);

        ServiceImpl service = new ServiceImpl();
        while (true){
//            service.business();
            service.redisService(jedis,"初级用户");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}