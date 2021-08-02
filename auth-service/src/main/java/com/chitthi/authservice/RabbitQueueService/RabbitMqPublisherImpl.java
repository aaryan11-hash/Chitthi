package com.chitthi.authservice.RabbitQueueService;


import com.chitthi.authservice.config.RabbitMqConfig;
import com.chitthi.authservice.model.NotificationEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqPublisherImpl implements RabbitMqPublisher{

    private final RabbitTemplate rabbitTemplate;

    @Async
    @Override
    public void userSignUpMailEvent(NotificationEmail notificationEmail){
        rabbitTemplate.convertAndSend(RabbitMqConfig.TEST_EXCHANGE,RabbitMqConfig.TEST_ROUTE3,notificationEmail);
    }


}
