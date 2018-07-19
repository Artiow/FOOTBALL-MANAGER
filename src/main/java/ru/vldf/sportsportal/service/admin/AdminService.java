package ru.vldf.sportsportal.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.common.UserDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.PlayerDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.PlayerOwnershipDAO;
import ru.vldf.sportsportal.domain.common.UserEntity;
import ru.vldf.sportsportal.domain.tourney.PlayerEntity;
import ru.vldf.sportsportal.domain.tourney.PlayerOwnershipEntity;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.dto.tourney.PlayerDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final String ROLE_UNCONFIRMED_CODE = "ROLE_UNCONFIRMED";
    private final String ROLE_CONFIRMED_CODE = "ROLE_USER";
    private final String ROLE_REJECTED_CODE = "ROLE_REJECTED";

    private UserDAO userDAO;

    private PlayerDAO playerDAO;
    private PlayerOwnershipDAO ownershipDAO;


    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Autowired
    public void setOwnershipDAO(PlayerOwnershipDAO ownershipDAO) {
        this.ownershipDAO = ownershipDAO;
    }


    @Transactional(readOnly = true)
    public int getUnconfirmedUsersNum() {
        return userDAO.numByRole(ROLE_UNCONFIRMED_CODE).intValue();
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getUnconfirmedUsers() {
        List<UserEntity> entityList = userDAO.findByRole(ROLE_UNCONFIRMED_CODE);
        if (entityList == null) return null;

        List<UserDTO> dtoList = new ArrayList<UserDTO>();
        for (UserEntity entity: entityList) dtoList.add(new UserDTO(entity));
        return dtoList;
    }


//    =========================================
//    TODO: lol wtf dude? add pagination!
    @Transactional(readOnly = true)
    public UserDTO getFirstUnconfirmedUser() {
        List<UserEntity> entityList = userDAO.findByRole(ROLE_UNCONFIRMED_CODE);
        if (entityList == null) return null;

        return new UserDTO(entityList.get(0));
    }
//    =========================================

    @Transactional(readOnly = true)
    public UserDTO getUser(Integer id) {
        return new UserDTO(userDAO.findByID(id));
    }

    @Transactional(readOnly = true)
    public List<PlayerDTO> getDuplicates(UserDTO user) {
        List<PlayerEntity> entityList = playerDAO.findByFullName(
                user.getName(),
                user.getSurname(),
                user.getPatronymic()
        );

        if (entityList == null) return null;

        List<PlayerDTO> dtoList = new ArrayList<PlayerDTO>();
        for (PlayerEntity entity: entityList) dtoList.add(new PlayerDTO(entity));
        return dtoList;
    }


    @Transactional
    public void confirmUser(Integer id) {
        PlayerEntity newPlayerEntity = new PlayerEntity(userDAO.findByID(id));
        bindUser(id, playerDAO.save(newPlayerEntity));
    }

    @Transactional
    public void rejectUser(Integer id) {
        userDAO.updateRoleByID(id, ROLE_REJECTED_CODE);
    }

    @Transactional
    public void deleteDuplicate(Integer id) {
        playerDAO.deleteByID(id);
    }

    @Transactional
    public void bindUser(Integer userID, Integer playerID) {
        userDAO.updateRoleByID(userID, ROLE_CONFIRMED_CODE);

        PlayerOwnershipEntity newOwnershipEntity = new PlayerOwnershipEntity(
                userDAO.findByID(userID),
                playerDAO.findByID(playerID)
        );

        ownershipDAO.save(newOwnershipEntity);
    }
}
