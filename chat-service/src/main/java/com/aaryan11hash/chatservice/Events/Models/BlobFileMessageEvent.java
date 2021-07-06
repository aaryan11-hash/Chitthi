package com.aaryan11hash.chatservice.Events.Models;

import com.aaryan11hash.chatservice.Web.Enums.MessageStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlobFileMessageEvent{

    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String senderName;
    private String recipientName;
    private MultipartFile blob;
    private Date timestamp;
    private MessageStatus status;

}
