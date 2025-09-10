package com.beekeeperApp.model.beeFamily;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

/**
 * Represents a Bee Family entity with a name, type, and power.
 * This class is used as a model in the application.
 */
@Data
public class BeeFamily {
    private int id;
    @NotBlank(message = "Bee family name cannot be empty")
    @Size(max = 64, message = "Bee family name cannot be longer than 64 characters")
    private String beeFamilyName;
    @NotNull
    private BeeFamilyType beeFamilyType;
    @NotNull
    private BeeFamilyPower beeFamilyPower;

    /**
     * Constructs a new BeeFamily instance with the specified name, type, and power.
     *
     * @param beeFamilyName  the name of the bee family (must not be null or blank)
     * @param beeFamilyType  the type of the bee family (must not be null)
     * @param beeFamilyPower the power of the bee family (must not be null)
     */
    public BeeFamily(@NonNull String beeFamilyName, @NonNull BeeFamilyType beeFamilyType,
                     @NonNull BeeFamilyPower beeFamilyPower) {
        this.beeFamilyName = beeFamilyName;
        this.beeFamilyType = beeFamilyType;
        this.beeFamilyPower = beeFamilyPower;
    }
}
