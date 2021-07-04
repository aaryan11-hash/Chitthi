package com.aaryan11hash.chatservice.Web.Service;

import com.aaryan11hash.chatservice.Repositories.BlobMessageRepository;
import com.aaryan11hash.chatservice.Web.Domain.BlobFileMessage;
import com.aaryan11hash.chatservice.Web.Domain.ChatMessage;
import com.aaryan11hash.chatservice.Web.Enums.MessageStatus;
import com.aaryan11hash.chatservice.Web.Model.ChatMessageDto;
import com.aaryan11hash.chatservice.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlobMessageService {

    private final BlobMessageRepository blobMessageRepository;

    private final ChatRoomService chatRoomService;

    private final MongoOperations mongoOperations;

    public BlobFileMessage save(BlobFileMessage blobFileMessage){
        blobFileMessage.setStatus(MessageStatus.RECEIVED);
        blobMessageRepository.save(blobFileMessage);
        return blobFileMessage;
    }

    public Long countNewMessages(String senderId, String recipientId) {

        return blobMessageRepository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<ChatMessageDto> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        var messages =
                chatId.map(cId -> blobMessageRepository.findByChatId(cId)).orElse(new ArrayList<>());

        if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        return messages;
    }

    public BlobFileMessage findById(String id) {
        return blobMessageRepository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return blobMessageRepository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));

    }

    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
        Query query = new Query(
                Criteria
                        .where("senderId").is(senderId)
                        .and("recipientId").is(recipientId));
        Update update = Update.update("status", status);
        mongoOperations.updateMulti(query, update, ChatMessageDto.class);
    }
}
