package com.ajunior.workshop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ajunior.workshop.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
