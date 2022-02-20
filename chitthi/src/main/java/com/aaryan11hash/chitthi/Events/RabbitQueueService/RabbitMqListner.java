package com.aaryan11hash.chitthi.Events.RabbitQueueService;



import com.aaryan11hash.chitthi.Config.RabbitMqConfig;
import com.aaryan11hash.chitthi.Events.models.BlobFileMessageEvent;
import com.aaryan11hash.chitthi.Web.model.NotificationEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.aaryan11hash.chitthi.services.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMqListner {

    private final BlobStorageService blobStorageService;

    public final MailingService mailingService;

    @RabbitListener(queues = RabbitMqConfig.BLOB_PROCESS_QUEUE)
    public void listen1(BlobFileMessageEvent blobFileMessageEvent){

        log.info("received message "+blobFileMessageEvent);

        blobStorageService.uploadBlobFile(blobFileMessageEvent);
    }

//    @RabbitListener(queues = RabbitMqConfig.BLOB_PROCESS_QUEUE_OUTPUT)
//    public void listen2(TestMessage testMessage){
//        log.info("at QUEUE2: "+ testMessage);
//    }

    @RabbitListener(queues = RabbitMqConfig.NOTIFICATION_EVENT)
    public void listen3(NotificationEmail notificationEmail){
        log.info("at QUEUE3: "+ notificationEmail);
    }
}
