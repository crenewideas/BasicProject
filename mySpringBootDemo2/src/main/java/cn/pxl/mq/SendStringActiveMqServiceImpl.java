package cn.pxl.mq;

import cn.pxl.mq.intf.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendStringActiveMqServiceImpl implements ActiveMqService<String> {

    //由spring boot 自动产生的 jmsTemplate
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMsg(String message) {
        System.out.println("发送消息：" + message);
        jmsTemplate.convertAndSend(message);
    }

    @JmsListener(destination = "${spring.jms.template.default-destination}")
    @Override
    public void receiveMsg(String message) {
        System.out.println("接收到消息："+message);
    }
}
