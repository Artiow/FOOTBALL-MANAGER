package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.CompositionDAO;
import ru.vldf.sportsportal.domain.lease.PlaygroundEntity;
import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.TeamEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

import java.util.List;

@Repository
public class CompositionDAOImpl extends AbstractDAOImpl<CompositionEntity, Integer> implements CompositionDAO {
    public CompositionDAOImpl() {
        super(CompositionEntity.class);
    }

    @Override
    public Integer save(CompositionEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public CompositionEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<CompositionEntity> findByTeam(Integer teamID) {
        List compositions = getSession()
                .createQuery("from CompositionEntity where team.id=?")
                .setParameter(0, teamID)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<CompositionEntity>) compositions;
        else return null;
    }

    public List<CompositionEntity> findByTeam(TeamEntity team) {
        List compositions = getSession()
                .createQuery("from CompositionEntity where team=?")
                .setParameter(0, team)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<CompositionEntity>) compositions;
        else return null;
    }

    public List<CompositionEntity> findByTourney(Integer tourneyID) {
        List compositions = getSession()
                .createQuery("from CompositionEntity where tourney.id=?")
                .setParameter(0, tourneyID)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<CompositionEntity>) compositions;
        else return null;
    }

    public List<CompositionEntity> findByTourney(TourneyEntity tourney) {
        List compositions = getSession()
                .createQuery("from CompositionEntity where tourney=?")
                .setParameter(0, tourney)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<CompositionEntity>) compositions;
        else return null;
    }

    public List<CompositionEntity> findAll() {
        return super.list();
    }

//    ==================================================================================
//    === UPDATE

    public void updateTimegridByID(Integer id, String timegrid) {
        getSession()
                .createQuery("update CompositionEntity set timegrid=:timegrid where id=:id")
                .setParameter("id", id)
                .setParameter("timegrid", timegrid)
                .executeUpdate();
    }

    public void incShiftbalanceByID(Integer id) {
        getSession()
                .createQuery("update CompositionEntity set shiftbalance = (shiftbalance + 1) where id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    public void decShiftbalanceByID(Integer id) {
        getSession()
                .createQuery("update CompositionEntity set shiftbalance = (shiftbalance - 1) where id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    public void updateShiftbalanceByID(Integer id, String shiftbalance) {
        getSession()
                .createQuery("update CompositionEntity set shiftbalance=:shiftbalance where id=:id")
                .setParameter("id", id)
                .setParameter("shiftbalance", shiftbalance)
                .executeUpdate();
    }

    public void updatePlaygroundByID(Integer id, PlaygroundEntity playground) {
        getSession()
                .createQuery("update CompositionEntity set playground=:playground where id=:id")
                .setParameter("id", id)
                .setParameter("playground", playground)
                .executeUpdate();
    }
}
