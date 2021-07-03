package com.aaryan11hash.chatservice.Events.RabbitQueueService;

import com.aaryan11hash.chatservice.Config.RabbitMqConfig;
import com.aaryan11hash.chatservice.Events.Models.BlobFileMessageEvent;
import com.aaryan11hash.chatservice.Events.Models.TestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqListner {

//    @RabbitListener(queues = RabbitMqConfig.BLOB_PROCESS_QUEUE)
//    public void listen1(TestMessage testMessage){
//        log.info("at QUEUE1: "+ testMessage);
//    }

    //todo need to make a new event model for this event listner..
    @RabbitListener(queues = RabbitMqConfig.BLOB_PROCESS_QUEUE_OUTPUT)
    public void listen2(BlobFileMessageEvent blobFileMessageEvent){
        log.info("at QUEUE2: "+ blobFileMessageEvent);
    }

    @RabbitListener(queues = RabbitMqConfig.TEST_QUEUE3)
    public void listen3(TestMessage testMessage){
        log.info("at QUEUE3: "+ testMessage);
    }
}
