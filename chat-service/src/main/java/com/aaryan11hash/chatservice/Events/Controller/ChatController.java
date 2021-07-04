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
import lombok.RequiredArgsConstructor;
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


    @MessageMapping("/chat/simple-text")
    public void processMessage(@Payload ChatMessageEvent chatMessageEvent) {
        var chatId = chatRoomService
                .getChatId(chatMessageEvent.getSenderId(), chatMessageEvent.getRecipientId(), true);
        chatMessageEvent.setChatId(chatId.get());


        ChatMessage chatMessageDto1 = chatMessageService.save(Converter.chatMessageEventToDomain(chatMessageEvent));

        //todo this piece of code will be removed from here,as multiple instances of this project will run,
        //todo we need to trigger this function in the redis sub class function to make this service fully scalable
        messagingTemplate.convertAndSendToUser(
                chatMessageDto1.getRecipientId(),"/queue/messages",

                ChatNotificationDto.builder()
                        .id(chatMessageDto1.getId())
                        .senderId(chatMessageDto1.getSenderId())
                        .senderName(chatMessageDto1.getSenderName())
                        .build()
        );

        //todo this part will be executed on side threads
        redisChatMessagePublisher.publish(chatMessageEvent.toString());

    }

    @MessageMapping("/chat/blob")
    public void processBlobFile(@Payload BlobFileMessageEvent blobFileMessageEvent){

        var chatId = chatRoomService
                .getChatId(blobFileMessageEvent.getSenderId(), blobFileMessageEvent.getRecipientId(), true);

        blobFileMessageEvent.setChatId(chatId.get());

        //todo blob link for this domain object is empty for now,this will be updated once the chitthi service gets the blob event obj and saves it on s3 and generates a link to access the data
        BlobFileMessage blobFileMessage = blobMessageService.save(Converter.blobFileMessageEventToDomain(blobFileMessageEvent));

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

    @MessageMapping("/chat/test")
    public void testMessage(@Payload String data){
        log.info(data);
    }


}
