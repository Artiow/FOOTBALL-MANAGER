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
        List<TourneyEntity> entityList = tourneyDAO.findAllOrderByID();
        if (entityList == null) return null;

        List<TourneyDTO> dtoList = new ArrayList<TourneyDTO>();
        for (TourneyEntity entity: entityList) dtoList.add(new TourneyDTO(entity));
        return dtoList;
    }

    @Transactional(readOnly = true)
    public List<TourDTO> getTourList(Integer tourneyID) {
        List<TourEntity> entityList = tourDAO.findByTourney(tourneyID);
        if (entityList == null) return null;

        List<TourDTO> dtoList = new ArrayList<TourDTO>();
        for (TourEntity entity: entityList) dtoList.add(new TourDTO(entity));
        return dtoList;
    }

    @Transactional(readOnly = true)
    public TourneyDTO getFirstTourney() {
        List<TourneyEntity> entityList = tourneyDAO.findAllOrderByID();
        if (entityList == null) return null;

        return new TourneyDTO(entityList.get(0));
    }

    @Transactional(readOnly = true)
    public Integer getFirstTourneyID() {
        List<TourneyEntity> entityList = tourneyDAO.findAllOrderByID();
        if (entityList == null) return null;

        return entityList.get(0).getId();
    }

    @Transactional(readOnly = true)
    public TourDTO getFirstTour(Integer tourneyID) {
        List<TourEntity> entityList = tourDAO.findByTourney(tourneyID);
        if (entityList == null) return null;

        return new TourDTO(entityList.get(0));
    }

    @Transactional(readOnly = true)
    public Integer getFirstTourID(Integer tourneyID) {
        List<TourEntity> entityList = tourDAO.findByTourney(tourneyID);
        if (entityList == null) return null;

        return entityList.get(0).getId();
    }

    @Transactional(readOnly = true)
    public TourneyDTO getTourney(Integer id) {
        return new TourneyDTO(tourneyDAO.findByID(id));
    }

    @Transactional(readOnly = true)
    public TourDTO getTour(Integer id) {
        return new TourDTO(tourDAO.findByID(id));
    }


    @Transactional(readOnly = true)
    public List<GameDTO> getGameList(Integer tourID) {
        List<GameEntity> entityList = gameDAO.findByTour(tourID);

        if (entityList == null) return null;
        List<GameDTO> dtoList = new ArrayList<GameDTO>();
        for (GameEntity entity: entityList) dtoList.add(new GameDTO(entity));
        return dtoList;
    }

    @Transactional(readOnly = true)
    public List<Integer[]> getResultList(List<GameDTO> games) {
        ArrayList<Integer[]> results = new ArrayList<Integer[]>(games.size());

        for(GameDTO game: games) {
            Integer[] result = new Integer[2];

            Integer gameID = game.getId();
            CompositionResultEntity r = compositionResultDAO.findByGameAndComposition(gameID, game.getRed().getId());
            CompositionResultEntity b = compositionResultDAO.findByGameAndComposition(gameID, game.getBlue().getId());

            if (r != null) result[0] = r.getGoal(); else result[0] = null;
            if (b != null) result[1] = b.getGoal(); else result[1] = null;

            results.add(result);
        }

        return results;
    }

    @Transactional(readOnly = true)
    public List<CompositionStatisticDTO> getStatisticList(TourneyDTO tourney) {
        List<CompositionStatisticEntity> entityList = compositionStatisticDAO.findByTourney(tourney.getId());
        if (entityList == null) return null;

        List<CompositionStatisticDTO> dtoList = new ArrayList<CompositionStatisticDTO>();
        for (CompositionStatisticEntity entity: entityList) dtoList.add(new CompositionStatisticDTO(entity, true));
        return dtoList;
    }
}
