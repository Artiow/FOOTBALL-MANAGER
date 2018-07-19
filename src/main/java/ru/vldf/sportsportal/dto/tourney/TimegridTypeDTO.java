package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.TimegridTypeEntity;

public class TimegridTypeDTO {
    private Integer id;
    private String description;

    public TimegridTypeDTO() {

    }

    public TimegridTypeDTO(TimegridTypeEntity type) {
        id = type.getId();
        description = type.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimegridTypeDTO that = (TimegridTypeDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TimegridTypeDTO{" +
                "id=" + id +
                '}';
    }
}
