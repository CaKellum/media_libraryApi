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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController implements CrudControler<User> {

	@Autowired
	private UserService service;

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Added user", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Error", content = @Content) })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<User> add(@RequestBody User user) {
		service.create(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found User", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content) })
	@GetMapping("/{id}")
	@Override
	public User findById(@PathVariable long id) throws MediaNotFoundException {
		Optional<User> result = service.findById(id);
		if (result.isEmpty()) {
			throw new MediaNotFoundException("User not found");
		}
		return result.get();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of users", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content) })
	@GetMapping
	@Override
	public List<User> findAll() {
		return service.findAll();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Updated User Sussecfully", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content) })
	@PutMapping
	@Override
	public ResponseEntity<User> update(@RequestBody User user) {
		service.update(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	@Override
	public void delete(@PathVariable("id") long id) {
		service.deleteById(id);
	}

}
