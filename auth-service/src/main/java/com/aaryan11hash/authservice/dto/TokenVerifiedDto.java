package com.aaryan11hash.authservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Builder
@Data
@Getter
@Setter
public class TokenVerifiedDto {

    private String message;
}
