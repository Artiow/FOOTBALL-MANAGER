package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.lease.PlaygroundEntity;
import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
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

    void updateTimegridByID(Integer id, String timegrid);

    void incShiftbalanceByID(Integer id);

    void decShiftbalanceByID(Integer id);

    Integer getShiftbalanceByID(Integer id);

    void updateShiftbalanceByID(Integer id, String shiftbalance);

    void updatePlaygroundByID(Integer id, PlaygroundEntity playground);
}
