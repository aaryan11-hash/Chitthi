package com.aaryan11hash.chitthi.Events.RabbitQueueService;


import com.aaryan11hash.chitthi.Config.RabbitMqConfig;
import com.aaryan11hash.chitthi.Web.Model.BlobFileMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqPublisherImpl implements RabbitMqPublisher{

    private final RabbitTemplate rabbitTemplate;




}
