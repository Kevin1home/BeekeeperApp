package com.beekeeperApp.controller;

import com.beekeeperApp.service.ai.AiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * AiController exposes REST endpoints for interacting with the AI service.
 * <p>
 * Provides an endpoint to generate text based on user input.
 * This controller acts as a bridge between the frontend application
 * (e.g., React on http://localhost:3000) and the backend AI service.
 * </p>
 */
// TODO: change to ResponseEntity
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ai")
public class AiController {
    private final AiService aiService;
    private static final Logger log = LoggerFactory.getLogger(AiService.class);

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    /**
     * Generates a text response from the AI service based on user input.
     *
     * @param userInput input provided by the user
     * @return a String response containing the generated text
     */
    @GetMapping
    String generate(@RequestParam(name = "userInput") String userInput) {
        log.info("Received AI generation request with input: {}", userInput);
        return aiService.generate(userInput);
    }
}
