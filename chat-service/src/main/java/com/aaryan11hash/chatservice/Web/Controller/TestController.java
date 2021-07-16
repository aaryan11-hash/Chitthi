package com.aaryan11hash.chatservice.Web.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/")
    public String get1(){
        return "Working fine";
    }
    @GetMapping("/1")
    public String get2(){
        return "Working fine1";
    }
    @GetMapping("/2")
    public String get3(){
        return "Working fine2";
    }

    @PostMapping("post1")
    public String post1(){
        return "Working fine";
    }
}
