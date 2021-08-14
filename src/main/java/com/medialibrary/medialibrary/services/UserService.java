package com.medialibrary.medialibrary.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medialibrary.medialibrary.model.User;
import com.medialibrary.medialibrary.repositories.UserRepository;

@Service
public class UserService implements MediaService<User> {

	@Autowired
	private UserRepository repo;
	
	public void create(User user) {
		repo.save(user);
	}
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public Optional<User> findById(long id){
		return repo.findById(id);
	}
	
	public void update(User user) {
		repo.save(user);
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
	
}
