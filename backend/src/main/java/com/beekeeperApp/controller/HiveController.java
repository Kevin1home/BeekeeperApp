package com.beekeeperApp.controller;

import com.beekeeperApp.service.hive.HiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.beekeeperApp.model.hive.Hive;

import java.util.List;

/**
 * HiveController exposes CRUD operations for managing hives.
 * <p>
 * This controller provides endpoints for:<br>
 * - Retrieving all hives<br>
 * - Retrieving a hive by its ID<br>
 * - Creating a new hive<br>
 * - Deleting a hive by ID<br>
 * </p>
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/hive")
// TODO: change to ResponseEntity
public class HiveController {
    private final HiveService hiveService;
    private static final Logger log = LoggerFactory.getLogger(HiveController.class);

    public HiveController(HiveService hiveService) {
        this.hiveService = hiveService;
    }

    /**
     * Retrieve all hives.
     * @return list of all hives
     */
    @GetMapping
    public List<Hive> getAllHives() {
        log.info("Received request to fetch all hives");
        return hiveService.getAllHives();
    }

    /**
     * Retrieve a hive by its ID.
     *
     * @param hiveId unique identifier of the hive
     * @return hive if found
     */
    @GetMapping("/{hiveId}")
    public Hive getHiveById(@PathVariable int hiveId) {
        log.info("Received request to fetch hive with id = {}", hiveId);
        return hiveService.getHiveById(hiveId);
    }

    /**
     * Create a new hive.
     *
     * @param hive request body containing hive details
     * @return created hive
     */
    @PostMapping
    public Hive addHive(@RequestBody Hive hive) {
        log.info("Received request to create new hive");
        return hiveService.addHive(hive);
    }

    /**
     * Delete a bee family by ID.
     *
     * @param hiveId unique identifier of the hive
     * @return a String response "Hive was deleted" if succeeded
     */
    @DeleteMapping("/{hiveId}")
    public String deleteHive(@PathVariable int hiveId) {
        log.info("DELETE request for hive with id = {}", hiveId);
        return hiveService.deleteHive(hiveId);
    }
}
