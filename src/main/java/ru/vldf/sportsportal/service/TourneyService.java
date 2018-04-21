package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.GameDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.ResultTeamDAO;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TourneyDAO;
import ru.vldf.sportsportal.domain.tourney.GameEntity;
import ru.vldf.sportsportal.domain.tourney.ResultTeamEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;
import ru.vldf.sportsportal.dto.tourney.GameDTO;
import ru.vldf.sportsportal.dto.tourney.TourneyDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourneyService {

    private ResultTeamDAO resultTeamDAO;
    private TourneyDAO tourneyDAO;
    private GameDAO gameDAO;

    @Autowired
    public void setResultTeamDAO(ResultTeamDAO resultTeamDAO) {
        this.resultTeamDAO = resultTeamDAO;
    }

    @Autowired
    public void setTourneyDAO(TourneyDAO tourneyDAO) {
        this.tourneyDAO = tourneyDAO;
    }

    @Autowired
    public void setGameDAO(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
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
    public List<GameDTO> getGameList(TourneyDTO tourneyDTO) {
        List<GameEntity> entityList = gameDAO.findByTourney(tourneyDTO.getId());

        if (entityList == null) return null;
        List<GameDTO> dtoList = new ArrayList<GameDTO>();
        for (GameEntity entity: entityList) dtoList.add(new GameDTO(entity));
        return dtoList;
    }

    @Transactional(readOnly = true)
    public List<Integer[]> getResults(List<GameDTO> gameDTOList) {
        ArrayList<Integer[]> result = new ArrayList<Integer[]>(gameDTOList.size());
        for(GameDTO gameDTO: gameDTOList) {
            Integer[] game = new Integer[2];

            ResultTeamEntity r = resultTeamDAO.findByGameAndComposition(gameDTO.getId(), gameDTO.getRed().getId());
            ResultTeamEntity b = resultTeamDAO.findByGameAndComposition(gameDTO.getId(), gameDTO.getBlue().getId());

            if (r != null) game[0] = r.getGoal(); else game[0] = null;
            if (b != null) game[1] = b.getGoal(); else game[1] = null;

            result.add(game);
        }

        return result;
    }

}
