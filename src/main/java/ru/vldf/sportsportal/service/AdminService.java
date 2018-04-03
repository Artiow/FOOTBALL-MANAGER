package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.UserRoleDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.dto.user.UserDTO;
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

//    ==================================================================================
//    === TOURNEY

    private TeamDAO teamDAO;

    @Autowired
    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

//    TODO: confirm team methods!
}
