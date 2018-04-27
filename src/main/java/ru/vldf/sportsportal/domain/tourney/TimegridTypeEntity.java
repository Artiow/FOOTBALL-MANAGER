package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "timegrid_type", schema = "tourney", catalog = "sportsportal")
public class TimegridTypeEntity {
    private Integer id;
    private String description;

    private Collection<TimegridEntity> timegrid;
    private Collection<TourneyEntity> tourneys;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 90)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "type")
    public Collection<TimegridEntity> getTimegrid() {
        return timegrid;
    }

    public void setTimegrid(Collection<TimegridEntity> timegrid) {
        this.timegrid = timegrid;
    }

    @OneToMany(mappedBy = "timegrid")
    public Collection<TourneyEntity> getTourneys() {
        return tourneys;
    }

    public void setTourneys(Collection<TourneyEntity> tourneys) {
        this.tourneys = tourneys;
    }

    //    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimegridTypeEntity that = (TimegridTypeEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TimegridTypeEntity{" +
                "description='" + description + '\'' +
                '}';
    }
}
