package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.TeamStatusEntity;

public class TeamStatusDTO {
    private Integer id;
    private String code;
    private String description;

    public TeamStatusDTO() {

    }

    public TeamStatusDTO(TeamStatusEntity teamTourneyStatus) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamStatusDTO that = (TeamStatusDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TeamStatusDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
