package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamTourneyDAO;
import ru.vldf.sportsportal.dto.tourney.TeamTourneyConfirmDTO;
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
    public List<TeamTourneyConfirmDTO> getAwaitingTeamTourneyList() {
        List<TeamTourneyEntity> entityList = teamTourneyDAO.getTeamTourneyListByStatus("TEAM_AWAITING");
        List<TeamTourneyConfirmDTO> dtoList = new ArrayList<TeamTourneyConfirmDTO>();

        for (TeamTourneyEntity entity: entityList) dtoList.add(new TeamTourneyConfirmDTO(entity));
//        TODO: impl not lazy init

        return dtoList;
    }

    @Transactional
    public void confirmAwaitingTeamTourneyList(List<TeamTourneyConfirmDTO> listTeamTourneyConfirmDTO) {
//        TODO: confirmAwaitingTeamTourneyList
        listTeamTourneyConfirmDTO.size();
    }
}
