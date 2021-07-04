package com.aaryan11hash.chitthi.Web.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {

    private String id;
    private String senderId;
    private String senderName;
    @Nullable
    private MultipartFile multipartFile;
}
