package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.TimegridTypeEntity;

import java.util.List;

public interface TimegridTypeDAO {
    Integer save(TimegridTypeEntity type);

//    ==================================================================================
//    === FIND

    TimegridTypeEntity findByID(Integer id);

    List<TimegridTypeEntity> findAll();
}
