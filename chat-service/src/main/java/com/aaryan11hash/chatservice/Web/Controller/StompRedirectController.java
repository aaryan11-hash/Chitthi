package com.aaryan11hash.chatservice.Web.Controller;

import com.aaryan11hash.chatservice.Web.Model.ChatMessageDto;
import com.aaryan11hash.chatservice.Web.Utils.BeanConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stomp")
public class StompRedirectController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/redirect/instance")
    public ResponseEntity<?> redirectToThisApp(@RequestBody ChatMessageDto chatMessageDto){

        System.out.println("INSIDE THE CONTEXT-SWITCH ::::: "+ chatMessageDto);
        simpMessagingTemplate.convertAndSend("/queue/"+chatMessageDto.getRecipientId()+chatMessageDto.getRecipientName()+"_textMessage", chatMessageDto);

        return ResponseEntity.ok().build();
    }

}
