package com.aaryan11hash.chatservice.Config;

import com.aaryan11hash.chatservice.BootStrap.MyStompSessionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;


@SpringBootTest
@ActiveProfiles("test")
class WebSocketConfigTest {

    static final String WEBSOCKET_URI = "ws://localhost:8001/register-socket";
    static final String WEBSOCKET_TOPIC = "/app/chat/simple-text";

    BlockingQueue<String> blockingQueue;
    WebSocketClient webSocketClient = new StandardWebSocketClient();
    WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
    StompSessionHandler sessionHandler = new MyStompSessionHandler();

    @PostConstruct
    public void postConstruct(){


    }

    @Test
    public void testConnection(){
        stompClient.setMessageConverter(new StringMessageConverter());
        stompClient.connect(WEBSOCKET_URI,sessionHandler);
    }
}