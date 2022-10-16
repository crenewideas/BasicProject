package cn.pxl.mq.intf;

public interface  ActiveMqService <T>{
    void sendMsg(T message);
    void receiveMsg(T message);
}
