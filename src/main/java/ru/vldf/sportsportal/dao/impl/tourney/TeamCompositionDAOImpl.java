package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamCompositionDAO;
import ru.vldf.sportsportal.model.tourney.TeamCompositionEntity;
import ru.vldf.sportsportal.model.tourney.TeamCompositionStatusEntity;
import ru.vldf.sportsportal.model.tourney.TeamEntity;
import ru.vldf.sportsportal.model.tourney.TourneyEntity;

import java.util.List;

@Repository
public class TeamCompositionDAOImpl extends AbstractDAOImpl<TeamCompositionEntity, Integer> implements TeamCompositionDAO {
    public TeamCompositionDAOImpl() {
        super(TeamCompositionEntity.class);
    }

    @Override
    public Integer save(TeamCompositionEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TeamCompositionEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<TeamCompositionEntity> findByTeam(Integer teamID) {
        List compositions = getSession()
                .createQuery("from TeamCompositionEntity as eTeamComposition where eTeamComposition.team.id=?")
                .setParameter(0, teamID)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<TeamCompositionEntity>) compositions;
        else return null;
    }

    public List<TeamCompositionEntity> findByTeam(TeamEntity team) {
        List compositions = getSession()
                .createQuery("from TeamCompositionEntity as eTeamComposition where eTeamComposition.team=?")
                .setParameter(0, team)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<TeamCompositionEntity>) compositions;
        else return null;
    }

    public List<TeamCompositionEntity> findByTourney(Integer tourneyID) {
        List compositions = getSession()
                .createQuery("from TeamCompositionEntity as eTeamComposition where eTeamComposition.tourney.id=?")
                .setParameter(0, tourneyID)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<TeamCompositionEntity>) compositions;
        else return null;
    }

    public List<TeamCompositionEntity> findByTourney(TourneyEntity tourney) {
        List compositions = getSession()
                .createQuery("from TeamCompositionEntity as eTeamComposition where eTeamComposition.tourney=?")
                .setParameter(0, tourney)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<TeamCompositionEntity>) compositions;
        else return null;
    }

    public List<TeamCompositionEntity> findAll() {
        return super.list();
    }

//    ==================================================================================
//    === SPECIAL

    public List<TeamCompositionEntity> findByTeamAndStatus(Integer teamID, Integer statusID) {
        List compositions = getSession()
                .createQuery("from TeamCompositionEntity as eTeamComposition"
                        + " where eTeamComposition.team.id=? and eTeamComposition.status.id=?"
                ).setParameter(0, teamID)
                .setParameter(1, statusID)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<TeamCompositionEntity>) compositions;
        else return null;
    }

    public List<TeamCompositionEntity> findByTeamAndStatus(Integer teamID, String statusCode) {
        List compositions = getSession()
                .createQuery("from TeamCompositionEntity as eTeamComposition"
                        + " where eTeamComposition.team.id=? and eTeamComposition.status.code=?"
                ).setParameter(0, teamID)
                .setParameter(1, statusCode)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<TeamCompositionEntity>) compositions;
        else return null;
    }

    public List<TeamCompositionEntity> findByTeamAndStatus(TeamEntity team, TeamCompositionStatusEntity status) {
        List compositions = getSession()
                .createQuery("from TeamCompositionEntity as eTeamComposition"
                                + " where eTeamComposition.team=? and eTeamComposition.status=?"
                ).setParameter(0, team)
                .setParameter(1, status)
                .list();

        if ((compositions != null) && (compositions.size() > 0)) return (List<TeamCompositionEntity>) compositions;
        else return null;
    }
}
