package com.example.OPDB.dao;

import com.example.OPDB.model.Pirate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PirateDataAccessService implements PirateDao{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PirateDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPirate(UUID id, Pirate pirate) {

       final String sql = "INSERT INTO pirate (id, name, epithet, crew, power) VALUES (?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql,
                id,
                pirate.getName(),
                pirate.getEpithet(),
                pirate.getCrew(),
                pirate.getPower());
    }


    @Override
    public List<Pirate> selectAllPeople() {
        final String sql = "SELECT id, name, epithet, crew, power FROM pirate";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String epithet = resultSet.getString("epithet");
            String crew = resultSet.getString("crew");
            String power = resultSet.getString("power");
            return new Pirate(id, name, epithet, crew, power);
        });
    } //OK

    @Override
    public Optional<Pirate> selectPirateById(UUID pirateId) {
        final String sql = "SELECT id, name, epithet, crew, power FROM pirate WHERE id = ?";

        Pirate pirate = jdbcTemplate.queryForObject(sql, new Object[]{pirateId}, (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String epithet = resultSet.getString("epithet");
                    String crew = resultSet.getString("crew");
                    String power = resultSet.getString("power");
                    return new Pirate(id, name, epithet, crew, power);
        });
        return Optional.ofNullable(pirate);
    } //OK

    @Override
    public int deletePirateById(UUID id) {
        Optional<Pirate> pirateMaybe = selectPirateById(id);
        if (pirateMaybe.isEmpty()) {
            return 0;
        }
       UUID idToDelete = pirateMaybe.get().getId();
        final String sql = "DELETE FROM pirate WHERE id = ?";
        return jdbcTemplate.update(sql, idToDelete);
    }

    @Override
    public int updatePirateById(UUID id, Pirate pirateToUpdate) {
        if (pirateToUpdate.getName() != null) {
            String sql = "UPDATE pirate SET name = ? WHERE id = ?;";
            jdbcTemplate.update(sql, pirateToUpdate.getName(), id);
        }
        if (pirateToUpdate.getEpithet() != null) {
            String sql = "UPDATE pirate SET epithet = ? WHERE id = ?;";
            jdbcTemplate.update(sql, pirateToUpdate.getEpithet(), id);
        }
        if (pirateToUpdate.getCrew() != null) {
            String sql = "Update pirate SET crew = ? WHERE id = ?;";
            jdbcTemplate.update(sql, pirateToUpdate.getCrew(), id);
        }
        if (pirateToUpdate.getPower() != null) {
            String sql = "UPDATE pirate SET power = ? WHERE id = ?;";
            jdbcTemplate.update(sql, pirateToUpdate.getPower(), id);
        }
        return 1;
    }
}
