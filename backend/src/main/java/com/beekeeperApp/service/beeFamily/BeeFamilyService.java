package com.beekeeperApp.service.beeFamily;

import com.beekeeperApp.model.beeFamily.BeeFamily;

import java.util.List;

/**
 * Service interface for managing BeeFamily entities.
 * <p>
 * Provides CRUD operations for bee families. Implementations may interact with
 * databases or in-memory repositories.
 * </p>
 */
public interface BeeFamilyService {
    List<BeeFamily> getAllBeeFamilies();
    BeeFamily getBeeFamilyById(int beeFamilyId);
    BeeFamily addBeeFamily(BeeFamily beeFamily);
    String deleteBeeFamily(int beeFamilyId);
}
