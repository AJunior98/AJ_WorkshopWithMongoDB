package com.ajunior.workshop.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajunior.workshop.dto.PostDTO;
import com.ajunior.workshop.entities.Post;
import com.ajunior.workshop.resources.util.URL;
import com.ajunior.workshop.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService postService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable String id){
		PostDTO dto = postService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		List<Post> list = postService.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
}
