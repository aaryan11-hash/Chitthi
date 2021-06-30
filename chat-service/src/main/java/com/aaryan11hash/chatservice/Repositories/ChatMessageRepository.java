package com.aaryan11hash.chatservice.Repositories;



import com.aaryan11hash.chatservice.Web.Model.ChatMessage;
import com.aaryan11hash.chatservice.Web.Model.MessageStatus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ChatMessageRepository
        extends ReactiveMongoRepository<ChatMessage, String> {

    Long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);
}