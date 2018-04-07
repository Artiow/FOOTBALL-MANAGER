package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.model.tourney.TeamEntity;
import ru.vldf.sportsportal.model.tourney.TeamStatusEntity;
import ru.vldf.sportsportal.model.tourney.TourneyEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.List;

public interface TeamDAO {

    Integer save(TeamEntity team);

//    ==================================================================================
//    === FIND

    TeamEntity findByID(Integer id);

    List<TeamEntity> findByUser(Integer userID);

    List<TeamEntity> findByUser(UserEntity user);

    List<TeamEntity> findByTourney(Integer tourneyID);

    List<TeamEntity> findByTourney(TourneyEntity tourney);

    Long numByStatus(Integer statusID);

    Long numByStatus(String statusCode);

    Long numByStatus(TeamStatusEntity status);

    List<TeamEntity> findByStatus(Integer statusID);

    List<TeamEntity> findByStatus(String statusCode);

    List<TeamEntity> findByStatus(TeamStatusEntity status);

//    ==================================================================================
//    === UPDATE

    Integer updateStatusByID(Integer id, TeamStatusEntity status);
}
