package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.*;
import ru.vldf.sportsportal.domain.tourney.*;
import ru.vldf.sportsportal.dto.tourney.CompositionStatisticDTO;
import ru.vldf.sportsportal.dto.tourney.GameDTO;
import ru.vldf.sportsportal.dto.tourney.TourDTO;
import ru.vldf.sportsportal.dto.tourney.TourneyDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourneyService {

    private CompositionStatisticDAO compositionStatisticDAO;
    private CompositionResultDAO compositionResultDAO;
    private TourneyDAO tourneyDAO;
    private TourDAO tourDAO;
    private GameDAO gameDAO;

    @Autowired
    public void setCompositionStatisticDAO(CompositionStatisticDAO compositionStatisticDAO) {
        this.compositionStatisticDAO = compositionStatisticDAO;
    }

    @Autowired
    public void setCompositionResultDAO(CompositionResultDAO compositionResultDAO) {
        this.compositionResultDAO = compositionResultDAO;
    }

    @Autowired
    public void setTourneyDAO(TourneyDAO tourneyDAO) {
        this.tourneyDAO = tourneyDAO;
    }

    @Autowired
    public void setTourDAO(TourDAO tourDAO) {
        this.tourDAO = tourDAO;
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
    public List<TourDTO> getTourList(int tourneyID) {
        List<TourEntity> entityList = tourDAO.findByTourney(tourneyID);
        if (entityList == null) return null;

        List<TourDTO> dtoList = new ArrayList<TourDTO>();
        for (TourEntity entity: entityList) dtoList.add(new TourDTO(entity));
        return dtoList;
    }

    @Transactional(readOnly = true)
    public TourneyDTO getTourney(Integer id) {
        return new TourneyDTO(tourneyDAO.findByID(id));
    }

    @Transactional(readOnly = true)
    public TourDTO getTour(int id) {
        return new TourDTO(tourDAO.findByID(id));
    }


    @Transactional(readOnly = true)
    public List<GameDTO> getGameList(TourDTO tourDTO) {
        List<GameEntity> entityList = gameDAO.findByTour(tourDTO.getId());

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

            CompositionResultEntity r = compositionResultDAO.findByGameAndComposition(gameDTO.getId(), gameDTO.getRed().getId());
            CompositionResultEntity b = compositionResultDAO.findByGameAndComposition(gameDTO.getId(), gameDTO.getBlue().getId());

            if (r != null) game[0] = r.getGoal(); else game[0] = null;
            if (b != null) game[1] = b.getGoal(); else game[1] = null;

            result.add(game);
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<CompositionStatisticDTO> getStatistics(TourneyDTO tourney) {
        List<CompositionStatisticEntity> entityList = compositionStatisticDAO.findByTourney(tourney.getId());
        if (entityList == null) return null;

        List<CompositionStatisticDTO> dtoList = new ArrayList<CompositionStatisticDTO>();
        for (CompositionStatisticEntity entity: entityList) dtoList.add(new CompositionStatisticDTO(entity));
        return dtoList;
    }
}
