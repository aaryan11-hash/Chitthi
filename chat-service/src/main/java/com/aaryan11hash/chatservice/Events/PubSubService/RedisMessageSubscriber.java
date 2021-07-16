package com.aaryan11hash.chatservice.Events.PubSubService;

import com.aaryan11hash.chatservice.Events.Models.ChatMessageEvent;
import com.aaryan11hash.chatservice.Events.Models.MessagingEvent;
import com.aaryan11hash.chatservice.Web.Model.ChatNotificationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class RedisMessageSubscriber implements MessageListener {

    private SimpMessagingTemplate simpMessagingTemplate;


    @SneakyThrows({NullPointerException.class, JsonProcessingException.class})
    @Override
    public void onMessage(Message message, byte[] bytes) {

        //todo there is some bug regarding the object mapper,everytime a new event obj is sent we need to instanciate a new object mapper for the same process.
        MessagingEvent messagingEvent = new ObjectMapper().readValue(message.toString(), MessagingEvent.class);

        log.info(messagingEvent.toString());

//        if (messagingEvent.getChatMessageEvent() != null) {
//            simpMessagingTemplate.convertAndSendToUser(
//                    messagingEvent.getChatMessageEvent().getRecipientId(), "/queue/messages",
//                    ChatNotificationDto.builder()
//                            .id(messagingEvent.getChatMessageEvent().getId())
//                            .senderId(messagingEvent.getChatMessageEvent().getSenderId())
//                            .senderName(messagingEvent.getChatMessageEvent().getSenderName())
//                            .build()
//            );
//        }
//
//        else if(messagingEvent.getBlobFileMessageEvent()!=null){
//            simpMessagingTemplate.convertAndSendToUser(
//                    messagingEvent.getBlobFileMessageEvent().getRecipientId(),"/queue/messages",
//
//                    ChatNotificationDto.builder()
//                            .id(messagingEvent.getBlobFileMessageEvent().getId())
//                            .senderId(messagingEvent.getBlobFileMessageEvent().getSenderId())
//                            .senderName(messagingEvent.getBlobFileMessageEvent().getSenderName())
//                            .multipartFile(messagingEvent.getBlobFileMessageEvent().getBlob())
//                            .build()
//            );
//        }
    }
}
