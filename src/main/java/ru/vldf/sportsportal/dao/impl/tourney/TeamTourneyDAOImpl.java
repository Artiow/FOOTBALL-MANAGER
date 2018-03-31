package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyDAO;
import ru.vldf.sportsportal.model.tourney.TeamTourneyEntity;
import ru.vldf.sportsportal.model.tourney.TeamTourneyStatusEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.List;

@Repository
public class TeamTourneyDAOImpl extends AbstractDAOImpl<TeamTourneyEntity, Integer> implements TeamTourneyDAO {
    public TeamTourneyDAOImpl() {
        super(TeamTourneyEntity.class);
    }

    public Integer saveTeamTourney(TeamTourneyEntity teamTourney) {
        return save(teamTourney);
    }

    public List<TeamTourneyEntity> getTeamTourneyList() {
        return list();
    }

    public List<TeamTourneyEntity> getTeamTourneyListByUser(UserEntity user) {
        List teams = getSession()
                .createQuery("from TeamTourneyEntity where captain=?")
                .setParameter(0, user)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamTourneyEntity>) teams;
        else return null;
    }

    public List<TeamTourneyEntity> getTeamTourneyListByStatus(TeamTourneyStatusEntity status) {
        List teams = getSession()
                .createQuery("from TeamTourneyEntity where status=?")
                .setParameter(0, status)
                .list();

        if ((teams != null) && (teams.size() > 0)) return (List<TeamTourneyEntity>) teams;
        else return null;
    }
}