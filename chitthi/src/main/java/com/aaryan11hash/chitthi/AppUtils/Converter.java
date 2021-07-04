package com.aaryan11hash.chitthi.AppUtils;


import com.aaryan11hash.chitthi.Events.Models.BlobFileMessageEvent;
import com.aaryan11hash.chitthi.Events.Models.ChatMessageEvent;
import com.aaryan11hash.chitthi.Web.Domain.BlobFileMessage;
import com.aaryan11hash.chitthi.Web.Domain.ChatMessage;
import com.aaryan11hash.chitthi.Web.Model.BlobFileMessageDto;

public class Converter {


    public static BlobFileMessageEvent blobFileModelToEvent(BlobFileMessageDto blobFileMessageDto){

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

    public static BlobFileMessageDto blobFileMessageEventToModel(BlobFileMessageEvent blobFileMessageEvent){

        return  BlobFileMessageDto.builder()
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


    public static BlobFileMessage blobFileMessageEventToDomain(BlobFileMessageEvent blobFileMessageEvent){
        return  BlobFileMessage.builder()
                .id(blobFileMessageEvent.getId())
                .chatId(blobFileMessageEvent.getChatId())
                .recipientId(blobFileMessageEvent.getRecipientId())
                .recipientName(blobFileMessageEvent.getRecipientName())
                .senderName(blobFileMessageEvent.getSenderName())
                .senderId(blobFileMessageEvent.getSenderId())
                .status(blobFileMessageEvent.getStatus())
                .timestamp(blobFileMessageEvent.getTimestamp())
                .build();
    }

    public static ChatMessage chatMessageEventToDomain(ChatMessageEvent chatMessageEvent){

        return  ChatMessage.builder()
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

}
