package com.aaryan11hash.chatservice.Events.Controller;

import com.aaryan11hash.chatservice.Web.Model.ChatMessage;
import com.aaryan11hash.chatservice.Web.Model.ChatNotification;
import com.aaryan11hash.chatservice.Web.Service.ChatMessageService;
import com.aaryan11hash.chatservice.Web.Service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());


        chatMessageService.save(chatMessage)
                .map(chatMessage1->{
                    messagingTemplate.convertAndSendToUser(
                            chatMessage.getRecipientId(),"/queue/messages",
                            new ChatNotification(
                                    chatMessage1.getId(),
                                    chatMessage1.getSenderId(),
                                    chatMessage1.getSenderName()));
                        return chatMessage1;
                });
    }


}
