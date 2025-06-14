package spring.ai.example.spring_ai_demo.repository.hive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import spring.ai.example.spring_ai_demo.model.hive.Hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class HiveRepository {
    private int countID = 0;
    private final Map<Integer, Hive> hives = new HashMap<>();

    public List<Hive> getAllHives() {
        log.info("HiveRepository, actual hives: " + hives);
        return hives.values().stream().toList();
    }

    public Hive getHiveById(int hiveId) {
        log.info("HiveRepository, actual hives: " + hives);
        return hives.get(hiveId);
    }

    public Hive addHive(Hive hive) {
        log.info("HiveRepository, hives BEFORE adding a new hive: " + hives);
        int newID = countID++;
        hive.setId(newID);
        log.info("Received ID: " + newID);
        hives.put(newID, hive);
        log.info("HiveRepository, hive AFTER adding a new hive: " + hives);
        return hives.get(newID);
    }

    public String deleteHive(int hiveId) {
        log.info("HiveRepository, hives BEFORE deleting a hive: " + hives);
        hives.remove(hiveId);
        log.info("HiveRepository, hives AFTER deleting a hive: " + hives);
        return "Hive was deleted";
    }
}
