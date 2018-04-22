package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.ResultTeamEntity;

import java.util.List;

public interface ResultTeamDAO {

    Integer save(ResultTeamEntity result);

//    ==================================================================================
//    === FIND

    ResultTeamEntity findByID(Integer id);

    ResultTeamEntity findByGameAndComposition(Integer gameID, Integer compositionID);

    List<ResultTeamEntity> findAll();
}