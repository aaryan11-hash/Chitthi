package com.aaryan11hash.eureka.Controller;


import com.aaryan11hash.eureka.Dto.RegisteryDto;
import com.aaryan11hash.eureka.Exception.UserNotOnlineException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
public class RegistryExceptionHandler {


    @ExceptionHandler(value = UserNotOnlineException.class)
    public ResponseEntity<RegisteryDto> userNotOnline(){

        return ResponseEntity.badRequest().body(RegisteryDto.builder()
                .userName("OFFLINE")
                .clientIp("OFFLINE")
                .email("OFFLINE")
                .spring_boot_app_Ip("OFFLINE")
                .spring_boot_port_number("OFFLINE")
                .build()
        );
    }

}
