package com.ajunior.workshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajunior.workshop.dto.PostDTO;
import com.ajunior.workshop.entities.Post;
import com.ajunior.workshop.repositories.PostRepository;
import com.ajunior.workshop.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public PostDTO findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		Post entity = obj.orElseThrow(() -> new ObjectNotFoundException(("Entity not found")));
		return new PostDTO(entity);
	}
	
}
