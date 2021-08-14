package com.medialibrary.medialibrary.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.medialibrary.medialibrary.exceptions.MediaNotFoundException;
import com.medialibrary.medialibrary.model.Game;
import com.medialibrary.medialibrary.services.GameService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.java.Log;


@RestController
@RequestMapping("/api/media_library/games")
@Log
public class GameRestController implements CrudController<Game>{

	@Autowired
	private GameService service;

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Added game", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content) })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Game> add(Game game) {
		log.info("creating game "+ game.toString());
		service.create(game);
		log.info("created game");
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(game.getId())
				.toUri();
		log.info("Created "+game.toString()+" at "+ location);
		return ResponseEntity.created(location).build();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Found user", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Error", content = @Content) })
	@GetMapping("/{id}")
	@Override
	public Game findById(long id) throws MediaNotFoundException {
		log.info("Searching for game by id "+id);
		Optional<Game> result = service.findById(id);
		if(result.isEmpty()) throw new MediaNotFoundException("Game not found");
		Game retGame = result.get();
		log.info("found "+ retGame.toString());
		return retGame;
	}


	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Found users", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "error", content = @Content) })
	@GetMapping
	@Override
	public List<Game> findAll() {
		log.info("Finding all games");
		List<Game> gameList = service.findAll();
		log.info(gameList.size() > 0 ? "returning "+gameList.toString() : "no games found");
		return gameList;
	}


	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Updated Game", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "500", description = "Internal Error", content = @Content) })
	@PutMapping
	@Override
	public ResponseEntity<Game> update(Game game) {
		log.info("Updating "+ game.toString());
		service.update(game);
		log.info("game updated");
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ID}").buildAndExpand(game.getId())
				.toUri();
		log.info("Updated at "+location);
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	@Override
	public void delete(long id) {
		log.info("deleting game "+ id);
		service.deleteById(id);
		log.info("deleted game");
	}

}
