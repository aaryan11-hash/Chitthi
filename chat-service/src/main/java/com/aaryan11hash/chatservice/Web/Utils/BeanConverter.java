package com.aaryan11hash.chatservice.Web.Utils;


import com.aaryan11hash.chatservice.Events.Models.ChatMessageEvent;
import com.aaryan11hash.chatservice.Web.Domain.ChatMessage;
import com.aaryan11hash.chatservice.Web.Model.ChatMessageDto;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
public class BeanConverter {


    public static ChatMessage chatMessageEventToDomain(ChatMessageEvent chatMessageEvent){

        return ChatMessage.builder()
                .chatId(chatMessageEvent.getChatId())
                .id(chatMessageEvent.getId())
                .recipientId(chatMessageEvent.getRecipientId())
                .recipientName(chatMessageEvent.getRecipientName())
                .senderName(chatMessageEvent.getSenderName())
                .senderId(chatMessageEvent.getSenderId())
                .status(chatMessageEvent.getStatus())
                .timestamp(chatMessageEvent.getTimestamp())
                .content(chatMessageEvent.getContent())
                .build();
    }

    public static ChatMessageDto chatMessageEventToDto(ChatMessageEvent chatMessageEvent){

        return ChatMessageDto.builder()
                .chatId(chatMessageEvent.getChatId())
                .id(chatMessageEvent.getId())
                .recipientId(chatMessageEvent.getRecipientId())
                .recipientName(chatMessageEvent.getRecipientName())
                .senderName(chatMessageEvent.getSenderName())
                .senderId(chatMessageEvent.getSenderId())
                .status(chatMessageEvent.getStatus())
                .timestamp(chatMessageEvent.getTimestamp())
                .content(chatMessageEvent.getContent())
                .build();
    }
}
