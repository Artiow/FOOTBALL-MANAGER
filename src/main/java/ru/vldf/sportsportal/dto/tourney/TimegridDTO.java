package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.TimegridEntity;
import ru.vldf.sportsportal.domain.tourney.TimegridTypeEntity;

import java.sql.Time;

public class TimegridDTO {
    private Integer id;
    private Time startTime;
    private Time endTime;

    private TimegridTypeDTO type;

    public TimegridDTO() {

    }

    public TimegridDTO(TimegridEntity timegrid) {
        id = timegrid.getId();
        startTime = timegrid.getStartTime();
        endTime = timegrid.getEndTime();

        TimegridTypeEntity timegridTypeEntity = timegrid.getType();
        if (timegridTypeEntity != null) type = new TimegridTypeDTO(timegridTypeEntity);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public TimegridTypeDTO getType() {
        return type;
    }

    public void setType(TimegridTypeDTO type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimegridDTO that = (TimegridDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TimegridDTO{" +
                "id=" + id +
                ", startTime=" + startTime.toString() +
                ", endTime=" + endTime.toString() +
                '}';
    }
}
