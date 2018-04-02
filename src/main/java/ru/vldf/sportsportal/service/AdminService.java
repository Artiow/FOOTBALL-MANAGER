package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.RoleDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyConfirmDTO;
import ru.vldf.sportsportal.dto.user.UserDTO;
import ru.vldf.sportsportal.model.tourney.TeamTourneyEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

//    ==================================================================================
//    === USER

    private UserDAO userDAO;
    private RoleDAO roleDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    private String ROLE_UNCONFIRMED_CODE = "ROLE_UNCONFIRMED";
    private String ROLE_CONFIRMED_CODE = "ROLE_USER";

    @Transactional(readOnly = true)
    public int getUnconfirmedUsersNum() {
        return userDAO.numByRoleCode(ROLE_UNCONFIRMED_CODE).intValue();
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getUnconfirmedUsers() {
        List<UserEntity> entityList = userDAO.findByRoleCode(ROLE_UNCONFIRMED_CODE);
        List<UserDTO> dtoList = new ArrayList<UserDTO>();

        for (UserEntity entity: entityList) dtoList.add(new UserDTO(entity));
//        TODO: impl not lazy init

        return dtoList;
    }

    @Transactional(readOnly = true)
    public UserDTO getFirstUnconfirmedUser() {
        List<UserEntity> entityList = userDAO.findByRoleCode(ROLE_UNCONFIRMED_CODE);
        return new UserDTO(entityList.get(0));
    }

    @Transactional(readOnly = true)
    public UserDTO getUser(Integer id) {
        return new UserDTO(userDAO.findByID(id));
    }

    @Transactional
    public void confirmUser(Integer id) {
        userDAO.updateRoleByID(id, roleDAO.findByCode(ROLE_CONFIRMED_CODE));
    }

//    ==================================================================================
//    === TOURNEY

    private TeamTourneyDAO teamTourneyDAO;

    @Autowired
    public void setTeamTourneyDAO(TeamTourneyDAO teamTourneyDAO) {
        this.teamTourneyDAO = teamTourneyDAO;
    }

    @Transactional(readOnly = true)
    public List<TeamTourneyConfirmDTO> getAwaitingTeamTourneyList() {
        List<TeamTourneyEntity> entityList = teamTourneyDAO.getTeamTourneyListByStatus("TEAM_AWAITING");
        List<TeamTourneyConfirmDTO> dtoList = new ArrayList<TeamTourneyConfirmDTO>();

        for (TeamTourneyEntity entity: entityList) dtoList.add(new TeamTourneyConfirmDTO(entity));
//        TODO: impl not lazy init

        return dtoList;
    }

    @Transactional
    public void confirmAwaitingTeamTourneyList(List<TeamTourneyConfirmDTO> listTeamTourneyConfirmDTO) {
//        TODO: confirmAwaitingTeamTourneyList
        listTeamTourneyConfirmDTO.size();
    }
}
