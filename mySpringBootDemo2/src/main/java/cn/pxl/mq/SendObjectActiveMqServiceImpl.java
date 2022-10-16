package cn.pxl.mq;

import cn.pxl.entity.MqEntity;
import cn.pxl.mq.intf.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

//发送实体时，还需要额外在配置文件中配置 trusted 值，。
@Service
public class SendObjectActiveMqServiceImpl implements ActiveMqService<MqEntity> {

    //由spring boot 自动产生的 jmsTemplate
    @Autowired
    private JmsTemplate jmsTemplate;

    private static final String MY_DESTINATION = "my-destination";

    @Override
    public void sendMsg(MqEntity message) {
        System.out.println("发送消息：" + message);
        jmsTemplate.convertAndSend(MY_DESTINATION,message);
    }

    @JmsListener(destination = MY_DESTINATION)
    @Override
    public void receiveMsg(MqEntity message) {
        System.out.println("接收到消息："+message);
    }
}
