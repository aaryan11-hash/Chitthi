package com.aaryan11hash.chatservice.Events.PubSub;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class RedisMessageSubscriber implements MessageListener {


    private ObjectMapper objectMapper;

    public static List<String> messageList = new ArrayList<>();


    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] bytes) {
         messageList.add(message.toString());
        com.aaryan11hash.chatservice.Events.Models.Message message1 = new ObjectMapper().readValue(message.toString(), com.aaryan11hash.chatservice.Events.Models.Message.class);
        //System.out.println(objectMapper.convertValue(message.toString(), com.aaryan11hash.chatservice.Events.Models.Message.class));
        System.out.println(message1.getData());
    }
}
