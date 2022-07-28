package com.ajunior.workshop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ajunior.workshop.entities.Post;
import com.ajunior.workshop.entities.User;

public class UserDTODetails implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
	private List<Post> posts = new ArrayList<>();
	
	public UserDTODetails() {
	}

	public UserDTODetails(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public UserDTODetails(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		posts = entity.getPosts();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
