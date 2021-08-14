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
import lombok.extern.java.Log;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/api/media_library/music")
@Log
public class MusicRestController implements CrudController<Music> {

	@Autowired
	private MusicService service;

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Added Music", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Error", content = @Content) })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Music> add(@RequestBody Music music) {
		log.info("Creating music "+music.toString());
		service.create(music);
		log.info("Created Music");
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(music.getId())
				.toUri();
		log.info("Created "+music.toString()+" at "+location);
		return ResponseEntity.created(location).build();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Music Found", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "internal error", content = @Content) })
	@GetMapping("/{id}")
	@Override
	public Music findById(@PathVariable("id") long id) throws MediaNotFoundException {
		log.info("Searching for music with id "+id);
		Optional<Music> result = service.findById(id);
		if (result.isEmpty()) throw new MediaNotFoundException("Music Doesn't exist");
		Music retMusic = result.get();
		log.info("found "+retMusic.toString());
		return retMusic;
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Found Music", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content) })
	@GetMapping
	@Override
	public List<Music> findAll() {
		log.info("finding all music");
		List<Music> musicList =  service.findAll();
		log.info(musicList.size() > 0 ? "returning "+ musicList.toString() : "no music found");
		return musicList;
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Updated Music", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content) })
	@PutMapping
	@Override
	public ResponseEntity<Music> update(@RequestBody Music music) {
		log.info("updating music "+music.toString());
		service.update(music);
		log.info("updated Music");
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(music.getId())
				.toUri();
		log.info("Updated "+music.toString()+" at "+location);
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	@Override
	public void delete(@PathVariable long id) {
		log.info("Deleting music at id "+id);
		service.deleteById(id);
		log.info("music deleted");
	}

}
