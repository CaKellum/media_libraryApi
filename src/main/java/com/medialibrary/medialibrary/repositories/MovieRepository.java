package com.medialibrary.medialibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medialibrary.medialibrary.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
