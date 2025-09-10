package com.beekeeperApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeController provides a simple health-check style endpoint
 * for verifying that the application is running and accessible.
 */
// TODO: change to ResponseEntity
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/")
public class HomeController {
    /**
     * Handles GET requests to the root endpoint.
     * @return a simple message indicating the application is reachable.
     */
    @GetMapping
    public String getHome() {
        log.info("Received GET request for home page");
        return "You are home";
    }
}
