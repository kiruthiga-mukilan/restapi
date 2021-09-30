package com.kiruthi.restapi.services;


import com.kiruthi.restapi.entities.User;
import com.kiruthi.restapi.exceptions.UserExistsException;
import com.kiruthi.restapi.exceptions.UserNotFoundException;
import com.kiruthi.restapi.repositories.BookRepository;
import com.kiruthi.restapi.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    public User createUser(User user) throws UserExistsException {
        //if user exist using username
        User existingUser = userRepository.findByUsername(user.getUsername());

        //if not exists throw UserExistsException
        if (existingUser != null) {
            throw new UserExistsException("User already exists in repository");
        }

        return userRepository.save(user);
    }

    // getUserById
    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUserid(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("User Not found in user Repository");
        }

        return user;
    }

    // updateUserById
    public User updateUserById(Long id, User user) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserid(id);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException(
                "User Not found in user Repository, provide the correct user id");
        }

        user.setUserid(id);
        return userRepository.save(user);

    }

    // deleteUserById
    public void deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findByUserid(id);
        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "User Not found in user Repository, provide the correct user id");
        }

        userRepository.deleteById(id);
    }

    // getUserByUsername

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByUserId(Long id) {
        return userRepository.findByUserid(id);
    }
}
