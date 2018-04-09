package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.model.tourney.TeamCompositionEntity;

public class TeamCompositionDTO {
    private Integer id;
    private String teamName;
    private Integer shiftBalance = 3; //default

    private TeamDTO team;
    private TourneyDTO tourney;

    public TeamCompositionDTO() {

    }

    public TeamCompositionDTO(TeamCompositionEntity teamComposition) {
        id = teamComposition.getId();
        teamName = teamComposition.getTeamName();
        shiftBalance = teamComposition.getShiftBalance();

        team = new TeamDTO(teamComposition.getTeam());
        tourney = new TourneyDTO(teamComposition.getTourney());
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
}