package com.medialibrary.medialibrary.exceptions;

import lombok.extern.log4j.Log4j;

@Log4j
public class MediaNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public MediaNotFoundException(String msg) {
        super(msg);
        log.error(msg);
    }
}