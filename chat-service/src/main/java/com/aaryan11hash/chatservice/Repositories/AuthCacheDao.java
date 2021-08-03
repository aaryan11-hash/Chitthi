package com.aaryan11hash.chatservice.Repositories;

import com.chitthi.authservice.dto.cache.AuthResponseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthCacheDao {


    @Autowired
    @Qualifier("common")
    private RedisTemplate redisTemplate;

    public static final String HASH_KEY = "AuthResponseCache";

    @Async
    public void saveToken(AuthResponseCache authResponseCache){
        redisTemplate.opsForHash().put(HASH_KEY,authResponseCache.getUsername(),authResponseCache);
    }

    public List<AuthResponseCache> findAllTokens(){

        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public AuthResponseCache findTokenById(String userName){

        AuthResponseCache authResponseCache = (AuthResponseCache) redisTemplate.opsForHash().get(HASH_KEY,userName);


        return authResponseCache;
    }

    public String removeToken(String userName){

        redisTemplate.opsForHash().delete(HASH_KEY,userName);
        return "TOKEN DELETED";
    }
}
