package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "schedule_cell", schema = "tourney", catalog = "sportsportal")
public class ScheduleCellEntity {
    private Integer id;
    private Time time;
    private ScheduleEntity schedule;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", nullable = false)
    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleCellEntity that = (ScheduleCellEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ScheduleCellEntity{" +
                "id=" + id +
                ", time=" + time +
                '}';
    }
}
