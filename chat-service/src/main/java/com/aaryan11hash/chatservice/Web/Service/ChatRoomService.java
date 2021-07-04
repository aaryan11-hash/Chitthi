package com.aaryan11hash.chatservice.Web.Service;

import com.aaryan11hash.chatservice.Repositories.ChatRoomRepository;
import com.aaryan11hash.chatservice.Web.Model.ChatRoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired private ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatId(
            String senderId, String recipientId, boolean createIfNotExist) {

         return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoomDto::getChatId)
                 .or(() -> {
                    if(!createIfNotExist) {
                        return  Optional.empty();
                    }
                     var chatId =
                            String.format("%s_%s", senderId, recipientId);

                    ChatRoomDto senderRecipient = ChatRoomDto
                            .builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .build();

                    ChatRoomDto recipientSender = ChatRoomDto
                            .builder()
                            .chatId(chatId)
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .build();
                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });

    }
}
