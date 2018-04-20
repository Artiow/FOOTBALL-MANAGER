package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "schedule", schema = "tourney", catalog = "sportsportal")
public class ScheduleEntity {
    private Integer id;
    private Date dateDay;
    private Integer weekDay;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_day", nullable = true)
    public Date getDateDay() {
        return dateDay;
    }

    public void setDateDay(Date dateDay) {
        this.dateDay = dateDay;
    }

    @Basic
    @Column(name = "week_day", nullable = true)
    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleEntity that = (ScheduleEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "id=" + id +
                ", dateDay=" + dateDay +
                ", weekDay=" + weekDay +
                '}';
    }
}
