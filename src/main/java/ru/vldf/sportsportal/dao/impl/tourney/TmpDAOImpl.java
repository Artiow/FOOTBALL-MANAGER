package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TmpDAO;
import ru.vldf.sportsportal.domain.tourney.TmpEntity;

import java.util.List;

@Repository
public class TmpDAOImpl extends AbstractDAOImpl<TmpEntity, Integer> implements TmpDAO {
    public TmpDAOImpl() {
        super(TmpEntity.class);
    }

    public TmpEntity findRivalByID(Integer id) {
        List tmps = getSession()
                .createQuery("from TmpEntity where red.id=? or blue.id=?")
                .setParameter(0, id)
                .setParameter(1, id)
                .list();

        if ((tmps != null) && (tmps.size() == 1)) return (TmpEntity) tmps.get(0);
        else return null;
    }
}
