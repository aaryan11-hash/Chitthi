package com.aaryan11hash.eureka.Controller;


import com.aaryan11hash.eureka.Dto.RegisteryDto;
import com.aaryan11hash.eureka.Service.RegistryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registry")
@CrossOrigin("*")
public class ServiceRegitrar {

    private final RegistryService registryService;

    @PostMapping("/save/userDetails")
    public ResponseEntity saveClientAppConnDetails(@RequestBody RegisteryDto registeryDto){

        System.out.println(registeryDto);
        registryService.saveClientAppConnDetails(registeryDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/{userName}/{email}")
    public ResponseEntity<RegisteryDto> getClientAppConnDetails(
            @PathVariable String userName,
            @PathVariable String email
    ){

        System.out.println(userName+" "+email);

        return ResponseEntity.ok(registryService.getClientAppConnDetails(userName,email));
    }
}
