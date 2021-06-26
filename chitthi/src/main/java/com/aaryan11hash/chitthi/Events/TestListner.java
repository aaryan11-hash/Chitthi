package com.aaryan11hash.chitthi.Events;

import com.aaryan11hash.chitthi.Config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestListner {

    @RabbitListener(queues = RabbitMqConfig.TEST_QUEUE1)
    public void listen1(TestMessage testMessage){
        log.info("at QUEUE1: "+ testMessage);
    }

    @RabbitListener(queues = RabbitMqConfig.TEST_QUEUE2)
    public void listen2(TestMessage testMessage){
        log.info("at QUEUE2: "+ testMessage);
    }

    @RabbitListener(queues = RabbitMqConfig.TEST_QUEUE3)
    public void listen3(TestMessage testMessage){
        log.info("at QUEUE3: "+ testMessage);
    }

}
