package com.medialibrary.medialibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medialibrary.medialibrary.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{

}
