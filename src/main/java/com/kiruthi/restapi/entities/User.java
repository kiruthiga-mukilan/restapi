package com.kiruthi.restapi.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.kiruthi.restapi.entities.Views.External;
import com.kiruthi.restapi.entities.Views.Internal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user")
@Data
@JsonFilter(value = "userFilter")
@ApiModel(description = "This model is to create a user")
public class User extends BaseMongoEntity<String> {

    @NotNull
    @Indexed(unique = true)
    @Field(name = "userid")
    @JsonView(External.class)
    private Long userid;

    @JsonManagedReference
    private List<Book> books;

    @ApiModelProperty(notes = "username should be in format flname", example = "kiruthiga", required = false, position = 2)
    @JsonView(External.class)
    @Field(name = "username")
    private String username;

    @Field(name = "firstname")
    private String firstname;

    @JsonView(Internal.class)
    @Field(name = "email")
    private String email;
}
