package com.kiruthi.restapi.controllers;

import com.kiruthi.restapi.dtos.UserMsDto;
import com.kiruthi.restapi.entities.User;
import com.kiruthi.restapi.mappers.UserMapper;
import com.kiruthi.restapi.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserMsDto> getAllUserDtos() {
        return userMapper.usersToUserDtos(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public UserMsDto getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findByUserid(id);
        User user = userOptional.get();
        return userMapper.userToUserMsDto(user);
    }


}
