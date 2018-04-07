package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamDAO;
import ru.vldf.sportsportal.model.tourney.TeamEntity;
import ru.vldf.sportsportal.model.tourney.TeamStatusEntity;
import ru.vldf.sportsportal.model.tourney.TourneyEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.List;

@Repository
public class TeamDAOImpl extends AbstractDAOImpl<TeamEntity, Integer> implements TeamDAO {
    public TeamDAOImpl() {
        super(TeamEntity.class);
    }

    @Override
    public Integer save(TeamEntity teamTourney) {
        return super.save(teamTourney);
    }

//    ==================================================================================
//    === FIND

    public TeamEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<TeamEntity> findByUser(Integer userID) {
        List teams = getSession()
                .createQuery("from TeamEntity where captain.id=?")
                .setParameter(0, userID)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public List<TeamEntity> findByUser(UserEntity user) {
        List teams = getSession()
                .createQuery("from TeamEntity where captain=?")
                .setParameter(0, user)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public List<TeamEntity> findByTourney(Integer tourneyID) {
        List teams = getSession() //TODO: fix!!!
                .createQuery("from TeamEntity ")
//                .setParameter(0, tourneyID)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public List<TeamEntity> findByTourney(TourneyEntity tourney) {
        List teams = getSession() //TODO: fix!!!
                .createQuery("from TeamEntity")
//                .setParameter(0, tourneyID)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public Long numByStatus(Integer statusID) {
        List count = getSession()
                .createQuery("select count(*) from TeamEntity where status.id=?")
                .setParameter(0, statusID)
                .list(); //TODO: list?

        if ((count != null) && (count.size() > 0)) return ((Long) count.get(0));
        else return null;
    }

    public Long numByStatus(String statusCode) {
        List count = getSession()
                .createQuery("select count(*) from TeamEntity where status.code=?")
                .setParameter(0, statusCode)
                .list(); //TODO: list?

        if ((count != null) && (count.size() > 0)) return ((Long) count.get(0));
        else return null;
    }

    public Long numByStatus(TeamStatusEntity status) {
        List count = getSession()
                .createQuery("select count(*) from TeamEntity where status=?")
                .setParameter(0, status)
                .list(); //TODO: list?

        if ((count != null) && (count.size() > 0)) return ((Long) count.get(0));
        else return null;
    }

    public List<TeamEntity> findByStatus(Integer statusID) {
        List teams = getSession()
                .createQuery("from TeamEntity where status.id=?")
                .setParameter(0, statusID)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public List<TeamEntity> findByStatus(String statusCode) {
        List teams = getSession()
                .createQuery("from TeamEntity where status.code=?")
                .setParameter(0, statusCode)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public List<TeamEntity> findByStatus(TeamStatusEntity status) {
        List teams = getSession()
                .createQuery("from TeamEntity where status=?")
                .setParameter(0, status)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

//    ==================================================================================
//    === UPDATE

    public Integer updateStatusByID(Integer id, TeamStatusEntity status) {
        return getSession()
                .createQuery("update TeamEntity set status=? where id=?")
                .setParameter(0, status)
                .setParameter(1, id)
                .executeUpdate();
    }
}