package com.medialibrary.medialibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medialibrary.medialibrary.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
