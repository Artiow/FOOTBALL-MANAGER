package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.PlayerResultEntity;

import java.util.List;

public interface PlayerResultDAO {

    Integer save(PlayerResultEntity result);

//    ==================================================================================
//    === FIND

    PlayerResultEntity findByID(Integer id);

    PlayerResultEntity findByGameAndStatistic(Integer gameID, Integer statisticID);

    PlayerResultEntity findByGameAndMembership(Integer gameID, Integer membershipID);

    List<PlayerResultEntity> findAll();
}
