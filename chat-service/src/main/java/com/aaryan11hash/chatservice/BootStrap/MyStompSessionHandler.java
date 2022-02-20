package com.aaryan11hash.chatservice.BootStrap;

import com.aaryan11hash.chatservice.Events.Models.ChatMessageEvent;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class MyStompSessionHandler implements HandshakeInterceptor {

//    @Override
//    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
//
//
//    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map attributes) throws Exception {


        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();

            attributes.put("sessionId", session.getId());



            System.out.println("BEFORE HANDHAKE========>");
            System.out.println( servletRequest.getServletRequest().getHeaderNames());
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(response.getHeaders().toString());
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(wsHandler);
        }
        return true;
    }


    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

        System.out.println("AFTER HANDHAKE========>");
        System.out.println(serverHttpRequest.getHeaders().toString());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(serverHttpResponse.getHeaders().toString());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(webSocketHandler.toString());
    }
}
