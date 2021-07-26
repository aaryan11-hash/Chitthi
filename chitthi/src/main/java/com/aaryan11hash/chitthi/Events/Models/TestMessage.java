package com.aaryan11hash.chitthi.Events.Models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
@ToString
public class TestMessage {

    private String testName;
    private Long testVal;
    private NotificationType notificationType;
}
