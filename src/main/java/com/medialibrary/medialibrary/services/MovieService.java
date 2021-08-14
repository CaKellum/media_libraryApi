package com.medialibrary.medialibrary.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medialibrary.medialibrary.model.Movie;
import com.medialibrary.medialibrary.repositories.MovieRepository;


@Service
public class MovieService implements MediaService<Movie> {

	@Autowired
	private MovieRepository repo;
	
	@Override
	public void create(Movie movie) {
		repo.save(movie);
	}

	@Override
	public List<Movie> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Movie> findById(long id) {
		return repo.findById(id);
	}

	@Override
	public void update(Movie movie) {
		repo.save(movie);
	}

	@Override
	public void deleteById(long id) {
		repo.deleteById(id);
	}

}
