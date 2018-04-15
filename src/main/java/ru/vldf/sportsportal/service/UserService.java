package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.*;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.dto.tourney.TeamCompositionDTO;
import ru.vldf.sportsportal.dto.tourney.TeamDTO;
import ru.vldf.sportsportal.dto.tourney.TeamPlayerDTO;
import ru.vldf.sportsportal.dto.user.UserDTO;
import ru.vldf.sportsportal.model.tourney.*;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserDAO userDAO;
    private AuthService authService;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Service
    public class UserTourneyService {
        private TeamDAO teamDAO;
        private TeamStatusDAO teamStatusDAO;
        private TeamPlayerDAO teamPlayerDAO;
        private TeamCompositionDAO teamCompositionDAO;
        private TeamCompositionStatusDAO teamCompositionStatusDAO;
        private TeamCompositionMembershipDAO teamCompositionMembershipDAO;

        @Autowired
        public void setTeamDAO(TeamDAO teamDAO) {
            this.teamDAO = teamDAO;
        }

        @Autowired
        public void setTeamStatusDAO(TeamStatusDAO teamStatusDAO) {
            this.teamStatusDAO = teamStatusDAO;
        }

        @Autowired
        public void setTeamPlayerDAO(TeamPlayerDAO teamPlayerDAO) {
            this.teamPlayerDAO = teamPlayerDAO;
        }

        @Autowired
        public void setTeamCompositionDAO(TeamCompositionDAO teamCompositionDAO) {
            this.teamCompositionDAO = teamCompositionDAO;
        }

        @Autowired
        public void setTeamCompositionStatusDAO(TeamCompositionStatusDAO teamCompositionStatusDAO) {
            this.teamCompositionStatusDAO = teamCompositionStatusDAO;
        }

        @Autowired
        public void setTeamCompositionMembershipDAO(TeamCompositionMembershipDAO teamCompositionMembershipDAO) {
            this.teamCompositionMembershipDAO = teamCompositionMembershipDAO;
        }



        @Transactional(readOnly = true)
        public List<TeamDTO> getTeamList() {
//            TODO: optimize this
            List<TeamEntity> entityList = teamDAO.findByUser(authService.getAuthUser().getId());

            if (entityList == null) return null;
            List<TeamDTO> dtoList = new ArrayList<TeamDTO>();
            for (TeamEntity entity: entityList) dtoList.add(new TeamDTO(entity));
            return dtoList;
        }

        @Transactional(readOnly = true)
        public TeamDTO getTeam(int teamID) {
//            TODO: optimize this
            UserDTO user = authService.getAuthUser();
            TeamDTO team = new TeamDTO(teamDAO.findByID(teamID));
            if (user.equals(team.getCaptain())) return team;
            else return null; //not user's team
        }

        @Transactional(readOnly = true)
        public List<TeamCompositionDTO> getCompositions(int teamID) {
            TeamDTO team = getTeam(teamID);
            if (team == null) return null; //not user's team

            List<TeamCompositionEntity> entityList = teamCompositionDAO.findByTeam(teamID);

            if (entityList == null) return null;
            List<TeamCompositionDTO> dtoList = new ArrayList<TeamCompositionDTO>();
            for (TeamCompositionEntity entity: entityList) dtoList.add(new TeamCompositionDTO(entity));
            return dtoList;
        }

        @Transactional(readOnly = true)
        public List<TeamCompositionDTO> getRecruitingCompositions(int teamID) {
            TeamDTO team = getTeam(teamID);
            if (team == null) return null; //not user's team

            String COMPOSITION_RECRUITING_CODE = "COMPOSITION_RECRUITING";

            List<TeamCompositionEntity> entityList = teamCompositionDAO
                    .findByTeamAndStatus(teamID, COMPOSITION_RECRUITING_CODE);

            if (entityList == null) return null;
            List<TeamCompositionDTO> dtoList = new ArrayList<TeamCompositionDTO>();
            for (TeamCompositionEntity entity: entityList) dtoList.add(new TeamCompositionDTO(entity));
            return dtoList;
        }

        @Transactional(readOnly = true)
        public List<TeamPlayerDTO> getPlayers(Integer compositionID) {
//            TODO: optimize this
            List<TeamPlayerEntity> entityList = teamPlayerDAO.findByTeamComposition(compositionID);

            if (entityList == null) return null;
            List<TeamPlayerDTO> dtoList = new ArrayList<TeamPlayerDTO>();
            for (TeamPlayerEntity entity : entityList) dtoList.add(new TeamPlayerDTO(entity));
            return dtoList;
        }

        @Transactional(readOnly = true)
        public List<TeamPlayerDTO> getPlayers(String name, String surname, String patronymic) {
//            TODO: optimize this
            List<TeamPlayerEntity> entityList = teamPlayerDAO.findByFullName(name, surname, patronymic);

            if (entityList == null) return null;
            List<TeamPlayerDTO> dtoList = new ArrayList<TeamPlayerDTO>();
            for (TeamPlayerEntity entity: entityList) dtoList.add(new TeamPlayerDTO(entity));
            return dtoList;
        }

        @Transactional
        public void createTeam(TeamDTO teamDTO) {
//            TODO: optimize this
            UserEntity captain = userDAO.findByID(authService.getAuthUser().getId());
            TeamStatusEntity status = teamStatusDAO.findByCode("TEAM_UNCONFIRMED");
            teamDAO.save(new TeamEntity(teamDTO, captain, status));
        }

        @Transactional
        public void addPlayerToComposition(Integer compositionID, Integer playerID) {
            TeamCompositionMembershipEntity membership = new TeamCompositionMembershipEntity();
            membership.setComposition(teamCompositionDAO.findByID(compositionID));
            membership.setPlayer(teamPlayerDAO.findByID(playerID));

            teamCompositionMembershipDAO.save(membership);
        }

        @Transactional
        public void deletePlayerFromComposition(Integer compositionID, Integer playerID) {
            teamCompositionMembershipDAO.deleteByMembership(playerID, compositionID);
        }
    }
}
