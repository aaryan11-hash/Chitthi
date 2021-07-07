package com.aaryan11hash.chatservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Configuration
@EnableScheduling
public class TaskSchedulerConfig {


    @Bean
    public ExecutorService inputOutputExec(){
        return Executors.newCachedThreadPool();
    }



}
