package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.lease.PlaygroundDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.*;
import ru.vldf.sportsportal.dao.generic.definite.common.UserDAO;
import ru.vldf.sportsportal.domain.lease.PlaygroundEntity;
import ru.vldf.sportsportal.dto.tourney.*;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.domain.tourney.*;
import ru.vldf.sportsportal.domain.common.UserEntity;

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


    @Service
    public class UserTourneyService {

        private TeamDAO teamDAO;
        private TeamStatusDAO teamStatusDAO;
        private CompositionDAO compositionDAO;
        private CompositionMembershipDAO compositionMembershipDAO;

        private GameDAO gameDAO;
        private PlayerDAO playerDAO;

        @Autowired
        public void setTeamDAO(TeamDAO teamDAO) {
            this.teamDAO = teamDAO;
        }

        @Autowired
        public void setTeamStatusDAO(TeamStatusDAO teamStatusDAO) {
            this.teamStatusDAO = teamStatusDAO;
        }

        @Autowired
        public void setGameDAO(GameDAO gameDAO) {
            this.gameDAO = gameDAO;
        }

        @Autowired
        public void setPlayerDAO(PlayerDAO playerDAO) {
            this.playerDAO = playerDAO;
        }

        @Autowired
        public void setCompositionDAO(CompositionDAO compositionDAO) {
            this.compositionDAO = compositionDAO;
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
        public List<CompositionDTO> getCompositionList(TeamDTO team) {
//            TODO: optimize this
            List<CompositionEntity> entityList = compositionDAO.findByTeam(team.getId());

            if (entityList == null) return null;
            List<CompositionDTO> dtoList = new ArrayList<CompositionDTO>();
            for (CompositionEntity entity: entityList) dtoList.add(new CompositionDTO(entity));
            return dtoList;
        }

        @Transactional(readOnly = true)
        public CompositionDTO getComposition(int compositionID) {
//            TODO: optimize this
            UserDTO user = authService.getAuthUser();
            CompositionDTO composition = new CompositionDTO(compositionDAO.findByID(compositionID));

            if (user.equals(composition.getTeam().getCaptain())) return composition;
            else return null; //not user's composition
        }

        @Transactional(readOnly = true)
        public GameDTO getRival(int compositionID, int tourNum) {
            GameEntity entity = gameDAO.findByRivalID(compositionID, tourNum);
            if (entity != null) return new GameDTO(entity);
            else return null;
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
            List<PlayerEntity> entityList;

            if ((name.equals("")) && (patronymic.equals("")))
                entityList = playerDAO.findBySurname(surname);
            else if (patronymic.equals(""))
                entityList = playerDAO.findBySurnameAndName(surname, name);
            else if (name.equals(""))
                entityList = playerDAO.findBySurnameAndPatronymic(surname, patronymic);
            else
                entityList = playerDAO.findByFullName(name, surname, patronymic);

            if (entityList == null) return null;
            List<PlayerDTO> dtoList = new ArrayList<PlayerDTO>();
            for (PlayerEntity entity : entityList) dtoList.add(new PlayerDTO(entity));
            return dtoList;
        }


        @Transactional
        public void timeChoice(CompositionDTO composition, Integer time, Character choice) {
            StringBuilder timegrid = new StringBuilder(composition.getTimegrid());
            timegrid.setCharAt(time, choice);

            compositionDAO.updateTimegridByID(composition.getId(),timegrid.toString());
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


        private PlaygroundDAO playgroundDAO;

        @Autowired
        public void setPlaygroundDAO(PlaygroundDAO playgroundDAO) {
            this.playgroundDAO = playgroundDAO;
        }

        @Transactional
        public void confirmPlayground(int compositionID, Integer playgroundID) {
            PlaygroundEntity playground;
            if (playgroundID != null) playground = playgroundDAO.findByID(playgroundID);
            else playground = null;

            compositionDAO.updatePlaygroundByID(compositionID, playground);
        }

        private TourDAO tourDAO;

        @Autowired
        public void setTourDAO(TourDAO tourDAO) {
            this.tourDAO = tourDAO;
        }

        @Transactional(readOnly = true)
        public TourDTO getNextTour(TourneyDTO tourneyDTO, Integer tourNum) {
            return new TourDTO(tourDAO.findByTourney(tourneyDTO.getId(), tourNum));
        }
    }
}
