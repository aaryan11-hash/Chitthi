package com.aaryan11hash.chatservice.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String TEST_QUEUE1 = "test-queue1";
    public static final String TEST_QUEUE2 = "test-queue2";
    public static final String TEST_QUEUE3 = "test-queue3";
    public static final String TEST_EXCHANGE  ="test_exchange";
    public static final String TEST_ROUTE1 = "test-route1";
    public static final String TEST_ROUTE2 = "test-route2";
    public static final String TEST_ROUTE3 = "test-route3";


    @Bean
    public Queue queue1(){
        return new Queue(TEST_QUEUE1);
    }

    @Bean
    public Queue queue2(){
        return new Queue(TEST_QUEUE2);
    }

    @Bean
    public Queue queue3(){
        return new Queue(TEST_QUEUE3);
    }

    @Bean
    public TopicExchange topicExchangeTest1(){
        return new TopicExchange(TEST_EXCHANGE);
    }



    @Bean
    public Binding bindingtest1(Queue queue1, TopicExchange topicExchange){

        return BindingBuilder
                .bind(queue1)
                .to(topicExchange)
                .with(TEST_ROUTE1);
    }

    @Bean
    public Binding bindingtest2(Queue queue2,TopicExchange topicExchange){

        return BindingBuilder
                .bind(queue2)
                .to(topicExchange)
                .with(TEST_ROUTE2);
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
