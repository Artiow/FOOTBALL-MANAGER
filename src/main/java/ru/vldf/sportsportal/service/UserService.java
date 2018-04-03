package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamStatusDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.dto.tourney.TeamDTO;
import ru.vldf.sportsportal.model.tourney.TeamEntity;
import ru.vldf.sportsportal.model.tourney.TeamStatusEntity;
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

    private TeamDAO teamDAO;
    private TeamStatusDAO teamStatusDAO;

    @Autowired
    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Autowired
    public void setTeamStatusDAO(TeamStatusDAO teamStatusDAO) {
        this.teamStatusDAO = teamStatusDAO;
    }

    @Transactional(readOnly = true)
    public List<TeamDTO> getTeamList() {
        List<TeamEntity> entityList = teamDAO.findByUser(
                authService.getAuthUser().getId()
        );

        List<TeamDTO> dtoList = new ArrayList<TeamDTO>();
        for (TeamEntity entity: entityList) dtoList.add(new TeamDTO(entity));
        return dtoList;
    }

    @Transactional
    public void createTeam(TeamDTO teamDTO) {
        UserEntity captain = userDAO.findByID(
                authService.getAuthUser().getId()
        );

        TeamStatusEntity status = teamStatusDAO.findByCode("TEAM_AWAITING");
        teamDAO.save(new TeamEntity(teamDTO, captain, status));
    }
}
