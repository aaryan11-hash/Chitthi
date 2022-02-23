package com.aaryan11hash.eureka.Domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@Document(collection = "service_registry")
public class Registry {


    @Id
    private String userName;


    private String email;


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
