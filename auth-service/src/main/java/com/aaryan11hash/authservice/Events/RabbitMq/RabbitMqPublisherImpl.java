package com.aaryan11hash.authservice.Events.RabbitMq;


import com.aaryan11hash.authservice.Config.RabbitMqConfig;
import com.aaryan11hash.authservice.model.NotificationEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqPublisherImpl implements RabbitMqPublisher {

    private final RabbitTemplate rabbitTemplate;


    @Override
    public void publishForEmailVerification(NotificationEmail notificationEmail) {

        rabbitTemplate.convertAndSend(RabbitMqConfig.NOTIFICATION_EVENT,RabbitMqConfig.NOTIFICATION_EVENT_ROUTE,notificationEmail);

    }
}
