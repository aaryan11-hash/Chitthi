package com.aaryan11hash.chatservice.Web.Controller;


import com.chitthi.authservice.dto.cache.AuthResponseCache;
import com.aaryan11hash.chatservice.Repositories.AuthCacheDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AuthCacheDao authCacheDao;

    @GetMapping("/")
    public String get1(){
        return "Working fine";
    }

    @GetMapping("/1")
    public String get2(){
        return "Working fine1";
    }

    @GetMapping("/2")
    public List<AuthResponseCache> get3(){
        return  authCacheDao.findAllTokens();
    }

    @PostMapping("post1")
    public String post1(){
        return "Working fine";
    }
}
