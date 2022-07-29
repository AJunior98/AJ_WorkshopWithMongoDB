package com.ajunior.workshop.services;

import java.util.Date;
import java.util.List;
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
	
	public List<Post> findByTitle(String text){
		return postRepository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return postRepository.fullSearch(text, minDate, maxDate);
	}
}
