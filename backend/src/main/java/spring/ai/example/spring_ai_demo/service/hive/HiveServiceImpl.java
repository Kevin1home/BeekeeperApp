package spring.ai.example.spring_ai_demo.service.hive;

import org.springframework.stereotype.Service;
import spring.ai.example.spring_ai_demo.model.hive.Hive;
import spring.ai.example.spring_ai_demo.repository.hive.HiveRepository;

import java.util.List;

@Service
public class HiveServiceImpl implements HiveService {
    private final HiveRepository hiveRepository = new HiveRepository();

    @Override
    public List<Hive> getAllHives() {
        return hiveRepository.getAllHives();
    }

    @Override
    public Hive getHiveById(int hiveId) {
        return hiveRepository.getHiveById(hiveId);
    }

    @Override
    public Hive addHive(Hive hive) {
        return hiveRepository.addHive(hive);
    }

    @Override
    public String deleteHive(int hiveId) {
        return hiveRepository.deleteHive(hiveId);
    }
}
