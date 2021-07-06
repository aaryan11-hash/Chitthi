package com.aaryan11hash.chatservice.BootStrap;

import com.aaryan11hash.chatservice.Repositories.TestDomainRepository;
import com.aaryan11hash.chatservice.Web.Domain.TestDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;


@RequiredArgsConstructor
@Slf4j
@Component
public class BootStrapRunner implements CommandLineRunner {


    static final String WEBSOCKET_URI = "http://localhost:8001/register-socket";
    static final String WEBSOCKET_TOPIC = "/app/chat/simple-text";





    @Override
    public void run(String... args) throws Exception {
//        WebSocketClient webSocketClient = new StandardWebSocketClient();
//        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
//        StompSessionHandler sessionHandler = new MyStompSessionHandler();
//        log.info("connecting");
//        stompClient.setMessageConverter(new StringMessageConverter());
//        stompClient.connect(WEBSOCKET_URI,sessionHandler);
//        log.info("connected"+ stompClient.isRunning());

    }
}
