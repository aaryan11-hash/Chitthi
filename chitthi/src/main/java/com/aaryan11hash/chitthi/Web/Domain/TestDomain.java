package com.aaryan11hash.chitthi.Web.Domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "text-data")
public class TestDomain {

    @Id
    private String id;
    private String name;
}
