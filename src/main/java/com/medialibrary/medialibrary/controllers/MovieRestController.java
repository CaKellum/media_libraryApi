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
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api/media_library/movies")
@Log4j
public class MovieRestController implements CrudController<Movie> {

	@Autowired
	private MovieService service;

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Added Movie", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content) })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Movie> add(@RequestBody Movie movie) {
		log.info("Creating Movie"+ movie.toString());
		service.create(movie);
		log.info("movie Created");
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(movie.getId())
				.toUri();
		log.info("resource built at "+location);
		return ResponseEntity.created(location).build();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found movie", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Error", content = @Content) })
	@GetMapping("/{id}")
	@Override
	public Movie findById(@PathVariable("id") long id) throws MediaNotFoundException {
		Optional<Movie> result = service.findById(id);
		if (result.isEmpty()) throw new MediaNotFoundException("Movie Not Found");
		Movie retMovie = result.get();
		log.info("returning "+ retMovie.toString());
		return retMovie;
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found movies", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Sorry, user not added", content = @Content) })
	@GetMapping
	@Override
	public List<Movie> findAll() {
		log.info("finding all movies in DB");
		List<Movie> movieList = service.findAll();
		log.info(movieList.size() > 0 ? "returning "+movieList.toString() : "no movies found");
		return movieList;
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Updated ", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Sorry, user not added", content = @Content) })
	@PutMapping
	@Override
	public ResponseEntity<Movie> update(@RequestBody Movie movie) {
		log.info("updating "+movie.toString());
		service.update(movie);
		log.info("updated movie");
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(movie.getId())
				.toUri();
		log.info("updated resource at: " + location.toString());
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	@Override
	public void delete(@PathVariable("id") long id) {
		log.info("deleting movie with "+ id);
		service.deleteById(id);
		log.info("deleted movie");
	}

}
