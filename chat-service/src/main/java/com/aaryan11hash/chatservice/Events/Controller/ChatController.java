package com.aaryan11hash.chatservice.Events.Controller;

import com.aaryan11hash.chatservice.AppUtils.Converter;
import com.aaryan11hash.chatservice.Events.Models.BlobFileMessageEvent;
import com.aaryan11hash.chatservice.Events.Models.ChatMessageEvent;
import com.aaryan11hash.chatservice.Events.PubSubService.RedisChatMessagePublisher;
import com.aaryan11hash.chatservice.Events.RabbitQueueService.RabbitMqPublisher;
import com.aaryan11hash.chatservice.Web.Domain.BlobFileMessage;
import com.aaryan11hash.chatservice.Web.Domain.ChatMessage;
import com.aaryan11hash.chatservice.Web.Model.ChatNotificationDto;
import com.aaryan11hash.chatservice.Web.Service.BlobMessageService;
import com.aaryan11hash.chatservice.Web.Service.ChatMessageService;
import com.aaryan11hash.chatservice.Web.Service.ChatRoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {


    private final SimpMessagingTemplate messagingTemplate;

    private final ChatMessageService chatMessageService;

    private final ChatRoomService chatRoomService;

    private final BlobMessageService blobMessageService;

    private final RabbitMqPublisher rabbitMqPublisher;

    private final RedisChatMessagePublisher redisChatMessagePublisher;


    @SneakyThrows
    @MessageMapping("/chat/simple-text")
    public void processMessage(@Payload ChatMessageEvent chatMessageEvent) throws JsonProcessingException {

        var chatId = chatRoomService
                .getChatId(chatMessageEvent.getSenderId(), chatMessageEvent.getRecipientId(), true);
        chatMessageEvent.setChatId(chatId.get());


       chatMessageService.save(Converter.chatMessageEventToDomain(chatMessageEvent));

       //todo this part will be executed on side threads
        redisChatMessagePublisher.publish(new ObjectMapper().writeValueAsString(chatMessageEvent));

    }

    @MessageMapping("/chat/blob")
    public void processBlobFile(@Payload BlobFileMessageEvent blobFileMessageEvent){

        log.info(blobFileMessageEvent.toString());

        var chatId = chatRoomService
                .getChatId(blobFileMessageEvent.getSenderId(), blobFileMessageEvent.getRecipientId(), true);

        blobFileMessageEvent.setChatId(chatId.get());
        String blobFileUrl = String.format("%s|%s",blobFileMessageEvent.getChatId(),blobFileMessageEvent.getTimestamp().toString());
        BlobFileMessage blobFileMessage = blobMessageService.save(Converter.blobFileMessageEventToDomain(blobFileMessageEvent,blobFileUrl));

        //todo this piece of code will be removed from here,as multiple instances of this project will run,
        //todo we need to trigger this function in the redis sub class function to make this service fully scalable
        messagingTemplate.convertAndSendToUser(
                blobFileMessageEvent.getRecipientId(),"/queue/messages",

                ChatNotificationDto.builder()
                        .id(blobFileMessageEvent.getId())
                        .senderId(blobFileMessageEvent.getSenderId())
                        .senderName(blobFileMessageEvent.getSenderName())
                        .multipartFile(blobFileMessageEvent.getBlob())
                        .build()
        );

        //todo this part will be executed on side threads
       rabbitMqPublisher.publishBlobForProcess(blobFileMessageEvent);



    }



}
