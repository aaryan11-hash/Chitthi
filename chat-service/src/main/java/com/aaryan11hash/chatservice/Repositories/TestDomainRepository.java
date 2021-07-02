package com.aaryan11hash.chatservice.Repositories;

import com.aaryan11hash.chatservice.Web.Domain.TestDomain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface TestDomainRepository extends ReactiveMongoRepository<TestDomain,String> {

}
