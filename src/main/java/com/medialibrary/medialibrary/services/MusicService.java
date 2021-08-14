package com.medialibrary.medialibrary.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medialibrary.medialibrary.model.Music;
import com.medialibrary.medialibrary.repositories.MusicRepository;

@Service
public class MusicService implements MediaService<Music>{

	@Autowired
	private MusicRepository repo;

	@Override
	public void create(Music music) {
		repo.save(music);
	}

	@Override
	public List<Music> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Music> findById(long id) {
		return repo.findById(id);
	}

	@Override
	public void update(Music music) {
		repo.save(music);
	}

	@Override
	public void deleteById(long id) {
		repo.deleteById(id);
	}
	
	
	
}
