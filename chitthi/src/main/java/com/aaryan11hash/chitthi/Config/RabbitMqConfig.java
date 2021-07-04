package com.aaryan11hash.chitthi.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {


    public static final String BLOB_PROCESS_QUEUE = "blob-process-queue";
    public static final String BLOB_PROCESS_QUEUE_OUTPUT = "blob-process-queue-output";
    public static final String NOTIFICATION_EVENT = "notification-event";
    public static final String TEST_EXCHANGE  ="test_exchange";
    public static final String BLOB_PROCESS_QUEUE_ROUTE = "blob-process-queue-route";
    public static final String BLOB_PROCESS_QUEUE_OUTPUT_ROUTE = "blob-process-queue-output-route";
    public static final String TEST_ROUTE3 = "test-route3";

    @Bean
    public Queue queue1(){
        return new Queue(BLOB_PROCESS_QUEUE);
    }

    @Bean
    public Queue queue2(){
        return new Queue(BLOB_PROCESS_QUEUE_OUTPUT);
    }

    @Bean
    public Queue queue3(){
        return new Queue(NOTIFICATION_EVENT);
    }

    @Bean
    public TopicExchange topicExchangeTest1(){
        return new TopicExchange(TEST_EXCHANGE);
    }



    @Bean
    public Binding bindingtest1(Queue queue1,TopicExchange topicExchange){

        return BindingBuilder
                .bind(queue1)
                .to(topicExchange)
                .with(BLOB_PROCESS_QUEUE_ROUTE);
    }

    @Bean
    public Binding bindingtest2(Queue queue2,TopicExchange topicExchange){

        return BindingBuilder
                .bind(queue2)
                .to(topicExchange)
                .with(BLOB_PROCESS_QUEUE_OUTPUT_ROUTE);
    }

    @Bean
    public Binding bindingtest3(Queue queue3,TopicExchange topicExchange){

        return BindingBuilder
                .bind(queue3)
                .to(topicExchange)
                .with(TEST_ROUTE3);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
