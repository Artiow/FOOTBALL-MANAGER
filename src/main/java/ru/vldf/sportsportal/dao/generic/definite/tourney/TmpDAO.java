package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.TmpEntity;

public interface TmpDAO {

    TmpEntity findRivalByID(Integer id);
}
