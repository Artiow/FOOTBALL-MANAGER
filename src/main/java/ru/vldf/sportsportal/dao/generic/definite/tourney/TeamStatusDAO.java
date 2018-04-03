package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.model.tourney.TeamStatusEntity;

public interface TeamStatusDAO {

    Integer save(TeamStatusEntity status);

//    ==================================================================================
//    === FIND

    TeamStatusEntity findByID(Integer id);

    TeamStatusEntity findByCode(String code);
}
