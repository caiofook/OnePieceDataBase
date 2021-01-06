package com.example.OPDB.service;

import com.example.OPDB.dao.PirateDao;
import com.example.OPDB.model.Pirate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PirateService {

    private final PirateDao pirateDao;

    @Autowired
    public PirateService(@Qualifier("postgres") PirateDao pirateDao) {
        this.pirateDao = pirateDao;
    }

    public int addPirate(Pirate pirate) {
        return pirateDao.insertPirate(pirate);
    }

    public List<Pirate> getAllPeople() {
        return pirateDao.selectAllPeople();
    }

    public Optional<Pirate> getPirateById(UUID id) {
        return pirateDao.selectPirateById(id);
    }
    public int deletePirate(UUID id) {
        return pirateDao.deletePirateById(id);
    }
    public int updatePirate(UUID id, Pirate newPirate) {
        return pirateDao.updatePirateById(id, newPirate);
    }
}
