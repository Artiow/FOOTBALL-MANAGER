package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.GameDAO;
import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.GameEntity;
import ru.vldf.sportsportal.domain.tourney.TourEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

import java.util.List;

@Repository
public class GameDAOImpl extends AbstractDAOImpl<GameEntity, Integer> implements GameDAO {
    public GameDAOImpl() {
        super(GameEntity.class);
    }

    @Override
    public Integer save(GameEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public GameEntity findByID(Integer id) {
        return super.get(id);
    }

    public GameEntity findByRivalID(Integer compositionID, Integer tourNum) {
        List games = getSession()
                .createQuery("from GameEntity where tour.num=:tourNum and (red.id=:compositionID or blue.id=:compositionID)")
                .setParameter("compositionID", compositionID)
                .setParameter("tourNum", tourNum)
                .list();

        if ((games != null) && (games.size() == 1)) return (GameEntity) games.get(0);
        else return null;
    }

    public GameEntity findByRivalsID(Integer r1ID, Integer r2ID) {
        List games = getSession()
                .createQuery("from GameEntity where (red.id=:r1ID and blue.id=:r2ID) or (red.id=:r2ID and blue.id=:r1ID)")
                .setParameter("r1ID", r1ID)
                .setParameter("r2ID", r2ID)
                .list();

        if ((games != null) && (games.size() == 1)) return (GameEntity) games.get(0);
        else return null;
    }

    public List<GameEntity> findAll() {
        return super.list();
    }

    public List<GameEntity> findByTour(Integer id) {
        List games = getSession()
                .createQuery("from GameEntity where tour.id=?")
                .setParameter(0, id)
                .list();

        if ((games != null) && (games.size() > 0)) return (List<GameEntity>) games;
        else return null;
    }

    public List<GameEntity> findByTour(TourEntity tour) {
        List games = getSession()
                .createQuery("from GameEntity where tour=?")
                .setParameter(0, tour)
                .list();

        if ((games != null) && (games.size() > 0)) return (List<GameEntity>) games;
        else return null;
    }

    public List<GameEntity> findCurrentByTourney(Integer id) {
        List games = getSession()
                .createQuery("select g from GameEntity as g, TourneyEntity as t"
                        + " where (g.tour.tourney.id=?) and (t.id = g.tour.tourney.id) and (g.tour.num = t.currentTour)"
                ).setParameter(0, id)
                .list();

        if ((games != null) && (games.size() > 0)) return (List<GameEntity>) games;
        else return null;
    }

    public List<GameEntity> findCurrentByTourney(TourneyEntity tourney) {
        List games = getSession()
                .createQuery("from GameEntity where tour.tourney=:tourney and tour.num=:num")
                .setParameter("num", tourney.getCurrentTour())
                .setParameter("tourney", tourney)
                .list();

        if ((games != null) && (games.size() > 0)) return (List<GameEntity>) games;
        else return null;
    }

    public List<GameEntity> findNextByTourney(Integer id) {
        List games = getSession()
                .createQuery("select g from GameEntity as g, TourneyEntity as t"
                        + " where (g.tour.tourney.id=?) and (t.id = g.tour.tourney.id) and (g.tour.num = t.nextTour)"
                ).setParameter(0, id)
                .list();

        if ((games != null) && (games.size() > 0)) return (List<GameEntity>) games;
        else return null;
    }

    public List<GameEntity> findNextByTourney(TourneyEntity tourney) {
        List games = getSession()
                .createQuery("from GameEntity where tour.tourney=:tourney and tour.num=:num")
                .setParameter("num", tourney.getNextTour())
                .setParameter("tourney", tourney)
                .list();

        if ((games != null) && (games.size() > 0)) return (List<GameEntity>) games;
        else return null;
    }

//    ==================================================================================
//    === UPDATE

    public void updateTimegridByID(Integer id, String timegrid) {
        getSession()
                .createQuery("update GameEntity set timegrid=:timegrid where id=:id")
                .setParameter("timegrid", timegrid)
                .setParameter("id", id)
                .executeUpdate();
    }
}
