package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.PlayerStatisticEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

import java.util.List;

public interface PlayerStatisticDAO {

    Integer save(PlayerStatisticEntity result);

//    ==================================================================================
//    === FIND

    PlayerStatisticEntity findByID(Integer id);

    List<PlayerStatisticEntity> findByTourney(Integer id);

    List<PlayerStatisticEntity> findByTourney(TourneyEntity tourney);

    List<PlayerStatisticEntity> findByComposition(Integer id);

    List<PlayerStatisticEntity> findByComposition(CompositionEntity composition);

    List<PlayerStatisticEntity> findAll();
}
