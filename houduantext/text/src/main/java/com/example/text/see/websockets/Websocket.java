package com.example.text.see.websockets;

import com.alibaba.fastjson.JSON;
import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@ConditionalOnClass(value = Websocketconfig.class)
@Component
@ServerEndpoint("/websocket")
@Slf4j
public class Websocket {
    private Session session;
    private String username;
    private String tousername;

    private static Integer usernumber = 0;
    private static CopyOnWriteArraySet<Websocket> websocketnumber = new CopyOnWriteArraySet<>();

    @OnOpen
    public void opOpen(Session session, @RequestParam String username) throws IOException {
        this.session = session;
        this.username = username;
        websocketnumber.add(this);
        usernumber++;
        Set<String> users = new TreeSet<>();
        for (Websocket websocket : websocketnumber) {
            users.add(websocket.username);
        }
        Map<String, Object> online = new HashMap<>();
        online.put("online", users);
        online.put("messagetype", 1);
        online.put("username", username);
        online.put("usernumber", usernumber);
        sendMessageAll(JSON.toJSONString(online), this.username);
        log.info("【websocket消息】有了新的连接， 总数:{}", usernumber);
    }
    @OnMessage
    public void onMessage() {

    }

    @OnClose
    public void onClose() {

    }

    public void sendMessageAll(String message, String username) throws IOException {
        for (Websocket websocket : websocketnumber) {
            websocket.session.getBasicRemote().sendText(message);
        }
    }
}
