package com.beekeeperApp.service.beeFamily;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.beekeeperApp.model.beeFamily.BeeFamily;
import com.beekeeperApp.repository.beeFamily.BeeFamilyRepo;

import java.util.List;

/**
 * Implementation of BeeFamilyService.
 * <p>
 * Provides CRUD operations for BeeFamily entities.
 * Delegates persistence operations BeeFamilyRepo.
 * </p>
 */
@Service
@Slf4j
@Getter
public class BeeFamilyServiceImpl implements BeeFamilyService {

    private final BeeFamilyRepo beeFamilyRepository;

    public BeeFamilyServiceImpl(@Qualifier("DbBeeFamilyRepo") BeeFamilyRepo beeFamilyRepository) {
        this.beeFamilyRepository = beeFamilyRepository;
    }

    /**
     * Retrieves all bee families.
     * @return list of BeeFamily
     */
    @Override
    public List<BeeFamily> getAllBeeFamilies() {
        List<BeeFamily> beeFamilies = beeFamilyRepository.getAllBeeFamilies();
        log.info("Service. Retrieved {} bee families", beeFamilies.size());
        return beeFamilies;
    }

    /**
     * Retrieves a bee family by its ID.
     *
     * @param beeFamilyId the ID of the bee family
     * @return the BeeFamily object if exists
     */
    @Override
    public BeeFamily getBeeFamilyById(int beeFamilyId) {
        BeeFamily beeFamily = beeFamilyRepository.getBeeFamilyById(beeFamilyId);
        log.info("Service. Retrieved bee family with ID {}: {}", beeFamilyId, beeFamily);
        return beeFamily;
    }

    /**
     * Adds a new bee family.
     *
     * @param beeFamily theBeeFamily to add
     * @return the added BeeFamily with assigned ID
     */
    @Override
    public BeeFamily addBeeFamily(BeeFamily beeFamily) {
        BeeFamily addedBeeFamily = beeFamilyRepository.addBeeFamily(beeFamily);
        log.info("Service. Added new bee family: {}", addedBeeFamily);
        return addedBeeFamily;
    }

    /**
     * Deletes a bee family by its ID.
     *
     * @param beeFamilyId the ID of the bee family to delete
     * @return success message if succeeded
     */
    @Override
    public String deleteBeeFamily(int beeFamilyId) {
        String result = beeFamilyRepository.deleteBeeFamily(beeFamilyId);
        log.info("Service. Deleted bee family with ID {}: {}", beeFamilyId, result);
        return result;
    }
}
