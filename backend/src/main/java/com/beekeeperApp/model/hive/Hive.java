package com.beekeeperApp.model.hive;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a Hive entity with a name, material, type and framesPerBody.
 * This class is used as a model in the application.
 */
@Data
public class Hive {
    private int id;
    @NotBlank(message = "Hive name cannot be empty")
    @Size(max = 64, message = "Hive name cannot be longer than 64 characters")
    private String hiveName;
    @NotNull
    private HiveMaterial hiveMaterial;
    @NotNull
    private HiveType hiveType;
    @NotNull
    @Positive
    private Integer framesPerBody;

    /**
     * Constructs a new Hive instance with the specified name, material, type and framesPerBody.
     *
     * @param hiveName  the name of the hive (must not be null or blank)
     * @param hiveMaterial  the type of the hive (must not be null)
     * @param hiveType the power of the hive (must not be null)
     * @param framesPerBody the power of the hive (must not be null but positive)
     */
    public Hive(@NonNull String hiveName, @NonNull HiveMaterial hiveMaterial, @NonNull HiveType hiveType,
                @NonNull Integer framesPerBody) {
        this.hiveName = hiveName;
        this.hiveMaterial = hiveMaterial;
        this.hiveType = hiveType;
        this.framesPerBody = framesPerBody;
    }
}
