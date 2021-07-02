package com.aaryan11hash.chitthi.BootStrap;

import com.aaryan11hash.chitthi.Repositories.TestDomainRepository;
import com.aaryan11hash.chitthi.Web.Domain.TestDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import reactor.core.publisher.Flux;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
public class BootStrapRunner implements CommandLineRunner {

    private final TestDomainRepository repository;

    @Override
    public void run(String... args) throws Exception {

        Flux<TestDomain> list = repository.findAll();

        list.toStream()
                .forEach(System.out::println);

    }
}
