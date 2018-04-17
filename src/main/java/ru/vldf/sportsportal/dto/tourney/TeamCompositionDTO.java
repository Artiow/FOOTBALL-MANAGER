package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.TeamCompositionEntity;

public class TeamCompositionDTO {
    private Integer id;
    private String teamName;
    private Integer shiftBalance = 3; //default

    private TeamDTO team;
    private TourneyDTO tourney;
    private TeamCompositionStatusDTO status;

    public TeamCompositionDTO() {

    }

    public TeamCompositionDTO(TeamCompositionEntity teamComposition) {
        id = teamComposition.getId();
        teamName = teamComposition.getTeamName();
        shiftBalance = teamComposition.getShiftBalance();

        if (teamComposition.getTeam() != null) team = new TeamDTO(teamComposition.getTeam());
        if (teamComposition.getTourney() != null) tourney = new TourneyDTO(teamComposition.getTourney());
        if (teamComposition.getStatus() != null) status = new TeamCompositionStatusDTO(teamComposition.getStatus());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getShiftBalance() {
        return shiftBalance;
    }

    public void setShiftBalance(Integer shiftBalance) {
        this.shiftBalance = shiftBalance;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public TourneyDTO getTourney() {
        return tourney;
    }

    public void setTourney(TourneyDTO tourney) {
        this.tourney = tourney;
    }

    public TeamCompositionStatusDTO getStatus() {
        return status;
    }

    public void setStatus(TeamCompositionStatusDTO status) {
        this.status = status;
    }
}
