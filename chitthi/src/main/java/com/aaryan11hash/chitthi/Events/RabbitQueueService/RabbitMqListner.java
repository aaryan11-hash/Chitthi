package com.aaryan11hash.chitthi.Events.RabbitQueueService;


import com.aaryan11hash.chitthi.Config.RabbitMqConfig;
import com.aaryan11hash.chitthi.Events.Models.BlobFileMessageEvent;
import com.aaryan11hash.chitthi.Events.Models.TestMessage;
import com.aaryan11hash.chitthi.Services.BlobStorageService;
import com.aaryan11hash.chitthi.Services.MailingService;
import com.aaryan11hash.chitthi.Web.Model.NotificationEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

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


    @RabbitListener(queues = RabbitMqConfig.NOTIFICATION_EVENT)
        public void listen3(NotificationEmail testMessage){
        log.info("at QUEUE3: "+ testMessage);
    }
}
