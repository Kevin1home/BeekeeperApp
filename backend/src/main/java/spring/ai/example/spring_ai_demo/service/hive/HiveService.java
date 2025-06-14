package spring.ai.example.spring_ai_demo.service.hive;

import spring.ai.example.spring_ai_demo.model.hive.Hive;

import java.util.List;

public interface HiveService {
    List<Hive> getAllHives();
    Hive getHiveById(int hiveId);
    Hive addHive(Hive hive);
    String deleteHive(int hiveId);
}
