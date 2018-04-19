package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.PlayerStatusEntity;

import java.util.List;

public interface PlayerStatusDAO {
    Integer save(PlayerStatusEntity sport);

//    ==================================================================================
//    === FIND

    PlayerStatusEntity findByID(Integer id);

    List<PlayerStatusEntity> findAll();
}
