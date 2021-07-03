package com.aaryan11hash.chitthi.Web.Contoller;


import com.aaryan11hash.chitthi.Config.RabbitMqConfig;
import com.aaryan11hash.chitthi.Events.Models.TestMessage;
import com.aaryan11hash.chitthi.Repositories.TestDomainRepository;
import com.aaryan11hash.chitthi.Web.Domain.TestDomain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    @Autowired
    RabbitTemplate template;

    private final TestDomainRepository testDomainRepository;

    @PostMapping("/check1")
    public ResponseEntity<String> sendTestResponse1(){
        template.convertAndSend(RabbitMqConfig.TEST_EXCHANGE,RabbitMqConfig.BLOB_PROCESS_QUEUE,TestMessage.builder().testName("test1").build());

        return
                ResponseEntity.ok().body("DONE");

    }

    @PostMapping("/check2")
    public ResponseEntity<String> sendTestResponse2(){
        template.convertAndSend(RabbitMqConfig.TEST_EXCHANGE,RabbitMqConfig.BLOB_PROCESS_QUEUE_OUTPUT,TestMessage.builder().testName("test2").build());

        return
                ResponseEntity.ok().body("DONE");

    }

    @PostMapping("/check3")
    public ResponseEntity<String> sendTestResponse3(){
        template.convertAndSend(RabbitMqConfig.TEST_EXCHANGE,RabbitMqConfig.TEST_ROUTE3,TestMessage.builder().testName("test3").build());

        return ResponseEntity.ok().body("DONE");

    }

    @GetMapping("/save")
    public String saveData(){


        TestDomain td2 = new TestDomain("11011","aaryanSri");
        TestDomain td3 = new TestDomain("11012","sanketSHEV");


        testDomainRepository.save(td2);
        testDomainRepository.save(td3);




        return "saved";
    }

    @GetMapping("/list")
    public List<TestDomain> lisdata(){
        return testDomainRepository.findAll();
    }
}
