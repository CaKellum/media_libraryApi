package com.medialibrary.medialibrary.exceptions;

public class MediaNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MediaNotFoundException(String msg) {
        super(msg);
    }
}