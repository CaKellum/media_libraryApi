package com.medialibrary.medialibrary.exceptions;

import lombok.extern.java.Log;

@Log
public class MediaNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MediaNotFoundException(String msg) {
        super(msg);
        log.severe(msg);
    }
}