package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.TourEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

import java.util.List;

public interface TourDAO {

    Integer save(TourEntity tour);

//    ==================================================================================
//    === FIND

    TourEntity findByID(Integer id);

    TourEntity findCurrentByTourney(Integer tourneyID);

    TourEntity findCurrentByTourney(TourneyEntity tourney);

    TourEntity findNextByTourney(Integer tourneyID);

    TourEntity findNextByTourney(TourneyEntity tourney);

    TourEntity findByTourney(Integer tourneyID, Integer num);

    List<TourEntity> findByTourney(Integer tourneyID);

    List<TourEntity> findByTourney(TourneyEntity tourney);

    List<TourEntity> findAll();
}
