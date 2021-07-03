package com.aaryan11hash.chatservice.Repositories;

import com.aaryan11hash.chatservice.Web.Domain.TestDomain;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TestDomainRepository extends MongoRepository<TestDomain,String> {

}
