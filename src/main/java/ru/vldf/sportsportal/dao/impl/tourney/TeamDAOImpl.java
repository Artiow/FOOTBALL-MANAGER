package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamDAO;
import ru.vldf.sportsportal.domain.tourney.TeamEntity;
import ru.vldf.sportsportal.domain.tourney.TeamStatusEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;
import ru.vldf.sportsportal.domain.common.UserEntity;

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
                .createQuery("from TeamEntity where captain.id=? order by name")
                .setParameter(0, userID)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public List<TeamEntity> findByUser(UserEntity user) {
        List teams = getSession()
                .createQuery("from TeamEntity where captain=? order by name")
                .setParameter(0, user)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public List<TeamEntity> findByTourney(Integer tourneyID) {
        List teams = getSession()
                .createQuery("select t from TeamEntity as t, CompositionEntity as c"
                        + " where c.tourney.id=? and c.team = t order by t.name"
                ).setParameter(0, tourneyID)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public List<TeamEntity> findByTourney(TourneyEntity tourney) {
        List teams = getSession()
                .createQuery("select t from TeamEntity as t, CompositionEntity as c"
                        + " where c.tourney=? and c.team = t order by t.name"
                ).setParameter(0, tourney)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public Long numByStatus(Integer statusID) {
        List count = getSession()
                .createQuery("select count(*) from TeamEntity where status.id=?")
                .setParameter(0, statusID)
                .list();

        if ((count != null) && (count.size() == 1)) return ((Long) count.get(0));
        else return null;
    }

    public Long numByStatus(String statusCode) {
        List count = getSession()
                .createQuery("select count(*) from TeamEntity where status.code=?")
                .setParameter(0, statusCode)
                .list();

        if ((count != null) && (count.size() == 1)) return ((Long) count.get(0));
        else return null;
    }

    public Long numByStatus(TeamStatusEntity status) {
        List count = getSession()
                .createQuery("select count(*) from TeamEntity where status=?")
                .setParameter(0, status)
                .list();

        if ((count != null) && (count.size() == 1)) return ((Long) count.get(0));
        else return null;
    }

    public List<TeamEntity> findByStatus(Integer statusID) {
        List teams = getSession()
                .createQuery("from TeamEntity where status.id=? order by name")
                .setParameter(0, statusID)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public List<TeamEntity> findByStatus(String statusCode) {
        List teams = getSession()
                .createQuery("from TeamEntity where status.code=? order by name")
                .setParameter(0, statusCode)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public List<TeamEntity> findByStatus(TeamStatusEntity status) {
        List teams = getSession()
                .createQuery("from TeamEntity where status=? order by name")
                .setParameter(0, status)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public List<TeamEntity> findByNameLike(String name) {
        List teams = getSession()
                .createQuery("from TeamEntity where name like ? order by name")
                .setParameter(0, "%" + name + "%")
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamEntity>) teams;
        else return null;
    }

    public List<TeamEntity> findAll() {
        return super.list();
    }

//    ==================================================================================
//    === UPDATE

    public void updateNameByID(Integer id, String name) {
        getSession()
                .createQuery("update TeamEntity set name=:name where id=:id")
                .setParameter("name", name)
                .setParameter("id", id)
                .executeUpdate();
    }

    public void updateStatusByID(Integer id, TeamStatusEntity status) {
        getSession()
                .createQuery("update TeamEntity set status=:status where id=:id")
                .setParameter("status", status)
                .setParameter("id", id)
                .executeUpdate();
    }
}