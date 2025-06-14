package spring.ai.example.spring_ai_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String getHome() {
        log.info("GET Request of start-page");
        return "You are home";
    }
}
