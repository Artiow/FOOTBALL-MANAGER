package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.*;
import ru.vldf.sportsportal.dao.generic.definite.user.UserRoleDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.dto.tourney.TeamDTO;
import ru.vldf.sportsportal.dto.tourney.TeamPlayerDTO;
import ru.vldf.sportsportal.dto.tourney.TourneyDTO;
import ru.vldf.sportsportal.dto.user.UserDTO;
import ru.vldf.sportsportal.model.tourney.TeamEntity;
import ru.vldf.sportsportal.model.tourney.TeamPlayerEntity;
import ru.vldf.sportsportal.model.tourney.TourneyEntity;
import ru.vldf.sportsportal.model.tourney.TourneyStatusEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

//    ==================================================================================
//    === USER

    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;
    private TeamPlayerDAO teamPlayerDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    @Autowired
    public void setTeamPlayerDAO(TeamPlayerDAO teamPlayerDAO) {
        this.teamPlayerDAO = teamPlayerDAO;
    }

    private final String ROLE_UNCONFIRMED_CODE = "ROLE_UNCONFIRMED";

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

    @Transactional(readOnly = true)
    public UserDTO getUser(Integer id) {
        return new UserDTO(userDAO.findByID(id));
    }

    @Transactional(readOnly = true)
    public UserDTO getFirstUnconfirmedUser() {
        List<UserEntity> entityList = userDAO.findByRole(ROLE_UNCONFIRMED_CODE);
        return new UserDTO(entityList.get(0));
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

    @Transactional
    public void deleteDuplicate(Integer id) {
        teamPlayerDAO.deleteByID(id);
    }

//    ==================================================================================
//    === TOURNEY

    private TeamDAO teamDAO;
    private TeamStatusDAO teamStatusDAO;
    private TourneyDAO tourneyDAO;
    private TourneyStatusDAO tourneyStatusDAO;

    @Autowired
    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Autowired
    public void setTeamStatusDAO(TeamStatusDAO teamStatusDAO) {
        this.teamStatusDAO = teamStatusDAO;
    }

    @Autowired
    public void setTourneyDAO(TourneyDAO tourneyDAO) {
        this.tourneyDAO = tourneyDAO;
    }

    @Autowired
    public void setTourneyStatusDAO(TourneyStatusDAO tourneyStatusDAO) {
        this.tourneyStatusDAO = tourneyStatusDAO;
    }

    private final String TEAM_UNCONFIRMED_CODE = "TEAM_UNCONFIRMED";

    @Transactional(readOnly = true)
    public int getUnconfirmedTeamsNum() {
        return teamDAO.numByStatus(TEAM_UNCONFIRMED_CODE).intValue();
    }

    @Transactional(readOnly = true)
    public List<TeamDTO> getUnconfirmedTeams() {
        List<TeamEntity> entityList = teamDAO.findByStatus(TEAM_UNCONFIRMED_CODE);
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

    @Transactional(readOnly = true)
    public int getTourneysNum() {
        return tourneyDAO.numAll().intValue();
    }

    @Transactional(readOnly = true)
    public List<TourneyDTO> getTourneysList() {
        List<TourneyEntity> entityList = tourneyDAO.findAll();
        if (entityList == null) return null;

        List<TourneyDTO> dtoList = new ArrayList<TourneyDTO>();
        for (TourneyEntity entity: entityList) dtoList.add(new TourneyDTO(entity));
        return dtoList;
    }

    @Transactional(readOnly = true)
    public TourneyDTO getTourney(Integer id) {
        return new TourneyDTO(tourneyDAO.findByID(id));
    }

    @Transactional
    public void createTourney(TourneyDTO tourneyDTO) {
        TourneyStatusEntity status = tourneyStatusDAO.findByCode("TOURNEY_FORMED");
        tourneyDAO.save(new TourneyEntity(tourneyDTO, status));
    }

    @Transactional(readOnly = true)
    public List<TeamDTO> getTeams(TourneyDTO tourney) {
        List<TeamEntity> entityList = teamDAO.findByTourney(tourney.getId());
        if (entityList == null) return null;

        List<TeamDTO> dtoList = new ArrayList<TeamDTO>();
        for (TeamEntity entity: entityList) dtoList.add(new TeamDTO(entity));
        return dtoList;
    }
}
