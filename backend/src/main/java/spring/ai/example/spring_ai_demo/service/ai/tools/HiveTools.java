/*
package spring.ai.example.spring_ai_demo.service.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import spring.ai.example.spring_ai_demo.model.hive.Hive;
import spring.ai.example.spring_ai_demo.service.hive.HiveService;
import spring.ai.example.spring_ai_demo.service.hive.HiveServiceImpl;

public class HiveTools {
    private final HiveService hiveService = new HiveServiceImpl();

    @Tool(description = "GET all Hives") String getAllHives() {
        return hiveService.getAllHives().toString();
    }

    @Tool(description = "GET Hive by given Hive-ID") String getHiveById(int hiveId) {
        return hiveService.getHiveById(hiveId).toString();
    }

    @Tool(description = "ADD Hive by given Hive-ID") String addHive(Hive hive) {
        return hiveService.addHive(hive).toString();
    }

    @Tool(description = "DELETE Hive by given Hive-ID") String deleteHive(int hiveId) {
        return hiveService.deleteHive(hiveId);
    }

}
*/
