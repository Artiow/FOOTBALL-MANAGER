package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.GameEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

import java.util.List;

public interface GameDAO {

    Integer save(GameEntity team);

//    ==================================================================================
//    === FIND

    GameEntity findByID(Integer id);

    GameEntity findByRivalID(Integer compositionID);

    GameEntity findByRivalsID(Integer r1ID, Integer r2ID);

    List<GameEntity> findAll();

    List<GameEntity> findByTourney(Integer id);

    List<GameEntity> findByTourney(TourneyEntity tourney);

//    ==================================================================================
//    === UPDATE

    Integer updateTimegridByID(Integer id, String timegrid);
}
