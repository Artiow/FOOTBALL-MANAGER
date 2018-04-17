package ru.vldf.sportsportal.dao.generic.definite.common;

import ru.vldf.sportsportal.domain.common.SportEntity;

import java.util.List;

public interface SportDAO {

    Integer save(SportEntity sport);

//    ==================================================================================
//    === FIND

    SportEntity findByID(Integer id);

    List<SportEntity> findAll();
}
