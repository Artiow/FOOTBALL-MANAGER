package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.model.tourney.TeamTourneyStatusEntity;

public class TeamTourneyStatusDTO {
    private Integer id;
    private String code;
    private String description;

    public TeamTourneyStatusDTO(TeamTourneyStatusEntity teamTourneyStatus) {
        id = teamTourneyStatus.getId();
        code = teamTourneyStatus.getCode();
        description = teamTourneyStatus.getDescription();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
