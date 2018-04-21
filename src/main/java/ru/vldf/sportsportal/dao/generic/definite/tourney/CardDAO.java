package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.CardEntity;

import java.util.List;

public interface CardDAO {

    Integer save(CardEntity card);

//    ==================================================================================
//    === FIND

    CardEntity findByID(Integer id);

    CardEntity findByCode(String code);

    List<CardEntity> findAll();
}
