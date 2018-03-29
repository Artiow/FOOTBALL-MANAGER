package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.dto.user.UserDTO;
import ru.vldf.sportsportal.model.tourney.TeamTourneyEntity;

public class TeamTourneyDTO {
    private Integer id;
    private String name;

    private UserDTO captain;
    private TeamTourneyStatusDTO status;

    public TeamTourneyDTO(TeamTourneyEntity teamTourney) {
        id = teamTourney.getId();
        name = teamTourney.getName();

        captain = new UserDTO(teamTourney.getCaptain());
        status = new TeamTourneyStatusDTO(teamTourney.getStatus());
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCaptain(UserDTO captain) {
        this.captain = captain;
    }

    public UserDTO getCaptain() {
        return captain;
    }

    public void setStatus(TeamTourneyStatusDTO status) {
        this.status = status;
    }

    public TeamTourneyStatusDTO getStatus() {
        return status;
    }
}
