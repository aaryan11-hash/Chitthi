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

    public static final String TEST_QUEUE = "test-queue";
    public static final String TEST_EXCHANGE  ="test_exchange";
    public static final String TEST_ROUTE = "test-route";

    @Bean
    public Queue queue(){
        return new Queue(TEST_QUEUE);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TEST_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue,TopicExchange topicExchange){

        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with(TEST_ROUTE);
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
