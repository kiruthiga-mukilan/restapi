package com.kiruthi.restapi.controllers;

import com.kiruthi.restapi.entities.User;
import com.kiruthi.restapi.exceptions.UserExistsException;
import com.kiruthi.restapi.exceptions.UserNameNotFoundException;
import com.kiruthi.restapi.exceptions.UserNotFoundException;
import com.kiruthi.restapi.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@Api(tags = "User Management RESTful Services", value = "UserController", description = "Controller for User Management Service")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Retrieve list of users")
    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userService.getAllUsers();

    }

    @ApiOperation(value = "Creates a new user")
    @PostMapping
    public ResponseEntity<Void> createUser(
        @ApiParam("User information for a new user to be created.") @Valid @RequestBody User user,
        UriComponentsBuilder builder) {
        try {
            userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers
                .setLocation(builder.path("/users/{id}").buildAndExpand(user.getUserid()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

        } catch (UserExistsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") @Min(1) Long id) {
        try {
            Optional<User> user = userService.getUserById(id);
            return user.get();
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {

        try {
            return userService.updateUserById(id, user);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/users/byusername/{username}")
    public User getUserByUsername(@PathVariable("username") String username)
        throws UserNameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UserNameNotFoundException(
                "Username: '" + username + "' not found in User repository");
        }
        return user;

    }


}
