package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.*;
import ru.vldf.sportsportal.dao.generic.definite.common.UserDAO;
import ru.vldf.sportsportal.dto.tourney.CompositionDTO;
import ru.vldf.sportsportal.dto.tourney.TeamDTO;
import ru.vldf.sportsportal.dto.tourney.PlayerDTO;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.domain.tourney.*;
import ru.vldf.sportsportal.domain.common.UserEntity;

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
        private PlayerDAO playerDAO;
        private CompositionDAO compositionDAO;
        private CompositionStatusDAO compositionStatusDAO;
        private CompositionMembershipDAO compositionMembershipDAO;

        @Autowired
        public void setTeamDAO(TeamDAO teamDAO) {
            this.teamDAO = teamDAO;
        }

        @Autowired
        public void setTeamStatusDAO(TeamStatusDAO teamStatusDAO) {
            this.teamStatusDAO = teamStatusDAO;
        }

        @Autowired
        public void setTeamPlayerDAO(PlayerDAO playerDAO) {
            this.playerDAO = playerDAO;
        }

        @Autowired
        public void setCompositionDAO(CompositionDAO compositionDAO) {
            this.compositionDAO = compositionDAO;
        }

        @Autowired
        public void setCompositionStatusDAO(CompositionStatusDAO compositionStatusDAO) {
            this.compositionStatusDAO = compositionStatusDAO;
        }

        @Autowired
        public void setCompositionMembershipDAO(CompositionMembershipDAO compositionMembershipDAO) {
            this.compositionMembershipDAO = compositionMembershipDAO;
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
        public List<CompositionDTO> getCompositions(int teamID) {
            TeamDTO team = getTeam(teamID);
            if (team == null) return null; //not user's team

            List<CompositionEntity> entityList = compositionDAO.findByTeam(teamID);

            if (entityList == null) return null;
            List<CompositionDTO> dtoList = new ArrayList<CompositionDTO>();
            for (CompositionEntity entity: entityList) dtoList.add(new CompositionDTO(entity));
            return dtoList;
        }

        @Transactional(readOnly = true)
        public List<CompositionDTO> getRecruitingCompositions(int teamID) {
            TeamDTO team = getTeam(teamID);
            if (team == null) return null; //not user's team

            String COMPOSITION_RECRUITING_CODE = "COMPOSITION_RECRUITING";

            List<CompositionEntity> entityList = compositionDAO
                    .findByTeamAndStatus(teamID, COMPOSITION_RECRUITING_CODE);

            if (entityList == null) return null;
            List<CompositionDTO> dtoList = new ArrayList<CompositionDTO>();
            for (CompositionEntity entity: entityList) dtoList.add(new CompositionDTO(entity));
            return dtoList;
        }

        @Transactional(readOnly = true)
        public List<PlayerDTO> getPlayers(CompositionDTO composition) {
//            TODO: optimize this
            List<PlayerEntity> entityList = playerDAO.findByTeamComposition(composition.getId());

            if (entityList == null) return null;
            List<PlayerDTO> dtoList = new ArrayList<PlayerDTO>();
            for (PlayerEntity entity : entityList) dtoList.add(new PlayerDTO(entity));
            return dtoList;
        }

        @Transactional(readOnly = true)
        public List<PlayerDTO> getPlayers(String name, String surname, String patronymic) {
//            TODO: optimize this
            List<PlayerEntity> entityList = playerDAO.findByFullName(name, surname, patronymic);

            if (entityList == null) return null;
            List<PlayerDTO> dtoList = new ArrayList<PlayerDTO>();
            for (PlayerEntity entity: entityList) dtoList.add(new PlayerDTO(entity));
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
            CompositionMembershipEntity membership = new CompositionMembershipEntity();
            membership.setComposition(compositionDAO.findByID(compositionID));
            membership.setPlayer(playerDAO.findByID(playerID));

            compositionMembershipDAO.save(membership);
        }

        @Transactional
        public void deletePlayerFromComposition(Integer compositionID, Integer playerID) {
            compositionMembershipDAO.deleteByMembership(playerID, compositionID);
        }

        @Transactional
        public void confirmComposition(Integer compositionID) {
            String COMPOSITION_UNCONFIRMED_CODE = "COMPOSITION_UNCONFIRMED";
            compositionDAO.updateStatusByID(
                    compositionID,
                    compositionStatusDAO.findByCode(COMPOSITION_UNCONFIRMED_CODE)
            );
        }
    }
}
