package com.beekeeperApp.repository.beeFamily;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.beekeeperApp.exception.BeeFamilyAlreadyExistException;
import com.beekeeperApp.exception.BeeFamilyNotFoundException;
import com.beekeeperApp.model.beeFamily.BeeFamily;
import com.beekeeperApp.model.beeFamily.BeeFamilyPower;
import com.beekeeperApp.model.beeFamily.BeeFamilyType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Database-backed implementation of BeeFamilyRepo.
 * <p>
 * Provides CRUD operations for BeeFamily entities using JDBC.
 * Exceptions are thrown if the requested entity does not exist
 * or if a duplicate is being added.
 * </p>
 */
// TODO: ControllerAdvice for exceptions
@Slf4j
@Repository("DbBeeFamilyRepo")
public class DbBeeFamilyRepo implements BeeFamilyRepo {
    private final JdbcTemplate jdbcTemplate;

    public DbBeeFamilyRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves all bee families from the database.
     * @return list of all BeeFamily objects
     */
    @Override
    public List<BeeFamily> getAllBeeFamilies() {
        String sql = "SELECT bf.name, bf.type, bf.power " +
                "FROM bee_family as bf";
        List<BeeFamily> allBeeFamilies = jdbcTemplate.query(sql, (rs, rowNum) -> makeBeeFamily(rs));

        log.info("Repo. There are {} beeFamilies found", allBeeFamilies.size());
        return allBeeFamilies;
    }

    /**
     * Retrieves a bee family by ID.
     *
     * @param beeFamilyId the ID of the bee family
     * @return the BeeFamily if found
     * @throws BeeFamilyNotFoundException if no bee family exists with the given ID
     */
    @Override
    public BeeFamily getBeeFamilyById(int beeFamilyId) {
        String sql = "SELECT bf.name, bf.type, bf.power " +
                "FROM bee_family as bf " +
                "WHERE bf.id=?";
        List<BeeFamily> beeFamilyList = jdbcTemplate.query(sql, (rs, rowNum) -> makeBeeFamily(rs), beeFamilyId);

        if (beeFamilyList.isEmpty()) {
            throw new BeeFamilyNotFoundException("No BeeFamily under such ID found");
        }
        return beeFamilyList.get(beeFamilyList.size() - 1);
    }

