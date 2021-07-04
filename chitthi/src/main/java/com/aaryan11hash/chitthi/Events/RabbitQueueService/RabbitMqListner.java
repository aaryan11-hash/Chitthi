package com.aaryan11hash.chitthi.Events.RabbitQueueService;


import com.aaryan11hash.chitthi.Config.RabbitMqConfig;
import com.aaryan11hash.chitthi.Events.Models.BlobFileMessageEvent;
import com.aaryan11hash.chitthi.Events.Models.TestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqListner {

    @RabbitListener(queues = RabbitMqConfig.BLOB_PROCESS_QUEUE)
    public void listen1(BlobFileMessageEvent blobFileMessageEvent){
        log.info("at QUEUE1: "+ blobFileMessageEvent);
    }

//    @RabbitListener(queues = RabbitMqConfig.BLOB_PROCESS_QUEUE_OUTPUT)
//    public void listen2(TestMessage testMessage){
//        log.info("at QUEUE2: "+ testMessage);
//    }

    @RabbitListener(queues = RabbitMqConfig.NOTIFICATION_EVENT)
    public void listen3(TestMessage testMessage){
        log.info("at QUEUE3: "+ testMessage);
    }
}
