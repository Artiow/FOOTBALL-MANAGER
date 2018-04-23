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
                .createQuery("from GameEntity where tour.num=? and (red.id=? or blue.id=?)")
                .setParameter(0, tourNum)
                .setParameter(1, compositionID)
                .setParameter(2, compositionID)
                .list();

        if ((games != null) && (games.size() > 0)) return (GameEntity) games.get(0);
        else return null;
    }

    public GameEntity findByRivalsID(Integer r1ID, Integer r2ID) {
        List games = getSession()
                .createQuery("from GameEntity where (red.id=? and blue.id=?) or (red.id=? and blue.id=?)")
                .setParameter(0, r1ID)
                .setParameter(1, r2ID)
                .setParameter(0, r2ID)
                .setParameter(1, r1ID)
                .list();

        if ((games != null) && (games.size() > 0)) return (GameEntity) games.get(0);
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
                        + " where g.tour.tourney.id=? and t.id=? and g.tour.num=t.currentTour"
                ).setParameter(0, id)
                .setParameter(1, id)
                .list();

        if ((games != null) && (games.size() > 0)) return (List<GameEntity>) games;
        else return null;
    }

    public List<GameEntity> findCurrentByTourney(TourneyEntity tourney) {
        List games = getSession()
                .createQuery("from GameEntity where tour.tourney=? and tour.num=?")
                .setParameter(0, tourney)
                .setParameter(1, tourney.getCurrentTour())
                .list();

        if ((games != null) && (games.size() > 0)) return (List<GameEntity>) games;
        else return null;
    }

    public List<GameEntity> findNextByTourney(Integer id) {
        List games = getSession()
                .createQuery("select g from GameEntity as g, TourneyEntity as t"
                        + " where g.tour.tourney.id=? and t.id=? and g.tour.num=t.nextTour"
                ).setParameter(0, id)
                .setParameter(1, id)
                .list();

        if ((games != null) && (games.size() > 0)) return (List<GameEntity>) games;
        else return null;
    }

    public List<GameEntity> findNextByTourney(TourneyEntity tourney) {
        List games = getSession()
                .createQuery("from GameEntity where tour.tourney=? and tour.num=?")
                .setParameter(0, tourney)
                .setParameter(1, tourney.getNextTour())
                .list();

        if ((games != null) && (games.size() > 0)) return (List<GameEntity>) games;
        else return null;
    }

//    ==================================================================================
//    === UPDATE

    public Integer updateTimegridByID(Integer id, String timegrid) {
        return getSession()
                .createQuery("update GameEntity set timegrid=? where id=?")
                .setParameter(0, timegrid)
                .setParameter(1, id)
                .executeUpdate();
    }
}
