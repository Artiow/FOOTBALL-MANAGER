package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.GameEntity;

import java.util.List;

public interface GameDAO {

    Integer save(GameEntity team);

//    ==================================================================================
//    === GET

//    ==================================================================================
//    === FIND

    GameEntity findByID(Integer id);

    GameEntity findByRivalID(Integer compositionID);

    GameEntity findByRivalsID(Integer r1ID, Integer r2ID);

    List<GameEntity> findAll();

//    ==================================================================================
//    === UPDATE

    Integer updateTimegrid(CompositionEntity red, CompositionEntity blue, String timegrid);
}
