package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyDAO;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyDTO;
import ru.vldf.sportsportal.model.tourney.TeamTourneyEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

//    ==================================================================================
//    === TOURNEY

    private TeamTourneyDAO teamTourneyDAO;

    @Autowired
    public void setTeamTourneyDAO(TeamTourneyDAO teamTourneyDAO) {
        this.teamTourneyDAO = teamTourneyDAO;
    }

    @Transactional(readOnly = true)
    public List<TeamTourneyDTO> getAwaitingTeamTourneyList() {
        List<TeamTourneyEntity> entityList = teamTourneyDAO.getTeamTourneyListByStatus("TEAM_AWAITING");
        List<TeamTourneyDTO> dtoList = new ArrayList<TeamTourneyDTO>();

        for (TeamTourneyEntity entity: entityList) dtoList.add(new TeamTourneyDTO(entity));
//        TODO: impl not lazy init

        return dtoList;
    }
}
