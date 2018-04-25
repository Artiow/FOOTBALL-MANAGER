package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.TourStatusEntity;

import java.util.List;

public interface TourStatusDAO {
    Integer save(TourStatusEntity status);

//    ==================================================================================
//    === FIND

    TourStatusEntity findByID(Integer id);

    TourStatusEntity findByCode(String code);

    List<TourStatusEntity> findAll();
}