    /**
     * Adds a new bee family to the database.
     *
     * @param beeFamily the BeeFamily object to add
     * @return the saved BeeFamily with generated ID
     * @throws BeeFamilyAlreadyExistException if an identical bee family already exists
     */
    @Override
    public BeeFamily addBeeFamily(BeeFamily beeFamily) {
        String name = beeFamily.getBeeFamilyName();
        String type = beeFamily.getBeeFamilyType().toString();
        String power = beeFamily.getBeeFamilyPower().toString();

        // Checking for duplicates
        String checkSql = "SELECT COUNT(*) FROM bee_family WHERE name = ? AND type = ? AND power  = ?";
        Integer count = jdbcTemplate.queryForObject(
                checkSql, Integer.class, name, type, power
        );
        if (count > 0) {
            throw new BeeFamilyAlreadyExistException("BeeFamily already exists");
        }

        String sql = "INSERT INTO bee_family (name, type, power) " +
                "VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int qnt = jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"});
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3, power);
            return stmt;
        }, keyHolder);
        log.info("Repo. Added {} new records to table bee_family", qnt);

        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        beeFamily.setId(id);
        return beeFamily;
    }

    /**
     * Deletes a bee family by ID.
     *
     * @param beeFamilyId the ID of the bee family to delete
     * @return success message if deletion is performed
     * @throws BeeFamilyNotFoundException if no bee family exists with the given ID
     */
    @Override
    public String deleteBeeFamily(int beeFamilyId) {
        String checkSql = "SELECT COUNT(*) FROM bee_family WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(
                checkSql, Integer.class, beeFamilyId
        );
        if (count == 0) {
            throw new BeeFamilyNotFoundException("No BeeFamily under such ID found");
        }

        String sql = "DELETE FROM bee_family WHERE id = ?";

        int qnt = jdbcTemplate.update(sql, beeFamilyId);
        log.info("Repo. Deleted {} records from table bee_family", qnt);
        return "BeeFamily was deleted";
    }

    // ----------------------
    // Private helper methods
    // ----------------------

    private BeeFamily makeBeeFamily(ResultSet rs) throws SQLException {
        String beeFamilyName = rs.getString("name");
        BeeFamilyType beeFamilyType = transformBeeFamilyTypeToEnum(rs.getString("type"));
        BeeFamilyPower beeFamilyPower = transformBeeFamilyPowerToEnum(rs.getString("power"));

        return new BeeFamily(beeFamilyName, beeFamilyType, beeFamilyPower);
    }

    private BeeFamilyType transformBeeFamilyTypeToEnum(String beeFamilyType) {
        if (beeFamilyType == null || beeFamilyType.isBlank()) {
            return BeeFamilyType.UNKNOWN_BEEFAMILY_TYPE;
        }

        return switch (beeFamilyType.toUpperCase().replaceAll("[\\s.-]", "_")) {
            case "APIS_MELLIFERA_LIGUSTICA" -> BeeFamilyType.APIS_MELLIFERA_LIGUSTICA;
            case "APIS_MELLIFERA_CARNICA" -> BeeFamilyType.APIS_MELLIFERA_CARNICA;
            case "APIS_MELLIFERA_CYPRIA" -> BeeFamilyType.APIS_MELLIFERA_CYPRIA;
            case "APIS_MELLIFERA_CECROPIA" -> BeeFamilyType.APIS_MELLIFERA_CECROPIA;
            case "APIS_MELLIFERA_MACEDONICA" -> BeeFamilyType.APIS_MELLIFERA_MACEDONICA;
            case "APIS_MELLIFERA_RUTTNERI" -> BeeFamilyType.APIS_MELLIFERA_RUTTNERI;
            case "APIS_MELLIFERA_SAHARIENSIS" -> BeeFamilyType.APIS_MELLIFERA_SAHARIENSIS;
            case "APIS_MELLIFERA_SCUTELLATA" -> BeeFamilyType.APIS_MELLIFERA_SCUTELLATA;
            case "APIS_MELLIFERA_CAPENSIS" -> BeeFamilyType.APIS_MELLIFERA_CAPENSIS;
            case "APIS_MELLIFERA_YEMENITICA" -> BeeFamilyType.APIS_MELLIFERA_YEMENITICA;
            case "APIS_MELLIFERA_SYRIACA" -> BeeFamilyType.APIS_MELLIFERA_SYRIACA;
            case "APIS_MELLIFERA_ANATOLICA" -> BeeFamilyType.APIS_MELLIFERA_ANATOLICA;
            case "APIS_MELLIFERA_CAUCASICA" -> BeeFamilyType.APIS_MELLIFERA_CAUCASICA;
            case "APIS_MELLIFERA_ADANSONII" -> BeeFamilyType.APIS_MELLIFERA_ADANSONII;
            case "APIS_MELLIFERA_MONTICOLA" -> BeeFamilyType.APIS_MELLIFERA_MONTICOLA;
            case "APIS_MELLIFERA_LAMARCKII" -> BeeFamilyType.APIS_MELLIFERA_LAMARCKII;
            case "APIS_MELLIFERA_UNCINATA" -> BeeFamilyType.APIS_MELLIFERA_UNCINATA;
            case "APIS_MELLIFERA_MELLIFERA" -> BeeFamilyType.APIS_MELLIFERA_MELLIFERA;
            case "APIS_MELLIFERA_IBERIENSIS" -> BeeFamilyType.APIS_MELLIFERA_IBERIENSIS;
            case "APIS_MELLIFERA_INTERMISSA" -> BeeFamilyType.APIS_MELLIFERA_INTERMISSA;
            case "APIS_MELLIFERA_SINISXITANICA" -> BeeFamilyType.APIS_MELLIFERA_SINISXITANICA;
            case "APIS_MELLIFERA_LEHMANNI" -> BeeFamilyType.APIS_MELLIFERA_LEHMANNI;
            case "APIS_MELLIFERA_SAUDITA" -> BeeFamilyType.APIS_MELLIFERA_SAUDITA;
            case "APIS_CERANA" -> BeeFamilyType.APIS_CERANA;
            case "APIS_DORSATA" -> BeeFamilyType.APIS_DORSATA;
            case "APIS_FLOREA" -> BeeFamilyType.APIS_FLOREA;
            case "APIS_LABORIOSA" -> BeeFamilyType.APIS_LABORIOSA;
            default -> BeeFamilyType.UNKNOWN_BEEFAMILY_TYPE;
        };
    }

    private BeeFamilyPower transformBeeFamilyPowerToEnum(String beeFamilyPower) {
        if (beeFamilyPower == null || beeFamilyPower.isBlank()) {
            return BeeFamilyPower.UNKNOWN_BEEFAMILY_POWER;
        }

        return switch (beeFamilyPower.toUpperCase().replaceAll("[\\s-]", "_")) {
            case "VERY_STRONG" -> BeeFamilyPower.VERY_STRONG;
            case "STRONG" -> BeeFamilyPower.STRONG;
            case "MODERATELY_STRONG" -> BeeFamilyPower.MODERATELY_STRONG;
            case "AVERAGE" -> BeeFamilyPower.AVERAGE;
            case "MEDIUM" -> BeeFamilyPower.MEDIUM;
            case "WEAK" -> BeeFamilyPower.WEAK;
            case "VERY_WEAK" -> BeeFamilyPower.VERY_WEAK;
            case "DEVELOPING" -> BeeFamilyPower.DEVELOPING;
            case "DECLINING" -> BeeFamilyPower.DECLINING;
            case "OVERWINTERED" -> BeeFamilyPower.OVERWINTERED;
            case "NEWLY_ESTABLISHED" -> BeeFamilyPower.NEWLY_ESTABLISHED;
            case "FULL_SIZE" -> BeeFamilyPower.FULL_SIZE;
            case "PARTIAL" -> BeeFamilyPower.PARTIAL;
            case "POPULOUS" -> BeeFamilyPower.POPULOUS;
            case "LOW_POPULATION" -> BeeFamilyPower.LOW_POPULATION;
            case "BROODLESS" -> BeeFamilyPower.BROODLESS;
            default -> BeeFamilyPower.UNKNOWN_BEEFAMILY_POWER;
        };
    }
}
