package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.TimegridEntity;
import ru.vldf.sportsportal.domain.tourney.TimegridTypeEntity;

import java.util.List;

public interface TimegridDAO {
    Integer save(TimegridEntity type);

//    ==================================================================================
//    === FIND

    TimegridEntity findByID(Integer id);

    List<TimegridEntity> findByTimegridType(Integer typeID);

    List<TimegridEntity> findByTimegridType(TimegridTypeEntity type);

    List<TimegridEntity> findAll();
}
