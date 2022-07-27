package com.ajunior.workshop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ajunior.workshop.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
