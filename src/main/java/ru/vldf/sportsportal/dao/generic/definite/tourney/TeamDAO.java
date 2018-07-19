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

    List<TeamEntity> findByStatusAndNameLike(Integer statusID, String name);

    List<TeamEntity> findByStatusAndNameLike(String statusCode, String name);

    List<TeamEntity> findByStatusAndNameLike(TeamStatusEntity status, String name);

    List<TeamEntity> findAll();

//    ==================================================================================
//    === UPDATE

    void updateNameByID(Integer id, String name);

    void updateStatusByID(Integer id, Integer statusID);

    void updateStatusByID(Integer id, String statusCode);

    void updateStatusByID(Integer id, TeamStatusEntity status);
}
