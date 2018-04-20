package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TourneyDAO;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;
import ru.vldf.sportsportal.dto.tourney.TourneyDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourneyService {

    private TourneyDAO tourneyDAO;

    @Autowired
    public void setTourneyDAO(TourneyDAO tourneyDAO) {
        this.tourneyDAO = tourneyDAO;
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
}
