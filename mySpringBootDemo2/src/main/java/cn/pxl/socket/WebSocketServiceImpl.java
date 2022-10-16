package cn.pxl.socket;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@ServerEndpoint("/ws")
@Data
public class WebSocketServiceImpl {

    //静态变量，保存当前在线连接数
    private static int onlineCount = 0;
    //concurrent 包的线程安全 set，用于存放每个客户端对应的 WebSocketServiceImpl 对象。
    private static CopyOnWriteArrayList<WebSocketServiceImpl> webSocketServices = new CopyOnWriteArrayList();
    //与某个客户端连接的会话，用于给客户端发送数据。
    private Session session;

    //连接成功调用的方法
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketServices.add(this);
        addOnlineCount();
        System.out.println("有新连接加入，当前在线人数为："+getOnlineCount() + "人");
        try {
            sendMessage("有新连接加入了！！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("io异常");
        }
    }

    @OnClose
    public void onClose(){
        webSocketServices.remove(this);
        subOnlineCount();
        System.out.println("有旧连接退出，当前在线人数为："+getOnlineCount() + "人");
    }

    //收到客户端消息后调用的方法
    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("来自客户端的消息：" + message);
        //群发消息
        for (WebSocketServiceImpl webSocketService : webSocketServices) {
            String name = webSocketService.getSession().getUserPrincipal().getName();
            System.out.println(name);
            try {
                webSocketService.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误！");
        error.printStackTrace();
    }


    private void sendMessage(String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    private static synchronized void addOnlineCount(){
        WebSocketServiceImpl.onlineCount++;
    }

    private static synchronized void subOnlineCount(){
        WebSocketServiceImpl.onlineCount--;
    }

    private static int getOnlineCount(){
        return WebSocketServiceImpl.onlineCount;
    }

}
