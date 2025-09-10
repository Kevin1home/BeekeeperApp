package com.beekeeperApp.repository.hive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.beekeeperApp.exception.HiveAlreadyExistException;
import com.beekeeperApp.exception.HiveNotFoundException;
import com.beekeeperApp.model.hive.Hive;
import com.beekeeperApp.model.hive.HiveMaterial;
import com.beekeeperApp.model.hive.HiveType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Database-backed implementation of HiveRepo.
 * <p>
 * Provides CRUD operations for Hive entities using JDBC.
 * Exceptions are thrown if the requested entity does not exist
 * or if a duplicate is being added.
 * </p>
 */
// TODO: ControllerAdvice for exceptions
@Slf4j
@Repository("DbHiveRepo")
public class DbHiveRepo implements HiveRepo {
    private final JdbcTemplate jdbcTemplate;

    public DbHiveRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves all hives from the database.
     * @return list of all Hive objects
     */
    @Override
    public List<Hive> getAllHives() {
        String sql = "SELECT h.name, h.material, h.type, h.frames_per_body " +
                "FROM hive as h";
        List<Hive> allHives = jdbcTemplate.query(sql, (rs, rowNum) -> makeHive(rs));

        log.info("Repo. There are {} hives found", allHives.size());
        return allHives;
    }

    /**
     * Retrieves a hive by ID.
     *
     * @param hiveId the ID of the hive
     * @return the Hive if found
     * @throws HiveNotFoundException if no hive exists with the given ID
     */
    @Override
    public Hive getHiveById(int hiveId) {
        String sql = "SELECT h.name, h.material, h.type, h.frames_per_body " +
                "FROM hive as h " +
                "WHERE h.id=?";
        List<Hive> hiveList = jdbcTemplate.query(sql, (rs, rowNum) -> makeHive(rs), hiveId);

        if (hiveList.isEmpty()) {
            throw new HiveNotFoundException("No Hive under such ID found");
        }
        return hiveList.get(hiveList.size() - 1);
    }

