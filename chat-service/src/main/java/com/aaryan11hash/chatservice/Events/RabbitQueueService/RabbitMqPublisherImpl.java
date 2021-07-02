package com.aaryan11hash.chatservice.Events.RabbitQueueService;

import com.aaryan11hash.chatservice.Config.RabbitMqConfig;
import com.aaryan11hash.chatservice.Web.Model.BlobFileMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RabbitMqPublisherImpl implements RabbitMqPublisher{

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publishBlobForProcess(BlobFileMessage blobFileMessage) {

        //todo make this run inside Mono wrapper obj
        rabbitTemplate.convertAndSend(RabbitMqConfig.TEST_EXCHANGE,RabbitMqConfig.TEST_ROUTE1,blobFileMessage);

    }


}
