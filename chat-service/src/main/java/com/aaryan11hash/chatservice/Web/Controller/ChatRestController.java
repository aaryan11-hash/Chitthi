package com.aaryan11hash.chatservice.Web.Controller;


import com.aaryan11hash.chatservice.Repositories.TestDomainRepository;
import com.aaryan11hash.chatservice.Web.Domain.TestDomain;
import com.aaryan11hash.chatservice.Web.Service.ChatMessageService;
import com.aaryan11hash.chatservice.Web.Service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class ChatRestController {

    private final SimpMessagingTemplate messagingTemplate;

    private final ChatMessageService chatMessageService;

    private final ChatRoomService chatRoomService;

    private final TestDomainRepository testDomainRepository;

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public Mono<ResponseEntity<Long>> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return Mono.just(ResponseEntity.ok(chatMessageService.countNewMessages(senderId, recipientId)));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String senderId,
                                                @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable String id) {
        return ResponseEntity
                .ok(chatMessageService.findById(id));
    }

}
