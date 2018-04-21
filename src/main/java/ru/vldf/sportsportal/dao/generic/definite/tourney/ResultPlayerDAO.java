package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.ResultPlayerEntity;

import java.util.List;

public interface ResultPlayerDAO {

    Integer save(ResultPlayerEntity result);

//    ==================================================================================
//    === FIND

    ResultPlayerEntity findByID(Integer id);

    List<ResultPlayerEntity> findAll();
}
