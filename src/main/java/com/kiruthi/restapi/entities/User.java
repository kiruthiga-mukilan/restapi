package com.kiruthi.restapi.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user")
@Data
public class User {

    @Field(name = "username")
    private String username;

    @Field(name = "firstname")
    private String firstname;

    @Field(name = "email")
    private String email;
}
