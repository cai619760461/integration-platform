package com.incaier.integration.platform.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.incaier.integration.platform.util.IdLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ServerEndpoint(value = "/websocket/{authorization}")
@Component
public class WebSocketServer {

    /**
     * 记录当前在线连接数
     */
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    /**
     * 存放所有在线的客户端
     */
    private static final Map<String, Session> CLIENTS = new ConcurrentHashMap<>();
    

    /**
     * 存放最近信息
     */
    private static final Queue<String> MESSAGES = new LinkedList<>();

    /**
     * 最近信息容量
     */
    private static final int ALARM_CAPACITY = 1;

//    private static AssessmentRecordMapper assessmentRecordMapper;

   
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "authorization") String authorization) {
        if (null == authorization) {
            throw new RuntimeException("未验证");
        }

        ONLINE_COUNT.incrementAndGet(); // 在线数加1
        CLIENTS.put(session.getId(), session);
        log.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), ONLINE_COUNT.get());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        ONLINE_COUNT.decrementAndGet(); // 在线数减1
        CLIENTS.remove(session.getId());
        log.info("有一连接关闭：{}，当前在线人数为：{}", session.getId(), ONLINE_COUNT.get());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
       //做自己的业务
        System.out.println("接收 " + session.getId() + " 会话发送消息，message = " + message);
        sendMessage(session.getId(), message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误" + (null == session ? "" : ",客户端id为: " + session.getId()));
        error.printStackTrace();
    }

    /**
     * 发送消息给指定客户端
     */
    public static void sendMessage(String sessionId, String message) {
        if (sessionId == null || message == null) {
            return;
        }
        synchronized (IdLockUtil.getLock("WebSocket:send" + sessionId)) {
            log.info("服务端给客户端[{}]发送消息", sessionId);
            try {
                Session session = CLIENTS.get(sessionId);
                if (ObjectUtils.isNotNull(session)) {
                    session.getBasicRemote().sendText(message);
                }
            } catch (Exception e) {
                log.error("服务端发送消息给客户端[{}]失败：{}", sessionId, e);
            }
        }
    }

    public static void sendMessage(String sessionId, Object message) {
        sendMessage(sessionId, JSON.toJSONString(message));
    }

    /**
     * 发送消息给全部客户端
     */
    public static void sendMessageToAll(String message) {
        //群发消息
       CLIENTS.forEach((sessionId, client) -> sendMessage(sessionId, message));
    }
}

