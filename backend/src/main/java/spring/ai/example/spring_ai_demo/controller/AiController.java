package spring.ai.example.spring_ai_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import spring.ai.example.spring_ai_demo.service.ai.AiService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ai")
public class AiController {
    private final AiService aiService;
    private static final Logger log = LoggerFactory.getLogger(HiveController.class);

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping
    String generate(@RequestParam(name = "userInput") String userInput) {
        log.info("GET request for AI-generation");
        return aiService.generate(userInput);
    }

}
