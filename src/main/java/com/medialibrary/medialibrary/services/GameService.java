package com.medialibrary.medialibrary.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medialibrary.medialibrary.model.Game;
import com.medialibrary.medialibrary.repositories.GameRepository;

@Service
public class GameService implements MediaService<Game>{
	
	@Autowired
	private GameRepository repo;

	@Override
	public void create(Game game) {
		repo.save(game);
	}

	@Override
	public List<Game> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Game> findById(long id) {
		return repo.findById(id);
	}

	@Override
	public void update(Game game) {
		repo.save(game);
	}

	@Override
	public void deleteById(long id) {
		repo.deleteById(id);
	}

}
