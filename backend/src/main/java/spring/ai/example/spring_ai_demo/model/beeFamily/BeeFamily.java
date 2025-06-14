package spring.ai.example.spring_ai_demo.model.beeFamily;

import lombok.Data;

@Data
public class BeeFamily {
    private int id;
    private String beeFamilyName;
    private BeeFamilyType beeFamilyType;
    private BeeFamilyPower beeFamilyPower;

    public BeeFamily(String beeFamilyName, BeeFamilyType beeFamilyType, BeeFamilyPower beeFamilyPower) {
        this.beeFamilyName = beeFamilyName;
        this.beeFamilyType = beeFamilyType;
        this.beeFamilyPower = beeFamilyPower;
    }
}
