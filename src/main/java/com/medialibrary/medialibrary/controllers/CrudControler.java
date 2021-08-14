package com.medialibrary.medialibrary.controllers;

import java.util.List;

import com.medialibrary.medialibrary.exceptions.MediaNotFoundException;

import org.springframework.http.ResponseEntity;

public interface CrudControler<T> {

	public ResponseEntity<T> add(T t);
	public T findById(long id) throws MediaNotFoundException;
	public List<T> findAll();
	public ResponseEntity<T> update(T t);
	public void delete(long id);
	
	
}
