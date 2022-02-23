package com.aaryan11hash.eureka.Exception;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotOnlineException extends RuntimeException{

    public UserNotOnlineException(String message) {

        log.error(message);
    }


}
