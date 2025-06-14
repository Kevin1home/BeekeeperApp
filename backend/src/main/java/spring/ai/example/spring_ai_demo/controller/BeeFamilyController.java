package spring.ai.example.spring_ai_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import spring.ai.example.spring_ai_demo.model.beeFamily.BeeFamily;
import spring.ai.example.spring_ai_demo.service.beeFamily.BeeFamilyService;
import spring.ai.example.spring_ai_demo.service.beeFamily.BeeFamilyServiceImpl;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/beeFamily")
public class BeeFamilyController {
    private final BeeFamilyService beeFamilyService;
    private static final Logger log = LoggerFactory.getLogger(HiveController.class);

    public BeeFamilyController(BeeFamilyService beeFamilyService) {
        this.beeFamilyService = beeFamilyService;
    }

    @GetMapping
    List<BeeFamily> getAllBeeFamilies() {
        log.info("GET request for all beeFamilies");
        return beeFamilyService.getAllBeeFamilies();
    }

    @GetMapping("/{beeFamilyId}")
    BeeFamily getBeeFamilyById(@PathVariable int beeFamilyId) {
        log.info("GET request for beeFamily with id = {}", beeFamilyId);
        return beeFamilyService.getBeeFamilyById(beeFamilyId);
    }

    @PostMapping
    BeeFamily addBeeFamily(@RequestBody BeeFamily beeFamily) {
        log.info("Post request to add beeFamily");
        return beeFamilyService.addBeeFamily(beeFamily);
    }

    @DeleteMapping("/{beeFamilyId}")
    String deleteBeeFamily(@PathVariable int beeFamilyId) {
        log.info("DELETE request for beeFamily with id = {}", beeFamilyId);
        return beeFamilyService.deleteBeeFamily(beeFamilyId);
    }

}
