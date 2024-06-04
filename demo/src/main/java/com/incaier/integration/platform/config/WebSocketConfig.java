package com.incaier.integration.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.WEBSOCKET_PROTOCOL;

@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator {
 
    /**
     * 这个bean的注册,用于扫描带有@ServerEndpoint的注解成为websocket  ,如果你使用外置的tomcat就            
        不需要该配置文件
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 建立握手时，连接前的操作
     * 请求头在通信子协议 Sec-WebSocket-Protocol 里携带token
     * 前端通过 WebSocket 的通信子协议携带token发送给后端，需要获取到该token就能获取用户信息
     * WebSocket 携带 token
     * 1. https://blog.csdn.net/oNew_Lifeo/article/details/130003676
     * 2. https://blog.csdn.net/qq_40951951/article/details/129328811?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-1-129328811-blog-130003676.235%5Ev43%5Epc_blog_bottom_relevance_base8&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-1-129328811-blog-130003676.235%5Ev43%5Epc_blog_bottom_relevance_base8&utm_relevant_index=2
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 这个userProperties 可以通过 session.getUserProperties()获取
        final Map<String, Object> userProperties = sec.getUserProperties();
        Map<String, List<String>> headers = request.getHeaders();
        List<String> protocol = headers.get(WEBSOCKET_PROTOCOL);
        // 存放自己想要的header信息
        if(protocol != null){
            userProperties.put(String.valueOf(WEBSOCKET_PROTOCOL), protocol.get(0));
        }
    }

    /**
     * 初始化端点对象,也就是被 @ServerEndpoint 所标注的对象
     */
    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return super.getEndpointInstance(clazz);
    }
}
