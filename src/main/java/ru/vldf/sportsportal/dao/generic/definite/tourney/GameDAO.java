package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.GameEntity;
import ru.vldf.sportsportal.domain.tourney.TourEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

import java.util.List;

public interface GameDAO {

    Integer save(GameEntity team);

//    ==================================================================================
//    === FIND

    GameEntity findByID(Integer id);

    GameEntity findByRivalID(Integer compositionID, Integer tourNum);

    GameEntity findByRivalsID(Integer r1ID, Integer r2ID);

    List<GameEntity> findAll();

    List<GameEntity> findByTour(Integer id);

    List<GameEntity> findByTour(TourEntity tour);

    List<GameEntity> findCurrentByTourney(Integer id);

    List<GameEntity> findCurrentByTourney(TourneyEntity tourney);

    List<GameEntity> findNextByTourney(Integer id);

    List<GameEntity> findNextByTourney(TourneyEntity tourney);

//    ==================================================================================
//    === UPDATE

    void updateTimegridByID(Integer id, String timegrid);
}
