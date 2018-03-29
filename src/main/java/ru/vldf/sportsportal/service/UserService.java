package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyStatusDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.RoleDAO;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyDTO;
import ru.vldf.sportsportal.dto.user.UserDTO;
import ru.vldf.sportsportal.model.tourney.TeamTourneyEntity;
import ru.vldf.sportsportal.model.tourney.TeamTourneyStatusEntity;
import ru.vldf.sportsportal.model.user.RoleEntity;
import ru.vldf.sportsportal.model.user.UserEntity;
import ru.vldf.sportsportal.service.security.SecurityPrincipal;

@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserDAO userDAO;
    private RoleDAO roleDAO;

    private TeamTourneyDAO teamTourneyDAO;
    private TeamTourneyStatusDAO teamTourneyStatusDAO;

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setTeamTourneyDAO(TeamTourneyDAO teamTourneyDAO) {
        this.teamTourneyDAO = teamTourneyDAO;
    }

    @Autowired
    public void setTeamTourneyStatusDAO(TeamTourneyStatusDAO teamTourneyStatusDAO) {
        this.teamTourneyStatusDAO = teamTourneyStatusDAO;
    }

//    ==================================================================================
//    === TOURNEY

    public void createTeam(TeamTourneyDTO teamTourneyDTO) {
        TeamTourneyStatusEntity status = teamTourneyStatusDAO.findByCode("TEAM_AWAITING");
        UserEntity captain = userDAO.findByID(getAuthUser().getId());

        teamTourneyDAO.saveTeamTourney(new TeamTourneyEntity(teamTourneyDTO, captain, status));
    }

//    ==================================================================================
//    === AUTH

    private final String ROLE_ANONYMOUS = "anonymousUser";

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void register(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        RoleEntity role = roleDAO.findByCode("ROLE_USER");

        userDAO.saveUser(new UserEntity(userDTO, role));
    }

    public UserDTO getAuthUser() {
        Authentication auth = getAuthentication();

        Object principal = auth.getPrincipal();
        if ((principal == null) || (principal.equals(ROLE_ANONYMOUS))) return null;
        else return ((SecurityPrincipal) principal).getUser();
    }

    public String getAuthUsername() {
        UserDTO user = getAuthUser();

        if (user != null) return user.toString();
        else return "ERROR";
    }
}