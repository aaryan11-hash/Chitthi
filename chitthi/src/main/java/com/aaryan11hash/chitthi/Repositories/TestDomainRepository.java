package com.aaryan11hash.chitthi.Repositories;

import com.aaryan11hash.chitthi.Web.Domain.TestDomain;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TestDomainRepository extends ReactiveMongoRepository<TestDomain,String> {

}
