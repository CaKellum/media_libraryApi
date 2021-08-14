package com.medialibrary.medialibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.log4j.PropertyConfigurator;

@SpringBootApplication
public class MediaLibraryApplication {

	public static void main(String[] args) {
		PropertyConfigurator.configure("src/main/resources/log4jProperties.properties");
		SpringApplication.run(MediaLibraryApplication.class, args);
	}

}
