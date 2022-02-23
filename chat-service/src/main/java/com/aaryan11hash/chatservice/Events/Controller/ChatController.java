package com.aaryan11hash.chatservice.Events.Controller;

import com.aaryan11hash.chatservice.AppUtils.Converter;
import com.aaryan11hash.chatservice.Events.Models.BlobFileMessageEvent;
import com.aaryan11hash.chatservice.Events.Models.ChatMessageEvent;
import com.aaryan11hash.chatservice.Events.Models.MessagingEvent;
import com.aaryan11hash.chatservice.Events.Models.RegisteryEvent;
import com.aaryan11hash.chatservice.Events.PubSubService.MessagePublisher;
import com.aaryan11hash.chatservice.Events.PubSubService.RedisChatMessagePublisher;
import com.aaryan11hash.chatservice.Events.RabbitQueueService.RabbitMqPublisher;
import com.aaryan11hash.chatservice.Events.Service.ChatControllerService;
import com.aaryan11hash.chatservice.Web.Domain.BlobFileMessage;
import com.aaryan11hash.chatservice.Web.Domain.ChatMessage;
import com.aaryan11hash.chatservice.Web.Enums.MessageStatus;
import com.aaryan11hash.chatservice.Web.Model.ChatMessageDto;
import com.aaryan11hash.chatservice.Web.Model.ChatNotificationDto;
import com.aaryan11hash.chatservice.Web.Service.BlobMessageService;
import com.aaryan11hash.chatservice.Web.Service.ChatMessageService;
import com.aaryan11hash.chatservice.Web.Service.ChatRoomService;
import com.aaryan11hash.chatservice.Web.Utils.BeanConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Slf4j
@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class ChatController {


    private final ChatMessageService chatMessageService;

    private final ChatControllerService chatControllerService;

    private final Environment environment;

    private final ExecutorService inputOutputExec;

    @MessageMapping("/chat/registry")
    public void registerSocketToInstance(@Payload RegisteryEvent registeryEvent){

        System.out.println("REGISTRY EVENT =====>"+ registeryEvent);


        //todo need to solve this problem || major hurdle in making the infrastructure kubernetes ready
        //todo for now localhost will work

        CompletableFuture.supplyAsync(()->{
            registeryEvent.setSpring_boot_port_number(environment.getProperty("server.port"));
            registeryEvent.setSpring_boot_app_Ip("localhost");
            return registeryEvent;
        })
                .thenAcceptAsync(chatControllerService::RegisterClientConnDetails,inputOutputExec);



        //in this we send the details to the service registry which keeps track of which client is connected to which instance of the application
        //this helps in solving the problem of connection isolation caused by websockets over horizontal scaling
        /*
        * structure of the schema is as follows
        * {
        * userName :
        * email :
        * clientIp :
        * spring-boot-app-Ip :
        * spring-boot-port-number :
        * }
        * ENDPOINT TO HIT IN SERVICE REGISTRY TO FIND WEATHER THE RECEIVING USER IS CONNECTED TO THE SAME INSTANC EOR NOT
        * /registry/search/{userName}/{email}
        *
        * and then check internally if the spring-boot-app-Ip and spring-boot-port-number matches, if true : send that message to the client using uniConnectionId as prefix in the stompTemplate url
        * else, send the message via REST to the right spring boot app as :- http://{spring-boot-app-ip}:{spring-boot-port-number}/send/(textMessage | blobMessage)
        * */
    }


    @SneakyThrows
    @MessageMapping("/chat/simple-text")
    public void processMessage(@Payload ChatMessageEvent chatMessageEvent){


        CompletableFuture.supplyAsync(() -> chatControllerService.createChatRoomIfNotExist(chatMessageEvent,true),inputOutputExec)
                .thenApply(chatRoomId -> {
                    chatMessageEvent.setChatId(chatRoomId);
                    return chatMessageEvent;
                })
                .thenApply(chatControllerService::resolveChatDataDestination)
                .thenApply(BeanConverter::chatMessageEventToDomain)
                .thenAccept(chatMessageService::save);




    }

    @SneakyThrows
    @MessageMapping("/chat/blob")
    public void processBlobFile(@Payload BlobFileMessageEvent blobFileMessageEvent){

//        inputOutputExec.execute(()->{
//
//            log.info(blobFileMessageEvent.toString());
//
//            var chatId = chatRoomService
//                    .getChatId(blobFileMessageEvent.getSenderId(), blobFileMessageEvent.getRecipientId(), true);
//
//            blobFileMessageEvent.setChatId(chatId.get());
//            String blobFileUrl = String.format("%s|%s",blobFileMessageEvent.getChatId(),blobFileMessageEvent.getTimestamp().toString());
//
//
//            BlobFileMessage blobFileMessage = blobMessageService.save(Converter.blobFileMessageEventToDomain(blobFileMessageEvent,blobFileUrl));
//            rabbitMqPublisher.publishBlobForProcess(blobFileMessageEvent);
//
//            try {
//                redisChatMessagePublisher.publish(new ObjectMapper().writeValueAsString(MessagingEvent.builder().blobFileMessageEvent(blobFileMessageEvent).build()));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//
//
//        });


    }







}
