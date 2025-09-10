package com.beekeeperApp.service.hive;

import com.beekeeperApp.model.hive.Hive;

import java.util.List;

/**
 * Service interface for managing Hive entities.
 * <p>
 * Provides CRUD operations for hives. Implementations may interact with
 * databases or in-memory repositories.
 * </p>
 */
public interface HiveService {
    List<Hive> getAllHives();
    Hive getHiveById(int hiveId);
    Hive addHive(Hive hive);
    String deleteHive(int hiveId);
}
