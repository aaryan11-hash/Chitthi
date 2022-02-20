package com.aaryan11hash.chitthi.Web.Contoller;


import com.aaryan11hash.chitthi.Config.RabbitMqConfig;
import com.aaryan11hash.chitthi.Events.models.BlobFileMessageEvent;
import com.aaryan11hash.chitthi.Events.models.TestMessage;
import com.aaryan11hash.chitthi.repositories.TestDomainRepository;
import com.aaryan11hash.chitthi.Web.domain.TestDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
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
        template.convertAndSend(RabbitMqConfig.TEST_EXCHANGE,RabbitMqConfig.BLOB_PROCESS_QUEUE_OUTPUT,BlobFileMessageEvent.builder().recipientName("sample").build());

        return
                ResponseEntity.ok().body("DONE");

    }

    @PostMapping("/check3")
    public ResponseEntity<String> sendTestResponse3(){
        template.convertAndSend(RabbitMqConfig.TEST_EXCHANGE,RabbitMqConfig.NOTIFICATION_EVENT_ROUTE, BlobFileMessageEvent.builder().recipientName("sample").build());

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
