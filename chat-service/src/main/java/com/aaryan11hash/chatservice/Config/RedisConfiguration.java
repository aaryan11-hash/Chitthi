package com.aaryan11hash.chatservice.Config;

import com.aaryan11hash.chatservice.Events.PubSubService.MessagePublisher;
import com.aaryan11hash.chatservice.Events.PubSubService.RedisChatMessagePublisher;
import com.aaryan11hash.chatservice.Events.PubSubService.RedisMessageSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;


@Configuration
public class RedisConfiguration {

    public static final String PUBLISH_EVENT_FROM_USER = "publish-event-from-user";


    @Autowired
    RedisMessageSubscriber redisMessageSubscriber;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic(PUBLISH_EVENT_FROM_USER);
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter,topic());
        return redisMessageListenerContainer;
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter()
    {
        return new MessageListenerAdapter(redisMessageSubscriber,"onMessage");
    }


    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return redisTemplate;
    }

    @Bean
    MessagePublisher messagePublisher()
    {
        return new RedisChatMessagePublisher(redisTemplate(redisConnectionFactory),topic());
    }


//    @Bean
//    public ObjectMapper objectMapper(){
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
//        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//        mapper.setVisibility(mapper.getSerializationConfig()
//                .getDefaultVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY)
//                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
//                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
//                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
//        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
//        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
//        return mapper;
//    }



}
