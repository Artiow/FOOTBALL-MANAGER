package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamPlayerDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamStatusDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.UserRoleDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.dto.tourney.TeamDTO;
import ru.vldf.sportsportal.dto.tourney.TeamPlayerDTO;
import ru.vldf.sportsportal.dto.user.UserDTO;
import ru.vldf.sportsportal.model.tourney.TeamEntity;
import ru.vldf.sportsportal.model.tourney.TeamPlayerEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

//    ==================================================================================
//    === USER

    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    private String ROLE_UNCONFIRMED_CODE = "ROLE_UNCONFIRMED";

    @Transactional(readOnly = true)
    public int getUnconfirmedUsersNum() {
        return userDAO.numByRole(ROLE_UNCONFIRMED_CODE).intValue();
    }

    @Transactional(readOnly = true)
    public UserDTO getFirstUnconfirmedUser() {
        List<UserEntity> entityList = userDAO.findByRole(ROLE_UNCONFIRMED_CODE);
        return new UserDTO(entityList.get(0));
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getUnconfirmedUsers() {
        List<UserEntity> entityList = userDAO.findByRole(ROLE_UNCONFIRMED_CODE);
        if (entityList == null) return null;

        List<UserDTO> dtoList = new ArrayList<UserDTO>();
        for (UserEntity entity: entityList) dtoList.add(new UserDTO(entity));
        return dtoList;
    }

    @Transactional(readOnly = true)
    public UserDTO getUser(Integer id) {
        return new UserDTO(userDAO.findByID(id));
    }

    @Transactional
    public void confirmUser(Integer id) {
        String ROLE_CONFIRMED_CODE = "ROLE_USER";
        userDAO.updateRoleByID(
                id,
                userRoleDAO.findByCode(ROLE_CONFIRMED_CODE)
        );
    }

    @Transactional
    public void rejectUser(Integer id) {
        String ROLE_REJECTED_CODE = "ROLE_REJECTED";
        userDAO.updateRoleByID(
                id,
                userRoleDAO.findByCode(ROLE_REJECTED_CODE)
        );
    }

    private TeamPlayerDAO teamPlayerDAO;

    @Autowired
    public void setTeamPlayerDAO(TeamPlayerDAO teamPlayerDAO) {
        this.teamPlayerDAO = teamPlayerDAO;
    }

    @Transactional(readOnly = true)
    public List<TeamPlayerDTO> getDuplicate(UserDTO user) {
        List<TeamPlayerEntity> entityList = teamPlayerDAO.findByFullName(user.getName(), user.getSurname(), user.getPatronymic());
        if (entityList == null) return null;

        List<TeamPlayerDTO> dtoList = new ArrayList<TeamPlayerDTO>();
        for (TeamPlayerEntity entity: entityList) dtoList.add(new TeamPlayerDTO(entity));
        return dtoList;
    }

    @Transactional
    public void deleteDuplicate(Integer id) {
        teamPlayerDAO.deleteByID(id);
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

    private String TEAM_AWAITING_CODE = "TEAM_AWAITING";

    @Transactional(readOnly = true)
    public int getUnconfirmedTeamsNum() {
        return teamDAO.numByStatus(TEAM_AWAITING_CODE).intValue();
    }

    @Transactional(readOnly = true)
    public List<TeamDTO> getUnconfirmedTeams() {
        List<TeamEntity> entityList = teamDAO.findByStatus(TEAM_AWAITING_CODE);
        if (entityList == null) return null;

        List<TeamDTO> dtoList = new ArrayList<TeamDTO>();
        for (TeamEntity entity: entityList) dtoList.add(new TeamDTO(entity));
        return dtoList;
    }

    @Transactional
    public void confirmTeam(Integer id) {
        String TEAM_CONFIRMED_CODE = "TEAM_CONFIRMED";
        teamDAO.updateStatusByID(
                id,
                teamStatusDAO.findByCode(TEAM_CONFIRMED_CODE)
        );
    }

    @Transactional
    public void rejectTeam(Integer id) {
        String TEAM_REJECTED_CODE = "TEAM_REJECTED";
        teamDAO.updateStatusByID(
                id,
                teamStatusDAO.findByCode(TEAM_REJECTED_CODE)
        );
    }
}
