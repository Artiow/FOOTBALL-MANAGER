package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.CompositionDAO;
import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.CompositionStatusEntity;
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
                .createQuery("from CompositionEntity as eTeamComposition where eTeamComposition.team.id=?")
                .setParameter(0, teamID)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<CompositionEntity>) compositions;
        else return null;
    }

    public List<CompositionEntity> findByTeam(TeamEntity team) {
        List compositions = getSession()
                .createQuery("from CompositionEntity as eTeamComposition where eTeamComposition.team=?")
                .setParameter(0, team)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<CompositionEntity>) compositions;
        else return null;
    }

    public List<CompositionEntity> findByTourney(Integer tourneyID) {
        List compositions = getSession()
                .createQuery("from CompositionEntity as eTeamComposition where eTeamComposition.tourney.id=?")
                .setParameter(0, tourneyID)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<CompositionEntity>) compositions;
        else return null;
    }

    public List<CompositionEntity> findByTourney(TourneyEntity tourney) {
        List compositions = getSession()
                .createQuery("from CompositionEntity as eTeamComposition where eTeamComposition.tourney=?")
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

    public Integer updateStatusByID(Integer id, CompositionStatusEntity status) {
        return getSession()
                .createQuery("update CompositionEntity set status=? where id=?")
                .setParameter(0, status)
                .setParameter(1, id)
                .executeUpdate();
    }

    public Integer updateTimeGridByID(Integer id, String timegrid) {
        return getSession()
                .createQuery("update CompositionEntity set timegrid=? where id=?")
                .setParameter(0, timegrid)
                .setParameter(1, id)
                .executeUpdate();
    }

//    ==================================================================================
//    === SPECIAL

    public List<CompositionEntity> findByTeamAndStatus(Integer teamID, Integer statusID) {
        List compositions = getSession()
                .createQuery("from CompositionEntity as eTeamComposition"
                        + " where eTeamComposition.team.id=? and eTeamComposition.status.id=?"
                ).setParameter(0, teamID)
                .setParameter(1, statusID)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<CompositionEntity>) compositions;
        else return null;
    }

    public List<CompositionEntity> findByTeamAndStatus(Integer teamID, String statusCode) {
        List compositions = getSession()
                .createQuery("from CompositionEntity as eTeamComposition"
                        + " where eTeamComposition.team.id=? and eTeamComposition.status.code=?"
                ).setParameter(0, teamID)
                .setParameter(1, statusCode)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<CompositionEntity>) compositions;
        else return null;
    }

    public List<CompositionEntity> findByTeamAndStatus(TeamEntity team, CompositionStatusEntity status) {
        List compositions = getSession()
                .createQuery("from CompositionEntity as eTeamComposition"
                                + " where eTeamComposition.team=? and eTeamComposition.status=?"
                ).setParameter(0, team)
                .setParameter(1, status)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<CompositionEntity>) compositions;
        else return null;
    }
}
