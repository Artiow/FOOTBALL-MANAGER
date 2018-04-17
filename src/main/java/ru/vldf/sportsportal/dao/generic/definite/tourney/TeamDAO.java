package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.TeamEntity;
import ru.vldf.sportsportal.domain.tourney.TeamStatusEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;
import ru.vldf.sportsportal.domain.common.UserEntity;

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

    List<TeamEntity> findByNameLike(String name);

//    ==================================================================================
//    === UPDATE

    Integer updateStatusByID(Integer id, TeamStatusEntity status);
}
