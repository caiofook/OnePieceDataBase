package com.example.OPDB.dao;
//a interface serve como um "contrato", definimos as operações que alguem pode fazer ao implementar esta interface.
//aí, com injeção de dependências, vai dar pra trocar de implementações facilmente

import com.example.OPDB.model.Pirate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PirateDao {
    int insertPirate(UUID id, Pirate pirate);
    default int insertPirate(Pirate pirate) {
        UUID id = UUID.randomUUID();
        return insertPirate(id, pirate);
    }
    List<Pirate> selectAllPeople();
    Optional<Pirate> selectPirateById(UUID id);

    int deletePirateById(UUID id);

    int updatePirateById(UUID id, Pirate pirateToUpdate);
}
