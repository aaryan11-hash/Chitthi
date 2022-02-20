package com.aaryan11hash.chitthi.repositories;

import com.aaryan11hash.chitthi.Web.domain.TestDomain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDomainRepository extends MongoRepository<TestDomain,String> {

}
