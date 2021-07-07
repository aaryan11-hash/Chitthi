package com.aaryan11hash.chatservice.Web.Domain;


import com.aaryan11hash.chatservice.Web.Enums.MessageStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Document(collection = "blob")
public class BlobFileMessage {

    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String senderName;
    private String recipientName;
    private String blobLink;
    private Date timestamp;
    private MessageStatus status;

}
