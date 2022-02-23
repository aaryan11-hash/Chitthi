package com.aaryan11hash.chatservice.Events.Service;


import com.aaryan11hash.chatservice.Events.Models.ChatMessageEvent;
import com.aaryan11hash.chatservice.Events.Models.RegisteryEvent;
import com.aaryan11hash.chatservice.Web.Enums.MessageStatus;
import com.aaryan11hash.chatservice.Web.Service.ChatRoomService;
import com.aaryan11hash.chatservice.Web.Utils.BeanConverter;
import com.aaryan11hash.chatservice.exception.ChatRoomNotBuilt;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ChatControllerService {

    private final ChatRoomService chatRoomService;
    private final RestTemplate restTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final Environment environment;




    public void RegisterClientConnDetails(RegisteryEvent registeryEvent){

        //todo make this http status based operation to check successfull connection
        restTemplate.postForEntity(URI.create("http://localhost:8761/registry/save/userDetails").toString(), registeryEvent, null);
    }

    public String createChatRoomIfNotExist(ChatMessageEvent chatMessageEvent, boolean createIfNotExist) {

        long startTime = System.currentTimeMillis();

        var chatId = chatRoomService
                .getChatId(chatMessageEvent.getSenderId(), chatMessageEvent.getRecipientId(), createIfNotExist);

        long endTime = System.currentTimeMillis();

        System.out.println("TOTAL TIME TO PROCESS A FIRST IO-OP ::::: " + (endTime - startTime));

        return chatId.orElseThrow(() -> new ChatRoomNotBuilt("ERROR IN BUILDING CHAT ROOM"));
    }

    public ChatMessageEvent resolveChatDataDestination(ChatMessageEvent chatMessageEvent) {


        System.out.println("INSIDE 2ND COMPLETE BLOCK  " + chatMessageEvent);

        long startTime = System.currentTimeMillis();

        //todo DELIVERED || RECEIVED feature broken because this code is not behaving the way it is supposed to
        ResponseEntity<RegisteryEvent> response1 = restTemplate.exchange(URI.create("http://localhost:8761/registry/search/" + chatMessageEvent.getRecipientName() + "/" + chatMessageEvent.getRecipientId()).toString()
                , HttpMethod.GET, null, new ParameterizedTypeReference<RegisteryEvent>() {
                });


        System.out.println("USER DATA FOR CLIENT ::: " + response1.getBody());

        long endTime = System.currentTimeMillis();

        System.out.println("TOTAL TIME TO PROCESS A SECOND IO-OP ::::: " + (endTime - startTime));

        chatMessageEvent.setStatus(response1.getStatusCode().value() == 200 ? MessageStatus.RECEIVED : MessageStatus.DELIVERED);

        return chatMessageEvent.getStatus().compareTo(MessageStatus.RECEIVED) == 0 ? resolveMessageRouteOnStatus(response1, chatMessageEvent) : chatMessageEvent;
    }

    private ChatMessageEvent resolveMessageRouteOnStatus(ResponseEntity<RegisteryEvent> response1, ChatMessageEvent chatMessageEvent) {

        long startTime = System.currentTimeMillis();

        if (response1.getStatusCode().value() == 200) {

            if (response1.getBody().getSpring_boot_port_number().matches(environment.getProperty("server.port")) == false) {

                System.out.println("INSIDE SENDING DATA TO ANOUTHER POD::::::::");
                System.out.println("URL IS ::::: " + URI.create("http://" + response1.getBody().getSpring_boot_app_Ip() + ":" + response1.getBody().getSpring_boot_port_number() + "/stomp/redirect/instance"));
                ResponseEntity response2 = restTemplate.exchange(URI.create("http://" + response1.getBody().getSpring_boot_app_Ip() + ":" + response1.getBody().getSpring_boot_port_number() + "/stomp/redirect/instance").toString(),
                        HttpMethod.POST,
                        new HttpEntity(BeanConverter.chatMessageEventToDto(chatMessageEvent)),
                        new ParameterizedTypeReference<Object>() {
                        }
                );

                //todo
                //maybe change this part and put it in stomp redirect controller
                chatMessageEvent.setStatus(response2.getStatusCode().value() == 200 ? MessageStatus.RECEIVED : MessageStatus.DELIVERED);

            } else {
                simpMessagingTemplate.convertAndSend("/queue/" + chatMessageEvent.getRecipientId() + chatMessageEvent.getRecipientName() + "_textMessage", chatMessageEvent);
                chatMessageEvent.setStatus(MessageStatus.RECEIVED);
            }

        }

        long endTime = System.currentTimeMillis();

        System.out.println("TOTAL TIME TO PROCESS A THIRD IO-OP ::::: " + (endTime - startTime));

        return chatMessageEvent;
    }


}
