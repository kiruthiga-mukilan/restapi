package com.kiruthi.restapi.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.hateoas.RepresentationModel;

@JsonInclude(Include.NON_NULL)
public class BaseMongoEntity<G extends Serializable> extends RepresentationModel {

    @Id
    protected G id;


}
