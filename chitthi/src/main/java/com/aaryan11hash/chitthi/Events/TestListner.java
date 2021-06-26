package com.aaryan11hash.chitthi.Events;

import com.aaryan11hash.chitthi.Config.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TestListner {

    @RabbitListener(queues = RabbitMqConfig.TEST_QUEUE)
    public void listen(TestMessage testMessage){
        System.out.println(testMessage);
    }


}
