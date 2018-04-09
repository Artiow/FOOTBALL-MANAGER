package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.model.tourney.TeamCompositionEntity;
import ru.vldf.sportsportal.model.tourney.TeamEntity;
import ru.vldf.sportsportal.model.tourney.TourneyEntity;

import java.util.List;

public interface TeamCompositionDAO {

    Integer save(TeamCompositionEntity composition);

//    ==================================================================================
//    === FIND

    TeamCompositionEntity findByID(Integer id);

    List<TeamCompositionEntity> findByTourney(Integer tourneyID);

    List<TeamCompositionEntity> findByTourney(TourneyEntity tourney);

    List<TeamCompositionEntity> findAll();
}
