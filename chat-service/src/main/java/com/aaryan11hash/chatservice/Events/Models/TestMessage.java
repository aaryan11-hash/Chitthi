package com.aaryan11hash.chatservice.Events.Models;

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
}
