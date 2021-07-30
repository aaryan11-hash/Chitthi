package com.aaryan11hash.chatservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.*;


@Configuration
@EnableScheduling
public class TaskSchedulerConfig {


    //TODO V.IMP decision to be made here, how to make trade-off btw throughput and resources
    //IMP Link :- https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/ThreadPoolExecutor.html
    @Bean
    public ExecutorService inputOutputExec(){

        return new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()/2,200,Long.MAX_VALUE, TimeUnit.NANOSECONDS,new ArrayBlockingQueue<Runnable>(1000));
    }



}
