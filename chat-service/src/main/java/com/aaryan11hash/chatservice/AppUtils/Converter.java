package com.aaryan11hash.chatservice.AppUtils;

import com.aaryan11hash.chatservice.Events.Models.BlobFileMessageEvent;
import com.aaryan11hash.chatservice.Events.Models.ChatMessageEvent;
import com.aaryan11hash.chatservice.Web.Domain.BlobFileMessage;
import com.aaryan11hash.chatservice.Web.Domain.ChatMessage;
import com.aaryan11hash.chatservice.Web.Model.BlobFileMessageDto;
import com.aaryan11hash.chatservice.Web.Model.ChatMessageDto;

public class Converter {


    public static BlobFileMessageEvent blobFileModelToEvent(BlobFileMessageDto blobFileMessageDto) {

        return BlobFileMessageEvent.builder()
                .id(blobFileMessageDto.getId())
                .chatId(blobFileMessageDto.getChatId())
                .recipientId(blobFileMessageDto.getRecipientId())
                .recipientName(blobFileMessageDto.getRecipientName())
                .senderId(blobFileMessageDto.getSenderId())
                .senderName(blobFileMessageDto.getSenderName())
                .status(blobFileMessageDto.getStatus())
                .timestamp(blobFileMessageDto.getTimestamp())
                .blob(blobFileMessageDto.getBlob())
                .build();
    }

    public static BlobFileMessageDto blobFileMessageEventToModel(BlobFileMessageEvent blobFileMessageEvent) {

        return BlobFileMessageDto.builder()
                .id(blobFileMessageEvent.getId())
                .chatId(blobFileMessageEvent.getChatId())
                .blob(blobFileMessageEvent.getBlob())
                .recipientId(blobFileMessageEvent.getRecipientId())
                .recipientName(blobFileMessageEvent.getRecipientName())
                .senderName(blobFileMessageEvent.getSenderName())
                .senderId(blobFileMessageEvent.getSenderId())
                .status(blobFileMessageEvent.getStatus())
                .timestamp(blobFileMessageEvent.getTimestamp())
                .build();
    }


    public static BlobFileMessage blobFileMessageEventToDomain(BlobFileMessageEvent blobFileMessageEvent, String blobFileUrl) {
        return BlobFileMessage.builder()
                .id(blobFileMessageEvent.getId())
                .chatId(blobFileMessageEvent.getChatId())
                .recipientId(blobFileMessageEvent.getRecipientId())
                .recipientName(blobFileMessageEvent.getRecipientName())
                .senderName(blobFileMessageEvent.getSenderName())
                .senderId(blobFileMessageEvent.getSenderId())
                .status(blobFileMessageEvent.getStatus())
                .blobLink(blobFileUrl)
                .timestamp(blobFileMessageEvent.getTimestamp())
                .build();
    }

    public static ChatMessage chatMessageEventToDomain(ChatMessageEvent chatMessageEvent) {

        return ChatMessage.builder()
                .id(chatMessageEvent.getId())
                .chatId(chatMessageEvent.getChatId())
                .recipientId(chatMessageEvent.getRecipientId())
                .recipientName(chatMessageEvent.getRecipientName())
                .senderName(chatMessageEvent.getSenderName())
                .senderId(chatMessageEvent.getSenderId())
                .status(chatMessageEvent.getStatus())
                .timestamp(chatMessageEvent.getTimestamp())
                .build();
    }

    public static ChatMessageEvent chatMessageDomainToEvent(ChatMessage chatMessage) {

        return ChatMessageEvent.builder()
                .id(chatMessage.getId())
                .chatId(chatMessage.getChatId())
                .recipientId(chatMessage.getRecipientId())
                .recipientName(chatMessage.getRecipientName())
                .senderName(chatMessage.getSenderName())
                .senderId(chatMessage.getSenderId())
                .status(chatMessage.getStatus())
                .timestamp(chatMessage.getTimestamp())
                .build();

    }
}
