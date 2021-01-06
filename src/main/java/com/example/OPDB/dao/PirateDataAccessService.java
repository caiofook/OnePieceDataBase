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
        return 0;
    }

    @Override
    public List<Pirate> selectAllPeople() {
        final String sql = "SELECT id, name, crew, power FROM pirate";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String crew = resultSet.getString("crew");
            String power = resultSet.getString("power");
            return new Pirate(id, name, crew, power);
        });
    }

    @Override
    public Optional<Pirate> selectPirateById(UUID id) {
        final String sql = "SELECT id, name FROM pirate WHERE id = ?";

        Pirate pirate = jdbcTemplate.queryForObject(sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID pirateId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    return new Pirate(pirateId, name);
        });
        return Optional.ofNullable(pirate);
    }

    @Override
    public int deletePirateById(UUID id) {
        return 0;
    }

    @Override
    public int updatePirateById(UUID id, Pirate pirate) {
        return 0;
    }
}
