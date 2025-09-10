package com.beekeeperApp.controller;

import com.beekeeperApp.service.beeFamily.BeeFamilyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.beekeeperApp.model.beeFamily.BeeFamily;

import java.util.List;

/**
 * BeeFamilyController exposes CRUD operations for managing bee families.
 * <p>
 * This controller provides endpoints for:<br>
 * - Retrieving all bee families<br>
 * - Retrieving a bee family by its ID<br>
 * - Creating a new bee family<br>
 * - Deleting a bee family by ID<br>
 * </p>
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/beeFamily")
public class BeeFamilyController {
    private final BeeFamilyService beeFamilyService;
    private static final Logger log = LoggerFactory.getLogger(BeeFamilyService.class);

    public BeeFamilyController(BeeFamilyService beeFamilyService) {
        this.beeFamilyService = beeFamilyService;
    }

    /**
     * Retrieve all bee families.
     * @return list of all bee families
     */
    @GetMapping
    List<BeeFamily> getAllBeeFamilies() {
        log.info("Received request to fetch all bee families");
        return beeFamilyService.getAllBeeFamilies();
    }

    /**
     * Retrieve a bee family by its ID.
     *
     * @param beeFamilyId unique identifier of the bee family
     * @return bee family if found
     */
    // TODO: change to ResponseEntity
    @GetMapping("/{beeFamilyId}")
    BeeFamily getBeeFamilyById(@PathVariable int beeFamilyId) {
        log.info("Received request to fetch bee family with id = {}", beeFamilyId);
        return beeFamilyService.getBeeFamilyById(beeFamilyId);
    }

    /**
     * Create a new bee family.
     *
     * @param beeFamily request body containing bee family details
     * @return created bee family
     */
    @PostMapping
    BeeFamily addBeeFamily(@RequestBody BeeFamily beeFamily) {
        log.info("Received request to create new bee family");
        return beeFamilyService.addBeeFamily(beeFamily);
    }

    /**
     * Delete a bee family by ID.
     *
     * @param beeFamilyId unique identifier of the bee family
     * @return a String response "BeeFamily was deleted" if succeeded
     */
    @DeleteMapping("/{beeFamilyId}")
    String deleteBeeFamily(@PathVariable int beeFamilyId) {
        log.info("DELETE request for beeFamily with id = {}", beeFamilyId);
        return beeFamilyService.deleteBeeFamily(beeFamilyId);
    }
}
