package com.aaryan11hash.chitthi.Web.Contoller;


import com.aaryan11hash.chitthi.Config.RabbitMqConfig;
import com.aaryan11hash.chitthi.Events.TestMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    RabbitTemplate template;

    @PostMapping("/check")
    public Mono<ResponseEntity<String>> sendTestResponse(){
        template.convertAndSend(RabbitMqConfig.TEST_EXCHANGE,RabbitMqConfig.TEST_ROUTE,TestMessage.builder().testName("test").build());
        return Mono.just(ResponseEntity.ok().body("DONE"));
    }
}
