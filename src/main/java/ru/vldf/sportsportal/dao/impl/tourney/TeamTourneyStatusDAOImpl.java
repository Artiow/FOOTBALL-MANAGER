package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyStatusDAO;
import ru.vldf.sportsportal.model.tourney.TeamTourneyEntity;
import ru.vldf.sportsportal.model.tourney.TeamTourneyStatusEntity;
import ru.vldf.sportsportal.model.user.RoleEntity;

import java.util.List;

@Repository
public class TeamTourneyStatusDAOImpl extends AbstractDAOImpl<TeamTourneyStatusEntity, Integer> implements TeamTourneyStatusDAO {
    public TeamTourneyStatusDAOImpl() {
        super(TeamTourneyStatusEntity.class);
    }

    public TeamTourneyStatusEntity findByCode(String code) {
        List statuses = getSession()
                .createQuery("from TeamTourneyStatusEntity where code=?")
                .setParameter(0, code)
                .list();

        if ((statuses != null) && (statuses.size() > 0)) return (TeamTourneyStatusEntity) statuses.get(0);
        else return null;
    }
}