package com.medialibrary.medialibrary.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ModelSanityTests {

	@Test
	public void MovieHoldsDataPased() {
		
		Movie movie = new Movie(0l, "title", "VHS", 0l);
		
		assertEquals(movie.getFormat(), "VHS");
		assertEquals(movie.getUserId(), 0l);
		assertEquals(movie.getTitle(), "title");
		assertEquals(movie.getId(), 0l);
	}
	
	
}
