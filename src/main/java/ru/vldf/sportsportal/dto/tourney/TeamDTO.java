package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.domain.tourney.TeamEntity;

public class TeamDTO {
    private Integer id;
    private String name;

    private TeamStatusDTO status;
    private UserDTO captain;

    public TeamDTO() {

    }

    public TeamDTO(TeamEntity team) {
        id = team.getId();
        name = team.getName();

        if (team.getCaptain() != null) status = new TeamStatusDTO(team.getStatus());
        if (team.getCaptain() != null) captain = new UserDTO(team.getCaptain());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamDTO teamDTO = (TeamDTO) o;

        return id != null ? id.equals(teamDTO.id) : teamDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
