package com.aaryan11hash.chatservice.Web.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get1")
    public String get1(){
        return "Working fine";
    }

    @PostMapping("post1")
    public String post1(){
        return "Working fine";
    }
}
