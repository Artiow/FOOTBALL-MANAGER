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

    public TourEntity findByTourney(Integer tourneyID, Integer num) {
        List tours = getSession()
                .createQuery("from TourEntity where tourney.id=? and num=?")
                .setParameter(0, tourneyID)
                .setParameter(1, num)
                .list();

        if ((tours != null) && (tours.size() > 0)) return (TourEntity) tours.get(0);
        else return null;
    }

    public TourEntity findCurrentByTourney(TourneyEntity tourney) {
        List tours = getSession()
                .createQuery("from TourEntity where tourney=? and num=?")
                .setParameter(0, tourney)
                .setParameter(1, tourney.getCurrentTour())
                .list();

        if ((tours != null) && (tours.size() > 0)) return (TourEntity) tours.get(0);
        else return null;
    }

    public TourEntity findNextByTourney(TourneyEntity tourney) {
        List tours = getSession()
                .createQuery("from TourEntity where tourney=? and num=?")
                .setParameter(0, tourney)
                .setParameter(1, tourney.getNextTour())
                .list();

        if ((tours != null) && (tours.size() > 0)) return (TourEntity) tours.get(0);
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
