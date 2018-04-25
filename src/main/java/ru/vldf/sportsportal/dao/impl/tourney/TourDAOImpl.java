package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TourDAO;
import ru.vldf.sportsportal.domain.tourney.TourEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

import java.util.List;

@Repository
public class TourDAOImpl extends AbstractDAOImpl<TourEntity, Integer> implements TourDAO {
    public TourDAOImpl() {
        super(TourEntity.class);
    }

    @Override
    public Integer save(TourEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TourEntity findByID(Integer id) {
        return super.get(id);
    }

    public TourEntity findCurrentByTourney(Integer tourneyID) {
        List tours = getSession()
                .createQuery("select t from TourEntity as t, TourneyEntity as tt"
                        + " where tt.id=? and t.tourney=tt and t.num=tt.currentTour"
                ).setParameter(0, tourneyID)
                .list();

        if ((tours != null) && (tours.size() == 1)) return (TourEntity) tours.get(0);
        else return null;
    }

    public TourEntity findCurrentByTourney(TourneyEntity tourney) {
        List tours = getSession()
                .createQuery("from TourEntity where tourney=:tourney and num=:num")
                .setParameter("tourney", tourney)
                .setParameter("num", tourney.getCurrentTour())
                .list();

        if ((tours != null) && (tours.size() == 1)) return (TourEntity) tours.get(0);
        else return null;
    }

    public TourEntity findNextByTourney(Integer tourneyID) {
        List tours = getSession()
                .createQuery("select t from TourEntity as t, TourneyEntity as tt"
                        + " where tt.id=? and t.tourney=tt and t.num=tt.nextTour"
                ).setParameter(0, tourneyID)
                .list();

        if ((tours != null) && (tours.size() == 1)) return (TourEntity) tours.get(0);
        else return null;
    }

    public TourEntity findNextByTourney(TourneyEntity tourney) {
        List tours = getSession()
                .createQuery("from TourEntity where tourney=:tourney and num=:num")
                .setParameter("tourney", tourney)
                .setParameter("num", tourney.getNextTour())
                .list();

        if ((tours != null) && (tours.size() == 1)) return (TourEntity) tours.get(0);
        else return null;
    }

    public TourEntity findByTourney(Integer tourneyID, Integer num) {
        List tours = getSession()
                .createQuery("from TourEntity where tourney.id=:tourneyID and num=:num")
                .setParameter("tourneyID", tourneyID)
                .setParameter("num", num)
                .list();

        if ((tours != null) && (tours.size() == 1)) return (TourEntity) tours.get(0);
        else return null;
    }

    public List<TourEntity> findByTourney(Integer tourneyID) {
        List tours = getSession()
                .createQuery("from TourEntity where tourney.id=? order by num")
                .setParameter(0, tourneyID)
                .list();

        if ((tours != null) && (tours.size() > 0)) return (List<TourEntity>) tours;
        else return null;
    }

    public List<TourEntity> findByTourney(TourneyEntity tourney) {
        List tours = getSession()
                .createQuery("from TourEntity where tourney=? order by num")
                .setParameter(0, tourney)
                .list();

        if ((tours != null) && (tours.size() > 0)) return (List<TourEntity>) tours;
        else return null;
    }

    public List<TourEntity> findAll() {
        return super.list();
    }
}
