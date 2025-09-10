package com.beekeeperApp.repository.hive;

import com.beekeeperApp.model.hive.Hive;

import java.util.List;

/**
 * Repository interface for managing Hive entities.
 * <p>
 * Defines CRUD operations for hives.
 * Implementations may interact with databases, in-memory storage, or other persistence layers.
 * </p>
 */
public interface HiveRepo {
    /**
     * Retrieves all hives.
     * @return a list of all Hives objects
     */
    List<Hive> getAllHives();

    /**
     * Retrieves a hive by its ID.
     *
     * @param hiveId unique identifier of the hive
     * @return the Hive instance if found
     */
    Hive getHiveById(int hiveId);

    /**
     * Adds a new hive to the repository.
     *
     * @param hive the Hive object to add
     * @return the added Hive instance
     */
    Hive addHive(Hive hive);

    /**
     * Deletes a hive by its ID.
     *
     * @param hiveId unique identifier of the hive to delete
     * @return a String response "Hive was deleted" if succeeded
     */
    String deleteHive(int hiveId);
}
