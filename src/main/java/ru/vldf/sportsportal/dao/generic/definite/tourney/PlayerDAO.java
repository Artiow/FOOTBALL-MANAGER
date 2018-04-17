package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.PlayerEntity;

import java.util.List;

public interface PlayerDAO {
    Integer save(PlayerEntity player);

//    ==================================================================================
//    === FIND

    PlayerEntity findByID(Integer id);

    List<PlayerEntity> findByFullName(String name, String surname, String patronymic);

    List<PlayerEntity> findByTeamComposition(Integer compositionID);

//    ==================================================================================
//    === DELETE

    void deleteByID(Integer id);
}
