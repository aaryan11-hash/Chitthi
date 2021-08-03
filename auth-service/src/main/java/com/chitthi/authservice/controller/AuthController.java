package com.chitthi.authservice.controller;

import com.chitthi.authservice.dto.AuthenticationResponse;
import com.chitthi.authservice.dto.LoginRequest;
import com.chitthi.authservice.dto.RefreshTokenRequest;
import com.chitthi.authservice.dto.RegisterRequest;
import com.chitthi.authservice.dto.cache.AuthResponseCache;
import com.chitthi.authservice.repository.AuthCacheDao;
import com.chitthi.authservice.service.AuthService;
import com.chitthi.authservice.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.Instant;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final AuthCacheDao authCacheDao;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

    @PostMapping("/saveCache")
    public String save(){
        AuthResponseCache cache = AuthResponseCache.builder().authenticationToken("asscagag").username("aaryan").expiresAt(Instant.now().plusMillis(10000)).build();
        log.info("DATA :"+cache);
        authCacheDao.saveToken(cache);
        return "SAVED";
    }

    @GetMapping("/getCache")
    public List<AuthResponseCache> get(){

        return authCacheDao.findAllTokens();
    }
}
