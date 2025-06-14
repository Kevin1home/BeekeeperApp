package spring.ai.example.spring_ai_demo.service.beeFamily;

import spring.ai.example.spring_ai_demo.model.beeFamily.BeeFamily;
import spring.ai.example.spring_ai_demo.model.hive.Hive;

import java.util.List;

public interface BeeFamilyService {
    List<BeeFamily> getAllBeeFamilies();
    BeeFamily getBeeFamilyById(int beeFamilyId);
    BeeFamily addBeeFamily(BeeFamily beeFamily);
    String deleteBeeFamily(int beeFamilyId);
}
