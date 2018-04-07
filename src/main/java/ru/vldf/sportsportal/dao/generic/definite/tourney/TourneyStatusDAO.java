package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.model.tourney.TourneyStatusEntity;

import java.util.List;

public interface TourneyStatusDAO {

    Integer save(TourneyStatusEntity status);

//    ==================================================================================
//    === FIND

    TourneyStatusEntity findByID(Integer id);

    TourneyStatusEntity findByCode(String code);

    List<TourneyStatusEntity> findAll();
}
