package com.beekeeperApp.repository.hive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.beekeeperApp.exception.HiveNotFoundException;
import com.beekeeperApp.model.hive.Hive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of HiveRepo.
 * <p>
 * Stores Hive entities in a HashMap for quick access.
 * Useful for testing.
 * </p>
 */
@Slf4j
@Repository
public class InMemoryHiveRepo implements HiveRepo {
    private int countID = 0;
    private final Map<Integer, Hive> hives = new HashMap<>();

    /**
     * Retrieves all hives.
     * @return list of all Hive objects
     */
    public List<Hive> getAllHives() {
        log.info("Repo. Retrieving all hives: {}", hives);
        return hives.values().stream().toList();
    }

    /**
     * Retrieves a hive by its ID.
     *
     * @param hiveId the ID of the hive
     * @return the Hive object
     * @throws HiveNotFoundException if the hive does not exist
     */
    public Hive getHiveById(int hiveId) {
        log.info("Fetching hive with ID: {}", hiveId);
        Hive hive = hives.get(hiveId);
        if (hive == null) {
            throw new HiveNotFoundException("Hive with ID " + hiveId + " not found");
        }
        return hive;
    }

    /**
     * Adds a new hive to the repository.
     *
     * @param hive the Hive object to add
     * @return the added Hive with assigned ID
     */
    public Hive addHive(Hive hive) {
        log.info("Adding new hive: {}", hive);
        int newID = countID++;
        hive.setId(newID);
        hives.put(newID, hive);
        log.info("Repo. Hive added with ID {}: {}", newID, hive);
        return hive;
    }

    /**
     * Deletes a hive by its ID.
     *
     * @param hiveId the ID of the hive to delete
     * @return success message
     * @throws HiveNotFoundException if the hive does not exist
     */
    public String deleteHive(int hiveId) {
        log.info("Deleting hive with ID: {}", hiveId);
        if (!hives.containsKey(hiveId)) {
            throw new HiveNotFoundException("Hive with ID " + hiveId + " not found");
        }
        hives.remove(hiveId);
        log.info("Repo. Hive with ID {} deleted", hiveId);
        return "Hive was deleted";
    }
}
