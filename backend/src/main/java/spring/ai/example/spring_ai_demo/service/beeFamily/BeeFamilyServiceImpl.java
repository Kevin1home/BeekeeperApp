package spring.ai.example.spring_ai_demo.service.beeFamily;

import org.springframework.stereotype.Service;
import spring.ai.example.spring_ai_demo.model.beeFamily.BeeFamily;
import spring.ai.example.spring_ai_demo.repository.beeFamily.BeeFamilyRepository;

import java.util.List;

@Service
public class BeeFamilyServiceImpl implements BeeFamilyService {
    private final BeeFamilyRepository beeFamilyRepository = new BeeFamilyRepository();

    @Override
    public List<BeeFamily> getAllBeeFamilies() {
        return beeFamilyRepository.getAllBeeFamilies();
    }

    @Override
    public BeeFamily getBeeFamilyById(int beeFamilyId) {
        return beeFamilyRepository.getBeeFamilyById(beeFamilyId);
    }

    @Override
    public BeeFamily addBeeFamily(BeeFamily beeFamily) {
        return beeFamilyRepository.addBeeFamily(beeFamily);
    }

    @Override
    public String deleteBeeFamily(int beeFamily) {
        return beeFamilyRepository.deleteBeeFamily(beeFamily);
    }
}
