package com.aaryan11hash.chatservice.Repositories;



import com.aaryan11hash.chatservice.Web.Domain.ChatMessage;
import com.aaryan11hash.chatservice.Web.Enums.MessageStatus;
import com.aaryan11hash.chatservice.Web.Model.ChatMessageDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ChatMessageRepository
        extends MongoRepository<ChatMessage, String> {

    Long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessageDto> findByChatId(String chatId);
}