package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.PlayerOwnershipEntity;

import java.util.List;

public interface PlayerOwnershipDAO {
    Integer save(PlayerOwnershipEntity sport);

//    ==================================================================================
//    === FIND

    PlayerOwnershipEntity findByID(Integer id);

    List<PlayerOwnershipEntity> findAll();
}
