package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TourneyDAO;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyStatusEntity;

import java.util.List;

@Repository
public class TourneyDAOImpl extends AbstractDAOImpl<TourneyEntity, Integer> implements TourneyDAO {
    public TourneyDAOImpl() {
        super(TourneyEntity.class);
    }

    @Override
    public Integer save(TourneyEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TourneyEntity findByID(Integer id) {
        return super.get(id);
    }

    public Long numAll() {
        List count = getSession()
                .createQuery("select count(*) from TourneyEntity")
                .list(); //TODO: list?

        if ((count != null) && (count.size() > 0)) return ((Long) count.get(0));
        else return null;
    }

    public List<TourneyEntity> findAll() {
        return super.list();
    }

    public List<TourneyEntity> findAllOrderByID() {
        List tourneys = getSession().createQuery("from TourneyEntity order by id").list();
        if ((tourneys != null) && (tourneys.size() > 0)) return (List<TourneyEntity>) tourneys;
        else return null;
    }

//    ==================================================================================
//    === UPDATE


    public void updateStatusByID(Integer id, Integer statusID) {
        getSession()
                .createQuery("update TourneyEntity set status = (from TourneyStatusEntity as s where s.id=:statusID) where id=:id")
                .setParameter("statusID", statusID)
                .setParameter("id", id)
                .executeUpdate();
    }

    public void updateStatusByID(Integer id, String statusCode) {
        getSession()
                .createQuery("update TourneyEntity set status = (from TourneyStatusEntity as s where s.code=:statusCode) where id=:id")
                .setParameter("statusCode", statusCode)
                .setParameter("id", id)
                .executeUpdate();
    }

    public void updateStatusByID(Integer id, TourneyStatusEntity status) {
        getSession()
                .createQuery("update TourneyEntity set status=:status where id=:id")
                .setParameter("status", status)
                .setParameter("id", id)
                .executeUpdate();
    }
}