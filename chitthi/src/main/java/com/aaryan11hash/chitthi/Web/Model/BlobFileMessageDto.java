package com.aaryan11hash.chitthi.Web.Model;


import com.aaryan11hash.chitthi.Web.Enums.MessageStatus;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class BlobFileMessageDto {

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
