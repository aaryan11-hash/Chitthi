package com.aaryan11hash.chatservice.Events.Controller;

import com.aaryan11hash.chatservice.Events.RabbitQueueService.RabbitMqPublisher;
import com.aaryan11hash.chatservice.Events.RabbitQueueService.RabbitMqPublisherImpl;
import com.aaryan11hash.chatservice.Web.Model.BlobFileMessage;
import com.aaryan11hash.chatservice.Web.Model.ChatMessage;
import com.aaryan11hash.chatservice.Web.Model.ChatNotification;
import com.aaryan11hash.chatservice.Web.Service.ChatMessageService;
import com.aaryan11hash.chatservice.Web.Service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {


    private final SimpMessagingTemplate messagingTemplate;

    private final ChatMessageService chatMessageService;

    private final ChatRoomService chatRoomService;

    private final RabbitMqPublisher rabbitMqPublisher;

    @MessageMapping("/chat/simple-text")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());


        ChatMessage chatMessage1 = chatMessageService.save(chatMessage);

        //todo this piece of code will be removed from here,as multiple instances of this project will run,
        //todo we need to trigger this function in the redis sub class function to make this service fully scalable
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),"/queue/messages",

                ChatNotification.builder()
                        .id(chatMessage1.getId())
                        .senderId(chatMessage1.getSenderId())
                        .senderName(chatMessage1.getSenderName())
                        .build()
        );



    }

    @MessageMapping("/chat/blob")
    public void processBlobFile(@Payload BlobFileMessage blobFileMessage){

        var chatId = chatRoomService
                .getChatId(blobFileMessage.getSenderId(), blobFileMessage.getRecipientId(), true);

        blobFileMessage.setChatId(chatId.get());

        //todo this piece of code will be removed from here,as multiple instances of this project will run,
        //todo we need to trigger this function in the redis sub class function to make this service fully scalable
        messagingTemplate.convertAndSendToUser(
                blobFileMessage.getRecipientId(),"/queue/messages",

                ChatNotification.builder()
                        .id(blobFileMessage.getId())
                        .senderId(blobFileMessage.getSenderId())
                        .senderName(blobFileMessage.getSenderName())
                        .multipartFile(blobFileMessage.getBlob())
                        .build()
        );

       rabbitMqPublisher.publishBlobForProcess(blobFileMessage);



    }

    @MessageMapping("/chat/test")
    public void testMessage(@Payload String data){
        log.info(data);
    }


}
