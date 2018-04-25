package ru.vldf.sportsportal.service.admin.specialized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.*;
import ru.vldf.sportsportal.domain.tourney.*;
import ru.vldf.sportsportal.dto.tourney.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourneyAdminService {

    private final String TEAM_UNCONFIRMED_CODE = "TEAM_UNCONFIRMED";
    private final String TEAM_CONFIRMED_CODE = "TEAM_CONFIRMED";
    private final String TEAM_REJECTED_CODE = "TEAM_REJECTED";

    private final String TOURNEY_FORMED_CODE = "TOURNEY_FORMED";
    private final String TOURNEY_ALREADY_CODE = "TOURNEY_ALREADY";

    private TeamDAO teamDAO;
    private TourneyDAO tourneyDAO;
    private TourneyStatusDAO tourneyStatusDAO;

    private CompositionDAO compositionDAO;
    private CompositionResultDAO compositionResultDAO;

    private GameDAO gameDAO;

    @Autowired
    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
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
    public void setCompositionDAO(CompositionDAO compositionDAO) {
        this.compositionDAO = compositionDAO;
    }

    @Autowired
    public void setCompositionResultDAO(CompositionResultDAO compositionResultDAO) {
        this.compositionResultDAO = compositionResultDAO;
    }

    @Autowired
    public void setGameDAO(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }


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
    public List<GameDTO> getNextGames(TourneyDTO tourney) {
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


    @Transactional
    public void createResultGame(GameDTO gameDTO, Integer redGoalNum, Integer blueGoalNum) {
        CompositionResultEntity redResult = new CompositionResultEntity();
        CompositionResultEntity blueResult = new CompositionResultEntity();

        redResult.setGame(gameDAO.findByID(gameDTO.getId()));
        blueResult.setGame(gameDAO.findByID(gameDTO.getId()));

        redResult.setComposition(compositionDAO.findByID(gameDTO.getRed().getId()));
        blueResult.setComposition(compositionDAO.findByID(gameDTO.getBlue().getId()));

        redResult.setGoal(redGoalNum);
        blueResult.setGoal(blueGoalNum);

        compositionResultDAO.save(redResult);
        compositionResultDAO.save(blueResult);
    }


    @Transactional
    public List<String[]> updateTimegrid(List<GameDTO> games) {
        String[] str;
        StringBuffer buffer;
        List<String[]> result = new ArrayList<String[]>(games.size());

        char[] t1, t2;
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
        TourneyStatusEntity status = tourneyStatusDAO.findByCode(TOURNEY_FORMED_CODE);
        tourneyDAO.save(new TourneyEntity(tourneyDTO, status));
    }

    @Transactional
    public void startTourney(TourneyDTO tourneyDTO) {
        tourneyDAO.updateStatusByID(tourneyDTO.getId(), TOURNEY_ALREADY_CODE);
    }


    @Transactional
    public void confirmTeam(Integer id) {
        teamDAO.updateStatusByID(id, TEAM_CONFIRMED_CODE);
    }

    @Transactional
    public void rejectTeam(Integer id) {
        teamDAO.updateStatusByID(id, TEAM_REJECTED_CODE);
    }

    @Transactional
    public void renameTeam(TeamDTO teamDTO) {
        teamDAO.updateNameByID(teamDTO.getId(), teamDTO.getName());
    }


    @Transactional(readOnly = true)
    public List<TeamDTO> getTeamList(Integer tourneyID) {
        List<TeamEntity> entityList = teamDAO.findByTourney(tourneyID);
        if (entityList == null) return null;

        List<TeamDTO> dtoList = new ArrayList<TeamDTO>();
        for (TeamEntity entity: entityList) dtoList.add(new TeamDTO(entity));
        return dtoList;
    }

    @Transactional(readOnly = true)
    public List<TeamDTO> getTeamListLike(String name) {
        List<TeamEntity> entityList = teamDAO.findByStatusAndNameLike(TEAM_CONFIRMED_CODE, name);
        if (entityList == null) return null;

        List<TeamDTO> dtoList = new ArrayList<TeamDTO>();
        for (TeamEntity entity: entityList) dtoList.add(new TeamDTO(entity));
        return dtoList;
    }


    @Transactional(readOnly = true)
    public List<CompositionDTO> getCompositionList(TourneyDTO tourney) {
        List<CompositionEntity> entityList = compositionDAO.findByTourney(tourney.getId());
        if (entityList == null) return null;

        List<CompositionDTO> dtoList = new ArrayList<CompositionDTO>();
        for (CompositionEntity entity: entityList) dtoList.add(new CompositionDTO(entity));
        return dtoList;
    }

    @Transactional
    public void inviteTeamList(Integer tourneyID, List<Integer> teamIDs) {
        TourneyEntity tourney = tourneyDAO.findByID(tourneyID);

        for (Integer teamID: teamIDs) {
            TeamEntity team = teamDAO.findByID(teamID);

            CompositionDTO compositionDTO = new CompositionDTO();
            compositionDTO.setName(team.getName());

            compositionDAO.save(new CompositionEntity(compositionDTO, team, tourney));
        }
    }
}
