package com.chitthi.authservice.dto.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("AuthResponseCache")
public class AuthResponseCache implements Serializable {

    @Id
    private String username;

    private String authenticationToken;
    private String refreshToken;
    private Instant expiresAt;

}
