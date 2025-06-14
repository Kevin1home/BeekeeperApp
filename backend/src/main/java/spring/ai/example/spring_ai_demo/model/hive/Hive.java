package spring.ai.example.spring_ai_demo.model.hive;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Hive {
    private int id;
    private String hiveName;
    private HiveMaterial hiveMaterial;
    private HiveType hiveType;
    private int framesPerBody;

    public Hive(String hiveName, HiveMaterial hiveMaterial, HiveType hiveType, int framesPerBody) {
        this.hiveName = hiveName;
        this.hiveMaterial = hiveMaterial;
        this.hiveType = hiveType;
        this.framesPerBody = framesPerBody;
    }
}
