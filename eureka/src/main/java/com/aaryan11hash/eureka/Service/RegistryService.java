package com.aaryan11hash.eureka.Service;

import com.aaryan11hash.eureka.Domain.Registry;
import com.aaryan11hash.eureka.Dto.RegisteryDto;
import com.aaryan11hash.eureka.Exception.UserNotOnlineException;
import com.aaryan11hash.eureka.Repositories.RegistryRepository;
import com.aaryan11hash.eureka.Utils.BeanConverter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class RegistryService {

    private final RegistryRepository registryRepository;


    public CompletableFuture saveClientAppConnDetails(RegisteryDto registeryDto){

        return CompletableFuture.runAsync(() -> registryRepository.save(BeanConverter.regiteryDtoToRegistry(registeryDto)));
    }

    public RegisteryDto getClientAppConnDetails(String userName, String email) {

        return CompletableFuture.supplyAsync(()-> BeanConverter.regiteryToRegistryDto(

                registryRepository.getByEmailAndUserName(email,userName)
                        .orElseThrow(() -> new UserNotOnlineException("User Not online :: " + userName +" "+ email)))).join();
    }
}
