package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.model.tourney.TeamTourneyStatusEntity;

public interface TeamTourneyStatusDAO {
    TeamTourneyStatusEntity findByCode(String code);
}
