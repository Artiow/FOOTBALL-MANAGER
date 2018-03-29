package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyDAO;
import ru.vldf.sportsportal.model.tourney.TeamTourneyEntity;

@Repository
public class TeamTourneyDAOImpl extends AbstractDAOImpl<TeamTourneyEntity, Integer> implements TeamTourneyDAO {
    public TeamTourneyDAOImpl() {
        super(TeamTourneyEntity.class);
    }

    public Integer saveTeamTourney(TeamTourneyEntity teamTourney) {
        return save(teamTourney);
    }
}