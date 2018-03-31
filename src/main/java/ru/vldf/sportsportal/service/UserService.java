package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyStatusDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyDTO;
import ru.vldf.sportsportal.model.tourney.TeamTourneyEntity;
import ru.vldf.sportsportal.model.tourney.TeamTourneyStatusEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional(readOnly = true)
    protected UserEntity getSelf() {
        return userDAO.findByID(
                authService.getAuthUser().getId()
        );
    }

    @Transactional(readOnly = true)
    public List<TeamTourneyDTO> getTeamTourneyList() {
        List<TeamTourneyEntity> entityList = teamTourneyDAO.getTeamTourneyListByUser(getSelf());
        List<TeamTourneyDTO> dtoList = new ArrayList<TeamTourneyDTO>();

        for (TeamTourneyEntity entity: entityList) dtoList.add(new TeamTourneyDTO(entity));
//        TODO: impl not lazy init

        return dtoList;
    }

    @Transactional
    public void createTeamTourney(TeamTourneyDTO teamTourneyDTO) {
        UserEntity captain = getSelf();
        TeamTourneyStatusEntity status = teamTourneyStatusDAO.findByCode("TEAM_AWAITING");
        teamTourneyDAO.saveTeamTourney(new TeamTourneyEntity(teamTourneyDTO, captain, status));
    }
}
