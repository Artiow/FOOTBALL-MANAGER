package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.TeamCompositionStatusEntity;

public class TeamCompositionStatusDTO {
    private Integer id;
    private String code;
    private String description;

    public TeamCompositionStatusDTO() {

    }

    public TeamCompositionStatusDTO(TeamCompositionStatusEntity status) {
        id = status.getId();
        code = status.getCode();
        description = status.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
