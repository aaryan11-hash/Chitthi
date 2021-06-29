package com.aaryan11hash.chatservice.Events.PubSub;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisMessageSubscriber implements MessageListener {

    @Autowired
    private ObjectMapper objectMapper;

    public static List<String> messageList = new ArrayList<>();

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] bytes) {
         messageList.add(message.toString());

        System.out.println(message.toString());


    }
}
