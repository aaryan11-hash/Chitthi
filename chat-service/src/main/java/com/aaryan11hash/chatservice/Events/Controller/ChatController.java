package com.aaryan11hash.chatservice.Events.Controller;

import com.aaryan11hash.chatservice.AppUtils.Converter;
import com.aaryan11hash.chatservice.Events.Models.BlobFileMessageEvent;
import com.aaryan11hash.chatservice.Events.Models.ChatMessageEvent;
import com.aaryan11hash.chatservice.Events.Models.MessagingEvent;
import com.aaryan11hash.chatservice.Events.PubSubService.MessagePublisher;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.concurrent.ExecutorService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MessagePublisher redisChatMessagePublisher;

    private final ChatMessageService chatMessageService;

    private final ChatRoomService chatRoomService;

    private final BlobMessageService blobMessageService;

    private final RabbitMqPublisher rabbitMqPublisher;

    private final ExecutorService inputOutputExec;

    private final SimpMessagingTemplate simpMessagingTemplate;


    @SneakyThrows
    @MessageMapping("/topic/chat/simple-text")
    public void processMessage(@Payload ChatMessageEvent chatMessageEvent){

        var chatId = chatRoomService
                .getChatId(chatMessageEvent.getSenderId(), chatMessageEvent.getRecipientId(), true);
        chatMessageEvent.setChatId(chatId.get());


        inputOutputExec.execute(()->{
            chatMessageService.save(Converter.chatMessageEventToDomain(chatMessageEvent));

            try {
                redisChatMessagePublisher.publish(new ObjectMapper().writeValueAsString(MessagingEvent.builder().chatMessageEvent(chatMessageEvent).build()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        });



    }

    @SneakyThrows
    @MessageMapping("/topic/chat/blob")
    public void processBlobFile(@Payload BlobFileMessageEvent blobFileMessageEvent){

        log.info(blobFileMessageEvent.toString());

        var chatId = chatRoomService
                .getChatId(blobFileMessageEvent.getSenderId(), blobFileMessageEvent.getRecipientId(), true);

        blobFileMessageEvent.setChatId(chatId.get());
        String blobFileUrl = String.format("%s|%s",blobFileMessageEvent.getChatId(),blobFileMessageEvent.getTimestamp().toString());

        inputOutputExec.execute(()->{

            BlobFileMessage blobFileMessage = blobMessageService.save(Converter.blobFileMessageEventToDomain(blobFileMessageEvent,blobFileUrl));
            rabbitMqPublisher.publishBlobForProcess(blobFileMessageEvent);

            try {
                redisChatMessagePublisher.publish(new ObjectMapper().writeValueAsString(MessagingEvent.builder().blobFileMessageEvent(blobFileMessageEvent).build()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


        });


    }

    @SneakyThrows
    @MessageMapping("/test")
    public void testEndPoint(@Payload BlobFileMessageEvent event){
        log.info(event.toString());
        redisChatMessagePublisher.publish(new ObjectMapper().writeValueAsString(MessagingEvent.builder().blobFileMessageEvent(event).build()));

    }



}
