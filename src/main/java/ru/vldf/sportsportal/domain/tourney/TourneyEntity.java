package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.dto.tourney.TourneyDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tourney", schema = "tourney", catalog = "sportsportal")
public class TourneyEntity {
    private Integer id;
    private String name;

    private TourneyStatusEntity status;

    private Collection<TeamCompositionEntity> compositions;

    public TourneyEntity() {

    }

    public TourneyEntity(TourneyDTO tourney, TourneyStatusEntity status) {
        id = tourney.getId();
        name = tourney.getName();

        this.status = status;
    }

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
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "tourney")
    public Collection<TeamCompositionEntity> getTeamCompositions() {
        return compositions;
    }

    public void setTeamCompositions(Collection<TeamCompositionEntity> compositions) {
        this.compositions = compositions;
    }

//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    public TourneyStatusEntity getStatus() {
        return status;
    }

    public void setStatus(TourneyStatusEntity status) {
        this.status = status;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourneyEntity that = (TourneyEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TourneyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
