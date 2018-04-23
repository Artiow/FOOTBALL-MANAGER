package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.lease.PlaygroundEntity;
import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.CompositionStatusEntity;
import ru.vldf.sportsportal.domain.tourney.TeamEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

import java.util.List;

public interface CompositionDAO {

    Integer save(CompositionEntity composition);

//    ==================================================================================
//    === FIND

    CompositionEntity findByID(Integer id);

    List<CompositionEntity> findByTeam(Integer teamID);

    List<CompositionEntity> findByTeam(TeamEntity team);

    List<CompositionEntity> findByTourney(Integer tourneyID);

    List<CompositionEntity> findByTourney(TourneyEntity tourney);

    List<CompositionEntity> findAll();

//    ==================================================================================
//    === UPDATE

    Integer updateStatusByID(Integer id, CompositionStatusEntity status);

    Integer updatePlaygroundByID(Integer id, PlaygroundEntity playground);

    Integer updateTimeGridByID(Integer id, String timegrid);

//    ==================================================================================
//    === SPECIAL

    List<CompositionEntity> findByTeamAndStatus(Integer teamID, Integer statusID);

    List<CompositionEntity> findByTeamAndStatus(Integer teamID, String statusCode);

    List<CompositionEntity> findByTeamAndStatus(TeamEntity team, CompositionStatusEntity status);
}
