package com.aaryan11hash.eureka.Repositories;

import com.aaryan11hash.eureka.Domain.Registry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistryRepository extends MongoRepository<Registry, String> {

    Optional<Registry> getByEmailAndUserName(String email, String userName);

}
