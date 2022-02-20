package com.aaryan11hash.chitthi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

@Configuration
@EnableAsync
public class AsyncConfig {

   //todo implement async programming

    @Bean("AsyncTaskExecutor")
    public ExecutorService inputOutputExec(){

        return new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()/2,
                200,Long.MAX_VALUE,
                TimeUnit.NANOSECONDS,
                new ArrayBlockingQueue<Runnable>(1000)
        );
    }
}
