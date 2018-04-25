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

    @Service //TOURNEY
    public class AdminTourneyService {

        private TeamDAO teamDAO;
        private TeamStatusDAO teamStatusDAO;

        private TourneyDAO tourneyDAO;
        private TourneyStatusDAO tourneyStatusDAO;

        private GameDAO gameDAO;

        private CompositionDAO compositionDAO;
        private CompositionResultDAO compositionResultDAO;

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
        public void setCompositionDAO(CompositionDAO compositionDAO) {
            this.compositionDAO = compositionDAO;
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

        @Autowired
        public void setPlayerDAO(PlayerDAO playerDAO) {
            this.playerDAO = playerDAO;
        }

        @Autowired
        public void setCompositionResultDAO(CompositionResultDAO compositionResultDAO) {
            this.compositionResultDAO = compositionResultDAO;
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

        @Transactional(readOnly = true) //TODO: duplicate code
        public List<PlayerDTO> getPlayerList(CompositionDTO composition) {
            List<PlayerEntity> entityList = playerDAO.findByTeamComposition(composition.getId());
            if (entityList == null) return null;

            List<PlayerDTO> dtoList = new ArrayList<PlayerDTO>();
            for (PlayerEntity entity: entityList) dtoList.add(new PlayerDTO(entity));
            return dtoList;
        }

        @Transactional
        public void createResultGame(GameDTO gameDTO, Integer r, Integer b) {
            CompositionResultEntity redResult = new CompositionResultEntity();
            CompositionResultEntity blueResult = new CompositionResultEntity();

            redResult.setGame(gameDAO.findByID(gameDTO.getId()));
            blueResult.setGame(gameDAO.findByID(gameDTO.getId()));

            redResult.setComposition(compositionDAO.findByID(gameDTO.getRed().getId()));
            blueResult.setComposition(compositionDAO.findByID(gameDTO.getBlue().getId()));

            redResult.setGoal(r);
            blueResult.setGoal(b);

            compositionResultDAO.save(redResult);
            compositionResultDAO.save(blueResult);
        }


        @Transactional(readOnly = true)
        public CompositionResultDTO getResultTeam(CompositionDTO composition) {
            return null;
        }


        @Transactional(readOnly = true)
        public List<TourneyDTO> getTourneyList() {
            List<TourneyEntity> entityList = tourneyDAO.findAllOrderByID();
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
            List<GameEntity> entityList = gameDAO.findNextByTourney(tourney.getId());
            if (entityList == null) return null;

            List<GameDTO> dtoList = new ArrayList<GameDTO>();
            for (GameEntity entity: entityList) dtoList.add(new GameDTO(entity));
            return dtoList;
        }

        @Transactional(readOnly = true)
        public GameDTO getGame(Integer id) {
            return new GameDTO(gameDAO.findByID(id));
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

            for (Integer teamID: teamsID) {
                TeamEntity team = teamDAO.findByID(teamID);

                CompositionDTO compositionDTO = new CompositionDTO();
                compositionDTO.setName(team.getName());

                compositionDAO.save(new CompositionEntity(compositionDTO, team, tourney));
            }
        }
    }

}
