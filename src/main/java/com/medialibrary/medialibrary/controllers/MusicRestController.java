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
import com.medialibrary.medialibrary.model.Music;
import com.medialibrary.medialibrary.services.MusicService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/api/v1/music")
public class MusicRestController implements CrudControler<Music> {

	@Autowired
	private MusicService service;

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Added Music", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Error", content = @Content) })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Music> add(@RequestBody Music music) {
		service.create(music);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(music.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Music Found", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "internal error", content = @Content) })
	@GetMapping("/{id}")
	@Override
	public Music findById(@PathVariable("id") long id) throws MediaNotFoundException {
		Optional<Music> result = service.findById(id);
		if (result.isEmpty())
			throw new MediaNotFoundException("Music Doesn't exist");
		return result.get();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Found Music", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content) })
	@GetMapping
	@Override
	public List<Music> findAll() {
		return service.findAll();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Updated Music", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content) })
	@PutMapping
	@Override
	public ResponseEntity<Music> update(@RequestBody Music music) {
		service.update(music);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(music.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	@Override
	public void delete(@PathVariable long id) {
		service.deleteById(id);
	}

}