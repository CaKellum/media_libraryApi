package com.medialibrary.medialibrary.services;

import java.util.List;
import java.util.Optional;

public interface MediaService<T> {

	public void create(T t);
	
	public List<T> findAll();
	
	public Optional<T> findById(long id);
	
	public void update(T t);
	
	public void deleteById(long id);
	
	
}
