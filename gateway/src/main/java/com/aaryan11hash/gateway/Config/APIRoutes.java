package com.aaryan11hash.gateway.Config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class APIRoutes {

    @Bean
    public RouteLocator ApiRoutes(RouteLocatorBuilder builder){

        return builder.routes()
                //REST Controller endpoints
                .route(r->r.path("/messages/*/*/count","/messages/*/*","/messages/*")
                        .uri("lb://chat-service")
                )
                //STOMP Brocker endpoints
                .route(r->r.path("/register-socket","/app/chat/simple-text","app/chat/blob")
                        .uri("lb://chat-service")
                        )
                .build();

    }

}
