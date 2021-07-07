package com.aaryan11hash.chatservice.Events.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class MessagingEvent {

    private ChatMessageEvent chatMessageEvent;
    private BlobFileMessageEvent blobFileMessageEvent;
}
