package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.model.tourney.TeamTourneyEntity;
import ru.vldf.sportsportal.model.tourney.TeamTourneyStatusEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.List;

public interface TeamTourneyDAO {
    Integer saveTeamTourney(TeamTourneyEntity teamTourney);

    List<TeamTourneyEntity> getTeamTourneyList();

    List<TeamTourneyEntity> getTeamTourneyListByUser(UserEntity user);

    List<TeamTourneyEntity> getTeamTourneyListByStatus(String status);

    List<TeamTourneyEntity> getTeamTourneyListByStatus(TeamTourneyStatusEntity status);
}
