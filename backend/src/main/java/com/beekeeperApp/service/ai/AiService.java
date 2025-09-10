package com.beekeeperApp.service.ai;

/**
 * Service interface for AI-based text generation.
 * Implementations can use various AI models to generate responses based on user input.
 */
public interface AiService {
    /**
     * Generates a response from AI based on the provided user input.
     *
     * @param userInput the input string from the user
     * @return the generated AI response as a String
     */
    String generate(String userInput);
}
