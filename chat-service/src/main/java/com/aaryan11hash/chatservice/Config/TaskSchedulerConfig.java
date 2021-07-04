package com.aaryan11hash.chatservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class TaskSchedulerConfig {

    @Bean
    public ExecutorService ioExecutor(){

        return Executors.newCachedThreadPool();
    }

    @Bean
    public ExecutorService processExecutor(){

        int kernelThreads = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(kernelThreads/2);
    }
}
