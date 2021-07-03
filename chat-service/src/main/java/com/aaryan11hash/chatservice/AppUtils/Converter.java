package com.aaryan11hash.chatservice.AppUtils;

import com.aaryan11hash.chatservice.Events.Models.BlobFileMessageEvent;
import com.aaryan11hash.chatservice.Web.Model.BlobFileMessage;
import org.springframework.stereotype.Component;

public class Converter {


    public static BlobFileMessageEvent blobFileModelToEvent(BlobFileMessage blobFileMessage){

        return BlobFileMessageEvent.builder()
                .id(blobFileMessage.getId())
                .chatId(blobFileMessage.getChatId())
                .recipientId(blobFileMessage.getRecipientId())
                .recipientName(blobFileMessage.getRecipientName())
                .senderId(blobFileMessage.getSenderId())
                .senderName(blobFileMessage.getSenderName())
                .status(blobFileMessage.getStatus())
                .timestamp(blobFileMessage.getTimestamp())
                .blob(blobFileMessage.getBlob())
                .build();
    }

    public static BlobFileMessage blobFileMessageEventToModel(BlobFileMessageEvent blobFileMessageEvent){

        return  BlobFileMessage.builder()
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

}
