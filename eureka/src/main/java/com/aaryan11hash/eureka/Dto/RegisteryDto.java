package com.aaryan11hash.eureka.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisteryDto {


    @NonNull
    private String userName;

    @NonNull
    private String email;

    @NonNull
    private String clientIp;
    private String city;
    private String continent;
    private String country;
    private String latitude;
    private String longitude;
    private String pincode;
    private String state;

    @NonNull
    private String spring_boot_app_Ip;

    @NonNull
    private String spring_boot_port_number;


}
