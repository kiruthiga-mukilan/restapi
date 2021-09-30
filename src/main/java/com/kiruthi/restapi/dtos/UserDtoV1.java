package com.kiruthi.restapi.dtos;

import lombok.Data;

@Data
public class UserDtoV1 {

    private Long userid;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private String ssn;


}
