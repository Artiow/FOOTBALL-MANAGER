package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TmpDAO;
import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.TmpEntity;

import java.util.List;

@Repository
public class TmpDAOImpl extends AbstractDAOImpl<TmpEntity, Integer> implements TmpDAO {
    public TmpDAOImpl() {
        super(TmpEntity.class);
    }

    @Override
    public Integer save(TmpEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === GET

    public TmpEntity getTimegrid(Integer redID, Integer blueID) {
        List tmps = getSession()
                .createQuery("from TmpEntity where red.id=? or blue.id=?")
                .setParameter(0, redID)
                .setParameter(1, blueID)
                .list();

        if ((tmps != null) && (tmps.size() == 1)) return (TmpEntity) tmps.get(0);
        else return null;
    }

//    ==================================================================================
//    === FIND

    public TmpEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<TmpEntity> findAll() {
        return super.list();
    }

//    ==================================================================================
//    === UPDATE

    public TmpEntity findRivalByID(Integer id) {
        List tmps = getSession()
                .createQuery("from TmpEntity where red.id=? or blue.id=?")
                .setParameter(0, id)
                .setParameter(1, id)
                .list();

        if ((tmps != null) && (tmps.size() == 1)) return (TmpEntity) tmps.get(0);
        else return null;
    }

    public Integer updateTimegrid(CompositionEntity red, CompositionEntity blue, String timegrid) {
        return getSession()
                .createQuery("update TmpEntity set timegrid=? where red=? and blue=?")
                .setParameter(0, timegrid)
                .setParameter(1, red)
                .setParameter(2, blue)
                .executeUpdate();
    }
}
