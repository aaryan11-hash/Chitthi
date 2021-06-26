package com.aaryan11hash.chitthi.Events;

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
