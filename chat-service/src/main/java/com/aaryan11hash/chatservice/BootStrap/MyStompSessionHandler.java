package com.aaryan11hash.chatservice.BootStrap;

import com.aaryan11hash.chatservice.Events.Models.ChatMessageEvent;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {

        session.send("/app/chat/simple-text", ChatMessageEvent.builder().chatId("12").content("sample-content").build());
    }
}
