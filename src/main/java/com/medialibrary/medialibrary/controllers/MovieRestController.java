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
import com.medialibrary.medialibrary.model.Movie;
import com.medialibrary.medialibrary.services.MovieService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieRestController implements CrudControler<Movie> {

	@Autowired
	private MovieService service;

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Added Movie", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content) })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Movie> add(@RequestBody Movie movie) {
		service.create(movie);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(movie.getId())
				.toUri();
		System.out.println("Created resource at: " + location.toString());
		return ResponseEntity.created(location).build();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found movie", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Error", content = @Content) })
	@GetMapping("/{id}")
	@Override
	public Movie findById(@PathVariable("id") long id) throws MediaNotFoundException {
		Optional<Movie> result = service.findById(id);
		if (result.isEmpty()) {
			throw new MediaNotFoundException("Movie Not Found");
		}
		return result.get();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found movies", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Sorry, user not added", content = @Content) })
	@GetMapping
	@Override
	public List<Movie> findAll() {
		return service.findAll();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Updated ", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Sorry, user not added", content = @Content) })
	@PutMapping
	@Override
	public ResponseEntity<Movie> update(@RequestBody Movie movie) {
		service.update(movie);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(movie.getId())
				.toUri();
		System.out.println("Created resource at: " + location.toString());
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	@Override
	public void delete(@PathVariable("id") long id) {
		service.deleteById(id);
	}

}
