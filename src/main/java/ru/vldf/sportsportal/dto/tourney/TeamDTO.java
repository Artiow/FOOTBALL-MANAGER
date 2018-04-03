package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.dto.user.UserDTO;
import ru.vldf.sportsportal.model.tourney.TeamEntity;

public class TeamDTO {
    private Integer id;
    private String name;

    private UserDTO captain;
    private TeamStatusDTO status;

    public TeamDTO() {

    }

    public TeamDTO(TeamEntity teamTourney) {
        id = teamTourney.getId();
        name = teamTourney.getName();

        captain = new UserDTO(teamTourney.getCaptain());
        status = new TeamStatusDTO(teamTourney.getStatus());
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

    public void setStatus(TeamStatusDTO status) {
        this.status = status;
    }

    public TeamStatusDTO getStatus() {
        return status;
    }
}
