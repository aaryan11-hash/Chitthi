package com.aaryan11hash.chitthi.Events.RabbitQueueService;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqPublisherImpl{

    private final RabbitTemplate rabbitTemplate;




}
