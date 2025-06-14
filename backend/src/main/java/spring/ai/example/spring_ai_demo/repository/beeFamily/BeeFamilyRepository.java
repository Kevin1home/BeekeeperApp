package spring.ai.example.spring_ai_demo.repository.beeFamily;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import spring.ai.example.spring_ai_demo.model.beeFamily.BeeFamily;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class BeeFamilyRepository {
    private int countID = 0;
    private final Map<Integer, BeeFamily> beeFamilies = new HashMap<>();

    public List<BeeFamily> getAllBeeFamilies() {
        log.info("BeeFamilyRepository, actual beeFamilies: " + beeFamilies);
        return beeFamilies.values().stream().toList();
    }

    public BeeFamily getBeeFamilyById(int beeFamilyId) {
        log.info("BeeFamilyRepository, actual beeFamilies: " + beeFamilies);
        return beeFamilies.get(beeFamilyId);
    }

    public BeeFamily addBeeFamily(BeeFamily beeFamily) {
        log.info("BeeFamilyRepository, beeFamilies BEFORE adding a new beeFamily: " + beeFamilies);
        int newID = countID++;
        beeFamily.setId(newID);
        log.info("Received ID: " + newID);
        beeFamilies.put(newID, beeFamily);
        log.info("BeeFamilyRepository, beeFamilies AFTER adding a new beeFamily: " + beeFamilies);
        return beeFamilies.get(newID);
    }

    public String deleteBeeFamily(int beeFamilyId) {
        log.info("BeeFamilyRepository, beeFamilies BEFORE deleting a beeFamily: " + beeFamilies);
        beeFamilies.remove(beeFamilyId);
        log.info("BeeFamilyRepository, beeFamilies AFTER deleting a beeFamily: " + beeFamilies);
        return "BeeFamily was deleted";
    }
}
