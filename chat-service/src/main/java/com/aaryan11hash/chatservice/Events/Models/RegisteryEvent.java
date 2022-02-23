package com.aaryan11hash.chatservice.Events.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisteryEvent {

    @NonNull
    private String userName;
    @NonNull
    private String email;

    //@NonNull
    private String clientIp;
    private String city;
    private String continent;
    private String country;
    private String latitude;
    private String longitude;
    private String pincode;
    private String state;


    private String spring_boot_app_Ip;

    private String spring_boot_port_number;


}
