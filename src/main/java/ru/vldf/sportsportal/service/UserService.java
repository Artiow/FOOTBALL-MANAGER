package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyStatusDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyDTO;
import ru.vldf.sportsportal.model.tourney.TeamTourneyEntity;
import ru.vldf.sportsportal.model.tourney.TeamTourneyStatusEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

@Service
public class UserService {
    private AuthService authService;
    private UserDAO userDAO;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

//    ==================================================================================
//    === TOURNEY

    private TeamTourneyDAO teamTourneyDAO;
    private TeamTourneyStatusDAO teamTourneyStatusDAO;

    @Autowired
    public void setTeamTourneyDAO(TeamTourneyDAO teamTourneyDAO) {
        this.teamTourneyDAO = teamTourneyDAO;
    }

    @Autowired
    public void setTeamTourneyStatusDAO(TeamTourneyStatusDAO teamTourneyStatusDAO) {
        this.teamTourneyStatusDAO = teamTourneyStatusDAO;
    }

    public void createTeam(TeamTourneyDTO teamTourneyDTO) {
        TeamTourneyStatusEntity status = teamTourneyStatusDAO.findByCode("TEAM_AWAITING");
        UserEntity captain = userDAO.findByID(
                authService.getAuthUser().getId()
        );

        teamTourneyDAO.saveTeamTourney(new TeamTourneyEntity(teamTourneyDTO, captain, status));
    }
}
