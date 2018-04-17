package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

import java.util.List;

public interface TourneyDAO {

    Integer save(TourneyEntity tourney);

//    ==================================================================================
//    === FIND

    TourneyEntity findByID(Integer id);

    Long numAll();

    List<TourneyEntity> findAll();
}
