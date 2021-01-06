package com.example.OPDB.dao;

import com.example.OPDB.model.Pirate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePirateDataAccessService implements PirateDao {

    private static List<Pirate> DB = new ArrayList<>();

    @Override
    public int insertPirate(UUID id, Pirate pirate) {
        DB.add(new Pirate(id, pirate.getName()));
        return 1;
    }

    @Override
    public List<Pirate> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Pirate> selectPirateById(UUID id) {
        return DB.stream()
                .filter(pirate -> pirate.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePirateById(UUID id) {
        Optional<Pirate> pirateMaybe = selectPirateById(id);
        if (pirateMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(pirateMaybe.get());
        return 1;
    }

    @Override
    public int updatePirateById(UUID id, Pirate update) {
        return selectPirateById(id)
                .map(pirate -> {
                    int indexOfPirateToUpdate = DB.indexOf(pirate);
                    if (indexOfPirateToUpdate >= 0) {
                        DB.set(indexOfPirateToUpdate, new Pirate(id, update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