    /**
     * Adds a new hive to the database.
     *
     * @param hive the Hive object to add
     * @return the saved Hive with generated ID
     * @throws HiveAlreadyExistException if an identical hive already exists
     */
    @Override
    public Hive addHive(Hive hive) {
        String name = hive.getHiveName();
        log.info("Hive name: {}", name);
        String material = hive.getHiveMaterial().toString();
        log.info("Hive material: {}", material);
        String type = hive.getHiveType().toString();
        log.info("Hive type: {}", type);
        int framesPerBody = hive.getFramesPerBody();
        log.info("Hive framesPerBody: {}", framesPerBody);

        String checkSql = "SELECT COUNT(*) FROM hive WHERE name = ? AND material = ? AND type  = ? " +
                "AND frames_per_body = ?";
        Integer count = jdbcTemplate.queryForObject(
                checkSql, Integer.class, name, material, type, framesPerBody
        );
        if (count > 0) {
            throw new HiveAlreadyExistException("Hive already exists");
        }

        String sql = "INSERT INTO hive (name, material, type, frames_per_body) " +
                "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int qnt = jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"});
            stmt.setString(1, name);
            stmt.setString(2, material);
            stmt.setString(3, type);
            stmt.setInt(4, framesPerBody);
            return stmt;
        }, keyHolder);
        log.info("Repo. Added {} new records to table hive", qnt);

        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        hive.setId(id);
        return hive;
    }

    /**
     * Deletes a hive by ID.
     *
     * @param hiveId the ID of the hive to delete
     * @return success message if deletion is performed
     * @throws HiveNotFoundException if no hive exists with the given ID
     */
    @Override
    public String deleteHive(int hiveId) {
        String checkSql = "SELECT COUNT(*) FROM hive WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(
                checkSql, Integer.class, hiveId
        );
        if (count == 0) {
            throw new HiveNotFoundException("No Hive under such ID found");
        }

        String sql = "DELETE FROM hive WHERE id = ?";

        int qnt = jdbcTemplate.update(sql, hiveId);
        log.info("Repo. Deleted {} records from table hive", qnt);
        return "Hive was deleted";
    }

    // ----------------------
    // Private helper methods
    // ----------------------

    private Hive makeHive(ResultSet rs) throws SQLException {
        String hiveName = rs.getString("name");
        HiveMaterial hiveMaterial = transformHiveMaterialToEnum(rs.getString("material"));
        HiveType hiveType = transformHiveTypeToEnum(rs.getString("type"));
        int framesPerBody = rs.getInt("frames_per_body");

        return new Hive(hiveName, hiveMaterial, hiveType, framesPerBody);
    }

    private HiveMaterial transformHiveMaterialToEnum(String hiveMaterial) {
        if (hiveMaterial == null || hiveMaterial.isBlank()) {
            return HiveMaterial.UNKNOWN_HIVE_MATERIAL;
        }

        return switch (hiveMaterial.toUpperCase().replaceAll("[\\s-]", "_")) {
            case "WOOD" -> HiveMaterial.WOOD;
            case "PLASTIC" -> HiveMaterial.PLASTIC;
            case "POLYSTYRENE" -> HiveMaterial.POLYSTYRENE;
            case "POLYURETHANE" -> HiveMaterial.POLYURETHANE;
            case "METAL" -> HiveMaterial.METAL;
            case "STAINLESS_STEEL" -> HiveMaterial.STAINLESS_STEEL;
            case "ALUMINUM" -> HiveMaterial.ALUMINUM;
            case "CERAMIC" -> HiveMaterial.CERAMIC;
            case "BAMBOO" -> HiveMaterial.BAMBOO;
            case "STRAW" -> HiveMaterial.STRAW;
            case "GRASS" -> HiveMaterial.GRASS;
            case "REED" -> HiveMaterial.REED;
            case "CARDBOARD" -> HiveMaterial.CARDBOARD;
            case "HARD_FOAM" -> HiveMaterial.HARD_FOAM;
            case "FIBERGLASS" -> HiveMaterial.FIBERGLASS;
            case "CEMENT" -> HiveMaterial.CEMENT;
            case "CONCRETE" -> HiveMaterial.CONCRETE;
            case "PVC" -> HiveMaterial.PVC;
            case "GLASS" -> HiveMaterial.GLASS;
            case "COMPOSITE_MATERIALS" -> HiveMaterial.COMPOSITE_MATERIALS;
            case "RECYCLED_MATERIALS" -> HiveMaterial.RECYCLED_MATERIALS;
            default -> HiveMaterial.UNKNOWN_HIVE_MATERIAL;
        };
    }

    private HiveType transformHiveTypeToEnum(String hiveType) {
        if (hiveType == null || hiveType.isBlank()) {
            return HiveType.UNKNOWN_HIVE_TYPE;
        }

        return switch (hiveType.toUpperCase().replaceAll("[\\s-]", "_")) {
            case "DADANT" -> HiveType.DADANT;
            case "LANGSTROTH" -> HiveType.LANGSTROTH;
            case "LAYENS" -> HiveType.LAYENS;
            case "WARRÉ", "WARRE" -> // на случай отсутствия акцента
                    HiveType.WARRÉ;
            case "TOP_BAR" -> HiveType.TOP_BAR;
            case "KENYAN_TOP_BAR_HIVE" -> HiveType.KENYAN_TOP_BAR_HIVE;
            case "FLOW_HIVE" -> HiveType.FLOW_HIVE;
            case "AZ_HIVE" -> HiveType.AZ_HIVE;
            case "ZANDER" -> HiveType.ZANDER;
            case "ALPINE_HIVE" -> HiveType.ALPINE_HIVE;
            case "DANDANT_BLATT" -> HiveType.DANDANT_BLATT;
            case "UKRAINIAN_HIVE" -> HiveType.UKRAINIAN_HIVE;
            case "RUSSIAN_HIVE" -> HiveType.RUSSIAN_HIVE;
            case "JUMBO" -> HiveType.JUMBO;
            case "MINI_PLUS" -> HiveType.MINI_PLUS;
            case "NUC_BOX" -> HiveType.NUC_BOX;
            case "SQUARE_HIVE" -> HiveType.SQUARE_HIVE;
            case "ROUNDED_BASKET_HIVE" -> HiveType.ROUNDED_BASKET_HIVE;
            case "SKERP" -> HiveType.SKERP;
            case "SKEP" -> HiveType.SKEP;
            case "LOG_HIVE" -> HiveType.LOG_HIVE;
            case "TREE_HIVE" -> HiveType.TREE_HIVE;
            case "TROUGH_HIVE" -> HiveType.TROUGH_HIVE;
            case "PERONE_HIVE" -> HiveType.PERONE_HIVE;
            case "HORIZONTAL_HIVE" -> HiveType.HORIZONTAL_HIVE;
            case "VERTICAL_HIVE" -> HiveType.VERTICAL_HIVE;
            case "LONG_LANGSTROTH" -> HiveType.LONG_LANGSTROTH;
            case "INSULATED_HIVE" -> HiveType.INSULATED_HIVE;
            case "EINAR_HIVE" -> HiveType.EINAR_HIVE;
            case "WBC_HIVE" -> HiveType.WBC_HIVE;
            case "COMMERCIAL_HIVE" -> HiveType.COMMERCIAL_HIVE;
            case "NATIONAL_HIVE" -> HiveType.NATIONAL_HIVE;
            case "SMALL_CELL_HIVE" -> HiveType.SMALL_CELL_HIVE;
            case "TANZANIAN_TOP_BAR_HIVE" -> HiveType.TANZANIAN_TOP_BAR_HIVE;
            case "GREEK_HIVE" -> HiveType.GREEK_HIVE;
            case "TURKISH_HIVE" -> HiveType.TURKISH_HIVE;
            case "GERMAN_NORMAL_HIVE" -> HiveType.GERMAN_NORMAL_HIVE;
            case "MODIFIED_DADANT" -> HiveType.MODIFIED_DADANT;
            case "CARNICA_HIVE" -> HiveType.CARNICA_HIVE;
            default -> HiveType.UNKNOWN_HIVE_TYPE;
        };
    }
}
