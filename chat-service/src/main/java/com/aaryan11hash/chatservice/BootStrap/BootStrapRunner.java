package com.aaryan11hash.chatservice.BootStrap;

import com.aaryan11hash.chatservice.Repositories.TestDomainRepository;
import com.aaryan11hash.chatservice.Web.Domain.TestDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Slf4j
public class BootStrapRunner implements CommandLineRunner {

    private final TestDomainRepository repository;

    @Override
    public void run(String... args) throws Exception {


        log.info("saving data");

    }
}
