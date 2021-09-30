package com.kiruthi.restapi.repositories;


import com.kiruthi.restapi.entities.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);

    Optional<User> findByUserid(Long userid);
}
