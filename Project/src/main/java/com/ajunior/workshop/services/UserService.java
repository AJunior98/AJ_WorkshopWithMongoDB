package com.ajunior.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajunior.workshop.dto.UserDTO;
import com.ajunior.workshop.dto.UserDTODetails;
import com.ajunior.workshop.entities.User;
import com.ajunior.workshop.repositories.UserRepository;
import com.ajunior.workshop.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public UserDTO findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		User entity = obj.orElseThrow(() -> new ObjectNotFoundException(("Entity not found")));
		return new UserDTO(entity);
	}
	
	public UserDTODetails findByIdPosts(String id) {
		Optional<User> obj = userRepository.findById(id);
		User entity = obj.orElseThrow(() -> new ObjectNotFoundException(("Entity not found")));
		return new UserDTODetails(entity);
	}
	
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity = userRepository.save(entity);
		return new UserDTO(entity);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public UserDTO update(String id, UserDTO dto) {
		try {
			Optional<User> obj = userRepository.findById(id);
			User entity = obj.orElseThrow(() -> new ObjectNotFoundException(("Entity not found")));
			updateData(dto, entity);

			entity = userRepository.save(entity);
			return new UserDTO(entity);
		} 
		catch(Exception e) {
			throw new ObjectNotFoundException("Id not found " + id);
		}
	}
	
	public void updateData(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
}
