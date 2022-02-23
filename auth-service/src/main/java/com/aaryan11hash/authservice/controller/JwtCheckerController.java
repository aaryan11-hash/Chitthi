package com.aaryan11hash.authservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
@CrossOrigin("*")
public class JwtCheckerController {

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkJwt(){

        return new ResponseEntity(true,HttpStatus.ACCEPTED);
    }
}
