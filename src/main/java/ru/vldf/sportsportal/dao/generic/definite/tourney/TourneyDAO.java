package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.TourneyEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyStatusEntity;

import java.util.List;

public interface TourneyDAO {

    Integer save(TourneyEntity tourney);

//    ==================================================================================
//    === FIND

    TourneyEntity findByID(Integer id);

    Long numAll();

    List<TourneyEntity> findAll();

    List<TourneyEntity> findAllOrderByID();

//    ==================================================================================
//    === UPDATE

    void updateStatusByID(Integer id, TourneyStatusEntity status);
}
