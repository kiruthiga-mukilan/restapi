package com.kiruthi.restapi.entities;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.hateoas.RepresentationModel;

@Document(collection = "book")
@Data
public class Book extends BaseMongoEntity<String> {

    @Indexed(unique = true)
    @NotNull
    @Field(name = "bookid")
    private Long bookid;

    @DBRef
    @Field(name = "user")
    private User user;

    @Field(name = "bookname")
    private String bookname;

}
