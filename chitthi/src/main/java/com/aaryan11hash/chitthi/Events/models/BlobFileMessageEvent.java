package com.aaryan11hash.chitthi.Events.models;



import com.aaryan11hash.chitthi.Web.Enum.MessageStatus;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BlobFileMessageEvent {

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