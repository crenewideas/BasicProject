package cn.pxl.controller;

import cn.pxl.entity.MqEntity;
import cn.pxl.mq.intf.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/activemq")
public class ActiveMqController {

    @Autowired()
    @Qualifier("sendStringActiveMqServiceImpl")
    private ActiveMqService<String> sendStringActiveMqServiceImpl;

    @Autowired()
    @Qualifier("sendObjectActiveMqServiceImpl")
    private ActiveMqService<MqEntity> sendObjectActiveMqServiceImpl;

    @GetMapping("/strmsg")
    public Map<String,Object> msg(String msg){
        sendStringActiveMqServiceImpl.sendMsg(msg);
        return getResult(msg);
    }

    @GetMapping("/entitymsg")
    public Map<String,Object> msg(Long id,String userName,String note){
        MqEntity msg = new MqEntity(id,userName,note);
        sendObjectActiveMqServiceImpl.sendMsg(msg);
        return getResult(msg);
    }

    private Map<String,Object> getResult(Object message){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("success","success");
        stringObjectHashMap.put("message",message);
        return stringObjectHashMap;
    }

}
