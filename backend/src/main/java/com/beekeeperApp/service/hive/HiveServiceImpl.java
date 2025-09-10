package com.beekeeperApp.service.hive;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.beekeeperApp.model.hive.Hive;
import com.beekeeperApp.repository.hive.HiveRepo;

import java.util.List;

/**
 * Implementation of HiveService.
 * <p>
 * Provides CRUD operations for Hive entities.
 * Delegates persistence operations HiveRepo.
 * </p>
 */
@Service
@Slf4j
@Getter
public class HiveServiceImpl implements HiveService {

    private final HiveRepo hiveRepository;

    public HiveServiceImpl(@Qualifier("DbHiveRepo") HiveRepo hiveRepository) {
        this.hiveRepository = hiveRepository;
    }

    /**
     * Retrieves all hives.
     * @return list of Hive
     */
    @Override
    public List<Hive> getAllHives() {
        List<Hive> hives = hiveRepository.getAllHives();
        log.info("Service. Retrieved {} hives", hives.size());
        return hives;
    }

    /**
     * Retrieves a hive by its ID.
     *
     * @param hiveId the ID of the hive
     * @return the Hive object if exists
     */
    @Override
    public Hive getHiveById(int hiveId) {
        Hive hive = hiveRepository.getHiveById(hiveId);
        log.info("Service. Retrieved hive with ID {}: {}", hiveId, hive);
        return hive;
    }

    /**
     * Adds a new hive.
     *
     * @param hive the Hive to add
     * @return the added Hive with assigned ID
     */
    @Override
    public Hive addHive(Hive hive) {
        Hive addedHive = hiveRepository.addHive(hive);
        log.info("Service. Added new hive: {}", addedHive);
        return addedHive;
    }

    /**
     * Deletes a hive by its ID.
     *
     * @param hiveId the ID of the hive to delete
     * @return success message if succeeded
     */
    @Override
    public String deleteHive(int hiveId) {
        String result = hiveRepository.deleteHive(hiveId);
        log.info("Service. Deleted hive with ID {}: {}", hiveId, result);
        return result;
    }
}
