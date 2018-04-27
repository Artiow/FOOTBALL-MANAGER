package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TimegridDAO;
import ru.vldf.sportsportal.domain.tourney.TimegridEntity;
import ru.vldf.sportsportal.domain.tourney.TimegridTypeEntity;

import java.util.List;

@Repository
public class TimegridDAOImpl extends AbstractDAOImpl<TimegridEntity, Integer> implements TimegridDAO {
    public TimegridDAOImpl() {
        super(TimegridEntity.class);
    }

    @Override
    public Integer save(TimegridEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TimegridEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<TimegridEntity> findByTimegridType(Integer typeID) {
        List timegrid = getSession()
                .createQuery("from TimegridEntity where type.id=?")
                .setParameter(0, typeID)
                .list();

        if ((timegrid != null) && (timegrid.size() > 0)) return (List<TimegridEntity>) timegrid;
        else return null;
    }

    public List<TimegridEntity> findByTimegridType(TimegridTypeEntity type) {
        List timegrid = getSession()
                .createQuery("from TimegridEntity where type=?")
                .setParameter(0, type)
                .list();

        if ((timegrid != null) && (timegrid.size() > 0)) return (List<TimegridEntity>) timegrid;
        else return null;
    }

    public List<TimegridEntity> findAll() {
        return super.list();
    }
}
