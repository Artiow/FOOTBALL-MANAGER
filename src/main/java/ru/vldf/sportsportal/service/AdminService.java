package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.*;
import ru.vldf.sportsportal.dao.generic.definite.common.UserRoleDAO;
import ru.vldf.sportsportal.dao.generic.definite.common.UserDAO;
import ru.vldf.sportsportal.dto.tourney.*;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.domain.tourney.*;
import ru.vldf.sportsportal.domain.common.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Service //USER
    public class AdminUserService {
        private UserDAO userDAO;
        private UserRoleDAO userRoleDAO;

        private PlayerDAO playerDAO;
        private PlayerOwnershipDAO playerOwnershipDAO;

        @Autowired
        public void setUserDAO(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Autowired
        public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
            this.userRoleDAO = userRoleDAO;
        }

        @Autowired
        public void setPlayerDAO(PlayerDAO playerDAO) {
            this.playerDAO = playerDAO;
        }

        @Autowired
        public void setPlayerOwnershipDAO(PlayerOwnershipDAO playerOwnershipDAO) {
            this.playerOwnershipDAO = playerOwnershipDAO;
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
            if ((entityList == null) || (entityList.isEmpty())) return null;

            return new UserDTO(entityList.get(0)); //TODO: lol wtf dude? add pagination!
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
//            TODO: optimize this?
            Integer playerID = playerDAO.save(new PlayerEntity(userDAO.findByID(id)));
            bindUser(id, playerID);
        }

        @Transactional
        public void rejectUser(Integer id) {
            String ROLE_REJECTED_CODE = "ROLE_REJECTED";
            userDAO.updateRoleByID(id, userRoleDAO.findByCode(ROLE_REJECTED_CODE));
        }

        @Transactional
        public void deleteDuplicate(Integer id) {
            playerDAO.deleteByID(id);
        }

        @Transactional
        public void bindUser(Integer userID, Integer playerID) {
            String ROLE_CONFIRMED_CODE = "ROLE_USER";
            userDAO.updateRoleByID(userID, userRoleDAO.findByCode(ROLE_CONFIRMED_CODE));

            playerOwnershipDAO.save(new PlayerOwnershipEntity(
                    userDAO.findByID(userID),
                    playerDAO.findByID(playerID)
            )); //TODO: wut? fix it u bastard!
        }

    }


    @Service //TOURNEY
    public class AdminTourneyService {
        private TeamDAO teamDAO;
        private TeamStatusDAO teamStatusDAO;
        private CompositionDAO compositionDAO;
        private CompositionStatusDAO compositionStatusDAO;
        private TourneyDAO tourneyDAO;
        private TourneyStatusDAO tourneyStatusDAO;
        private GameDAO gameDAO;

        @Autowired
        public void setTeamDAO(TeamDAO teamDAO) {
            this.teamDAO = teamDAO;
        }

        @Autowired
        public void setTeamStatusDAO(TeamStatusDAO teamStatusDAO) {
            this.teamStatusDAO = teamStatusDAO;
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
        public void setTourneyDAO(TourneyDAO tourneyDAO) {
            this.tourneyDAO = tourneyDAO;
        }

        @Autowired
        public void setTourneyStatusDAO(TourneyStatusDAO tourneyStatusDAO) {
            this.tourneyStatusDAO = tourneyStatusDAO;
        }

        @Autowired
        public void setGameDAO(GameDAO gameDAO) {
            this.gameDAO = gameDAO;
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

        @Transactional(readOnly = true)
        public List<TeamDTO> getTeamList() {
            List<TeamEntity> entityList = teamDAO.findAll();
            if (entityList == null) return null;

            List<TeamDTO> dtoList = new ArrayList<TeamDTO>();
            for (TeamEntity entity: entityList) dtoList.add(new TeamDTO(entity));
            return dtoList;
        }

        @Transactional(readOnly = true)
        public TeamDTO getTeam(Integer id) {
            return new TeamDTO(teamDAO.findByID(id));
        }


        @Transactional(readOnly = true)
        public List<TourneyDTO> getTourneyList() {
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

        @Transactional(readOnly = true)
        public List<GameDTO> getGames(TourneyDTO tourney) {
            List<GameEntity> entityList = gameDAO.findByTourney(tourney.getId());
            if (entityList == null) return null;

            List<GameDTO> dtoList = new ArrayList<GameDTO>();
            for (GameEntity entity: entityList) dtoList.add(new GameDTO(entity));
            return dtoList;
        }

        @Transactional(readOnly = true)
        public List<String[]> getTimegrid(List<GameDTO> games) {
            return null; //TODO:
        }

        @Transactional
        public List<String[]> updateTimegrid(List<GameDTO> games) {
            char[] t1, t2;
            String[] str;
            StringBuffer buffer;
            List<String[]> result = new ArrayList<String[]>(games.size());
            for (GameDTO game : games) {
                t1 = compositionDAO.findByID(game.getRed().getId()).getTimegrid().toCharArray();
                t2 = compositionDAO.findByID(game.getBlue().getId()).getTimegrid().toCharArray();

                int length = t1.length;
                if (length != t2.length) return null;

                str = new String[length];
                buffer = new StringBuffer(length);

                char c1, c2, c;
                for (int i = 0; i < length; i++) {
                    c1 = t1[i];
                    c2 = t2[i];

                    if (c1 == c2) c = c1;
                    else if ((c1 == 'N') || (c2 == 'N')) c = 'N';
                    else if ((c1 == 'C') || (c2 == 'C')) c = 'C';
                    else c = 'Y';

                    str[i] = "" + c;
                    buffer.append(c);
                }

                result.add(str);

                String tmp = buffer.toString();
                gameDAO.updateTimegridByID(game.getId(), tmp);
                game.setTimegrid(tmp);
            }

            return result;
        }


        @Transactional
        public void createTourney(TourneyDTO tourneyDTO) {
            TourneyStatusEntity status = tourneyStatusDAO.findByCode("TOURNEY_FORMED");
            tourneyDAO.save(new TourneyEntity(tourneyDTO, status));
        }

        @Transactional
        public void timeupTourney(TourneyDTO tourneyDTO) {
            TourneyStatusEntity status = tourneyStatusDAO.findByCode("TOURNEY_TIMEUP");
            tourneyDAO.updateStatusByID(tourneyDTO.getId(), status);
        }


        @Transactional
        public void confirmTeam(Integer id) {
            String TEAM_CONFIRMED_CODE = "TEAM_CONFIRMED";
            teamDAO.updateStatusByID(id, teamStatusDAO.findByCode(TEAM_CONFIRMED_CODE));
        }

        @Transactional
        public void rejectTeam(Integer id) {
            String TEAM_REJECTED_CODE = "TEAM_REJECTED";
            teamDAO.updateStatusByID(id, teamStatusDAO.findByCode(TEAM_REJECTED_CODE));
        }

        @Transactional
        public void renameTeam(TeamDTO teamDTO) {
            teamDAO.updateNameByID(teamDTO.getId(), teamDTO.getName());
        }



        @Transactional(readOnly = true)
        public List<TeamDTO> getTeams(TourneyDTO tourney) {
            List<TeamEntity> entityList = teamDAO.findByTourney(tourney.getId());
            if (entityList == null) return null;

            List<TeamDTO> dtoList = new ArrayList<TeamDTO>();
            for (TeamEntity entity: entityList) dtoList.add(new TeamDTO(entity));
            return dtoList;
        }

        @Transactional(readOnly = true)
        public List<TeamDTO> getTeamsLike(String name) {
            List<TeamEntity> entityList = teamDAO.findByNameLike(name);
            if (entityList == null) return null;

            List<TeamDTO> dtoList = new ArrayList<TeamDTO>();
            for (TeamEntity entity: entityList)
                if (entity.getStatus().getCode().equals("TEAM_CONFIRMED")) //TODO: that's right, huh?
                    dtoList.add(new TeamDTO(entity));

            if (dtoList.isEmpty()) return null;
            return dtoList;
        }


        @Transactional(readOnly = true)
        public List<CompositionDTO> getTeamCompositions(TourneyDTO tourney) {
            List<CompositionEntity> entityList = compositionDAO.findByTourney(tourney.getId());
            if (entityList == null) return null;

            List<CompositionDTO> dtoList = new ArrayList<CompositionDTO>();
            for (CompositionEntity entity: entityList) dtoList.add(new CompositionDTO(entity));
            return dtoList;
        }

        @Transactional
        public void inviteTeams(Integer tourneyID, List<Integer> teamsID) {
//            TODO: upgrade and optimize this!
            TourneyEntity tourney = tourneyDAO.findByID(tourneyID);

            String COMPOSITION_RECRUITING_CODE = "COMPOSITION_RECRUITING";
            CompositionStatusEntity compositionStatus = compositionStatusDAO.findByCode(COMPOSITION_RECRUITING_CODE);

            for (Integer teamID: teamsID) {
                TeamEntity team = teamDAO.findByID(teamID);

                CompositionDTO compositionDTO = new CompositionDTO();
                compositionDTO.setName(team.getName());

                compositionDAO.save(new CompositionEntity(compositionDTO, team, tourney, compositionStatus));
            }
        }

    }

}
