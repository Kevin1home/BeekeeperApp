package spring.ai.example.spring_ai_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import spring.ai.example.spring_ai_demo.model.hive.Hive;
import spring.ai.example.spring_ai_demo.service.hive.HiveService;
import spring.ai.example.spring_ai_demo.service.hive.HiveServiceImpl;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/hive")
public class HiveController {
    private final HiveService hiveService;
    private static final Logger log = LoggerFactory.getLogger(HiveController.class);

    public HiveController(HiveService hiveService) {
        this.hiveService = hiveService;
    }

    @GetMapping
    public List<Hive> getAllHives() {
        log.info("GET request for all hives");
        return hiveService.getAllHives();
    }

    @GetMapping("/{hiveId}")
    public Hive getHiveById(@PathVariable int hiveId) {
        log.info("GET request for hive with id = {}", hiveId);
        return hiveService.getHiveById(hiveId);
    }

    @PostMapping
    public Hive addHive(@RequestBody Hive hive) {
        log.info("Post request to add hive");
        return hiveService.addHive(hive);
    }

    @DeleteMapping("/{hiveId}")
    public String deleteHive(@PathVariable int hiveId) {
        log.info("DELETE request for hive with id = {}", hiveId);
        return hiveService.deleteHive(hiveId);
    }

}
