package com.aaryan11hash.eureka.Utils;

import com.aaryan11hash.eureka.Domain.Registry;
import com.aaryan11hash.eureka.Dto.RegisteryDto;
import org.springframework.stereotype.Service;

@Service
public class BeanConverter {

    public static Registry regiteryDtoToRegistry(RegisteryDto registeryDto){

        return Registry.builder()
                .userName(registeryDto.getUserName())
                .city(registeryDto.getCity())
                .clientIp(registeryDto.getClientIp())
                .continent(registeryDto.getContinent())
                .country(registeryDto.getCountry())
                .email(registeryDto.getEmail())
                .latitude(registeryDto.getLatitude())
                .longitude(registeryDto.getLongitude())
                .pincode(registeryDto.getPincode())
                .spring_boot_app_Ip(registeryDto.getSpring_boot_app_Ip())
                .spring_boot_port_number(registeryDto.getSpring_boot_port_number())
                .state(registeryDto.getState())
                .build();
    }

    public static RegisteryDto regiteryToRegistryDto(Registry registery){

        return RegisteryDto.builder()
                .userName(registery.getUserName())
                .city(registery.getCity())
                .clientIp(registery.getClientIp())
                .continent(registery.getContinent())
                .country(registery.getCountry())
                .email(registery.getEmail())
                .latitude(registery.getLatitude())
                .longitude(registery.getLongitude())
                .pincode(registery.getPincode())
                .spring_boot_app_Ip(registery.getSpring_boot_app_Ip())
                .spring_boot_port_number(registery.getSpring_boot_port_number())
                .state(registery.getState())
                .build();
    }
}
