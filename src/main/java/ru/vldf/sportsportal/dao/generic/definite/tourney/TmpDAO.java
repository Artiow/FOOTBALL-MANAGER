package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.TmpEntity;

import java.util.List;

public interface TmpDAO {

    Integer save(TmpEntity team);

//    ==================================================================================
//    === GET

    TmpEntity getTimegrid(Integer redID, Integer blueID);

//    ==================================================================================
//    === FIND

    TmpEntity findByID(Integer id);

    List<TmpEntity> findAll();

//    ==================================================================================
//    === UPDATE

    TmpEntity findRivalByID(Integer id);

    Integer updateTimegrid(CompositionEntity red, CompositionEntity blue, String timegrid);
}
