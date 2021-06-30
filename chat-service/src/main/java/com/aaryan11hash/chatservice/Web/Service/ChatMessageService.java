package com.aaryan11hash.chatservice.Web.Service;

import com.aaryan11hash.chatservice.Repositories.ChatMessageRepository;
import com.aaryan11hash.chatservice.Web.Model.ChatMessage;
import com.aaryan11hash.chatservice.Web.Model.MessageStatus;
import com.aaryan11hash.chatservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {
    @Autowired private ChatMessageRepository repository;
    @Autowired private ChatRoomService chatRoomService;
    @Autowired private ReactiveMongoOperations mongoOperations;

    public Mono<ChatMessage> save(ChatMessage chatMessage) {

        return Mono.just(chatMessage)
                .flatMap(chatMessage1 -> {
                    chatMessage.setStatus(MessageStatus.RECEIVED);
                    repository.save(chatMessage);
                    return Mono.just(chatMessage1);
                });


    }

    public Long countNewMessages(String senderId, String recipientId) {

        return repository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        var messages =
                chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());

        if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        return messages;
    }

    public Mono<ChatMessage> findById(String id) {
        return repository
                .findById(id)
                .flatMap(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return repository.save(chatMessage);
                })
                .doOnError(chatRoomService->Mono.just(new ResourceNotFoundException("can't find message (" + id + ")")));


    }

    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
        Query query = new Query(
                Criteria
                        .where("senderId").is(senderId)
                        .and("recipientId").is(recipientId));
        Update update = Update.update("status", status);
        mongoOperations.updateMulti(query, update, ChatMessage.class);
    }
}
