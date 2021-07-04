package com.aaryan11hash.chatservice.Events.PubSubService;

import com.aaryan11hash.chatservice.Events.Models.ChatMessageEvent;
import com.aaryan11hash.chatservice.Web.Model.ChatNotificationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisMessageSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] bytes) {

        //todo there is some bug regarding the object mapper,everytime a new event obj is sent we need to instanciate a new object mapper for the same process.
        ChatMessageEvent chatMessageEvent = new ObjectMapper().readValue(message.toString(), ChatMessageEvent.class);

        log.info(chatMessageEvent.toString());

        simpMessagingTemplate.convertAndSendToUser(
                chatMessageEvent.getRecipientId(),"/queue/messages",
                ChatNotificationDto.builder()
                        .id(chatMessageEvent.getId())
                        .senderId(chatMessageEvent.getSenderId())
                        .senderName(chatMessageEvent.getSenderName())
                        .build()
        );
    }
}
