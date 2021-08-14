package com.medialibrary.medialibrary.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.medialibrary.medialibrary.exceptions.MediaNotFoundException;
import com.medialibrary.medialibrary.model.User;
import com.medialibrary.medialibrary.services.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/media_library/users")
@Log4j
public class UserRestController implements CrudController<User> {

	@Autowired
	private UserService service;

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Added user", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Error", content = @Content) })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<User> add(@RequestBody User user) {
		log.info("Creating user "+user.toString());
		service.create(user);
		log.info("Created User");
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(user.getId())
				.toUri();
		log.info("Created "+user.toString()+" at "+location);
		return ResponseEntity.created(location).build();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found User", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content) })
	@GetMapping("/{id}")
	@Override
	public User findById(@PathVariable long id) throws MediaNotFoundException {
		log.info("Searching for user with id "+id);
		Optional<User> result = service.findById(id);
		if (result.isEmpty()) throw new MediaNotFoundException("User not found");
		User retUser = result.get();
		log.info("returning "+retUser.toString());
		return retUser;
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of users", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content) })
	@GetMapping
	@Override
	public List<User> findAll() {
		log.info("Getting all users");
		List<User> userList = service.findAll();
		log.info(userList.size() > 0 ? "returning" + userList.toString() : "no users found");
		return userList;
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Updated User Sussecfully", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content) })
	@PutMapping
	@Override
	public ResponseEntity<User> update(@RequestBody User user) {
		log.info("updating user "+user.toString());
		service.create(user);
		log.info("updating user");
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(user.getId())
				.toUri();
		log.info("Created "+user.toString()+" at "+location);
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	@Override
	public void delete(@PathVariable("id") long id) {
		log.info("Deleting user with id "+ id);
		service.deleteById(id);
		log.info("user deleted");
	}

}
