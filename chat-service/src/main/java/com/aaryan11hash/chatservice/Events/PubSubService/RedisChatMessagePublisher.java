package com.aaryan11hash.chatservice.Events.PubSubService;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Primary
public class RedisChatMessagePublisher implements MessagePublisher{

    private final RedisTemplate<String,Object> redisTemplate;

    private final ChannelTopic topic;

    @Override
    public void publish(String message) {
        redisTemplate.convertAndSend(topic.getTopic(),message);
    }
}
