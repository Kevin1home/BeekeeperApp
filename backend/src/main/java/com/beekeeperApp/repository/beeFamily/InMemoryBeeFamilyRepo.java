package com.beekeeperApp.repository.beeFamily;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.beekeeperApp.exception.BeeFamilyNotFoundException;
import com.beekeeperApp.model.beeFamily.BeeFamily;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of BeeFamilyRepo.
 * <p>
 * Stores BeeFamily entities in a HashMap for quick access.
 * Useful for testing.
 * </p>
 */
@Slf4j
@Repository("InMemoryBeeFamilyRepo")
public class InMemoryBeeFamilyRepo implements BeeFamilyRepo {
    private int countID = 0;
    private final Map<Integer, BeeFamily> beeFamilies = new HashMap<>();

    /**
     * Retrieves all bee families.
     * @return list of all BeeFamily objects
     */
    public List<BeeFamily> getAllBeeFamilies() {
        log.info("Repo. Retrieving all bee families: {}", beeFamilies);
        return beeFamilies.values().stream().toList();
    }

    /**
     * Retrieves a bee family by its ID.
     *
     * @param beeFamilyId the ID of the bee family
     * @return the BeeFamily object
     * @throws BeeFamilyNotFoundException if the bee family does not exist
     */
    public BeeFamily getBeeFamilyById(int beeFamilyId) {
        log.info("Fetching bee family with ID: {}", beeFamilyId);
        BeeFamily beeFamily = beeFamilies.get(beeFamilyId);
        if (beeFamily == null) {
            throw new BeeFamilyNotFoundException("BeeFamily with ID " + beeFamilyId + " not found");
        }
        return beeFamily;
    }

    /**
     * Adds a new bee family to the repository.
     *
     * @param beeFamily the BeeFamily object to add
     * @return the added BeeFamily with assigned ID
     */
    public BeeFamily addBeeFamily(BeeFamily beeFamily) {
        log.info("Adding new bee family: {}", beeFamily);
        int newID = countID++;
        beeFamily.setId(newID);
        beeFamilies.put(newID, beeFamily);
        log.info("Repo. Bee family added with ID {}: {}", newID, beeFamily);
        return beeFamily;
    }

    /**
     * Deletes a bee family by its ID.
     *
     * @param beeFamilyId the ID of the bee family to delete
     * @return success message
     * @throws BeeFamilyNotFoundException if the bee family does not exist
     */
    public String deleteBeeFamily(int beeFamilyId) {
        log.info("Deleting bee family with ID: {}", beeFamilyId);
        if (!beeFamilies.containsKey(beeFamilyId)) {
            throw new BeeFamilyNotFoundException("BeeFamily with ID " + beeFamilyId + " not found");
        }
        beeFamilies.remove(beeFamilyId);
        log.info("Repo. Bee family with ID {} deleted", beeFamilyId);
        return "BeeFamily was deleted";
    }
}
