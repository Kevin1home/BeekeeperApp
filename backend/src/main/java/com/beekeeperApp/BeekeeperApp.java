package com.beekeeperApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Beekeeper Application.
 * <p>
 * Starts the Spring Boot context and initializes all services,
 * repositories, and controllers.
 * </p>
 */
@SpringBootApplication
@Slf4j
public class BeekeeperApp {

	public static void main(String[] args) {
		log.info("Starting BeekeeperApp...");
		SpringApplication.run(BeekeeperApp.class, args);
		log.info("BeekeeperApp started successfully!");
	}
}
