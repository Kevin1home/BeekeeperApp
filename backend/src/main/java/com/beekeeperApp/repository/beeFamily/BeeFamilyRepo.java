package com.beekeeperApp.repository.beeFamily;

import com.beekeeperApp.model.beeFamily.BeeFamily;

import java.util.List;

/**
 * Repository interface for managing BeeFamily entities.
 * <p>
 * Defines CRUD operations for bee families.
 * Implementations may interact with databases, in-memory storage, or other persistence layers.
 * </p>
 */
public interface BeeFamilyRepo {
    /**
     * Retrieves all bee families.
     * @return a list of all BeeFamily objects
     */
    List<BeeFamily> getAllBeeFamilies();

    /**
     * Retrieves a bee family by its ID.
     *
     * @param beeFamilyId unique identifier of the bee family
     * @return the BeeFamily instance if found
     */
    BeeFamily getBeeFamilyById(int beeFamilyId);

    /**
     * Adds a new bee family to the repository.
     *
     * @param beeFamily the BeeFamily object to add
     * @return the added BeeFamily instance
     */
    BeeFamily addBeeFamily(BeeFamily beeFamily);

    /**
     * Deletes a bee family by its ID.
     *
     * @param beeFamilyId unique identifier of the bee family to delete
     * @return a String response "BeeFamily was deleted" if succeeded
     */
    String deleteBeeFamily(int beeFamilyId);
}
