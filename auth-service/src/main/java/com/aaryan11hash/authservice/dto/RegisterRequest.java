package com.aaryan11hash.authservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "userName")
    private String username;
    @JsonProperty(value = "password")
    private String password;

}
