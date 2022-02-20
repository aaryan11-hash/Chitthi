package com.aaryan11hash.chitthi.BootStrap;

import com.aaryan11hash.chitthi.repositories.TestDomainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;


@RequiredArgsConstructor
@Slf4j
public class BootStrapRunner implements CommandLineRunner {

    private final TestDomainRepository repository;

    @Override
    public void run(String... args) throws Exception {
        //empty
    }
}
