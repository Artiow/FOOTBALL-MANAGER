package ru.vldf.sportsportal.model.tourney;

import ru.vldf.sportsportal.dto.tourney.TourneyDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Tourney", schema = "sportsportal")
public class TourneyEntity {
    private Integer id;
    private String name;

    private TourneyStatusEntity status;

    private Collection<TeamCompositionEntity> compositions;

    public TourneyEntity() {

    }

    public TourneyEntity(TourneyDTO tourney, TourneyStatusEntity status) {
        id = tourney.getId();
        name= tourney.getName();

        this.status = status;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 45)
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
    @JoinColumn(name = "Status_ID", referencedColumnName = "ID", nullable = false)
    public TourneyStatusEntity getTourneyStatus() {
        return status;
    }

    public void setTourneyStatus(TourneyStatusEntity status) {
        this.status = status;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourneyEntity that = (TourneyEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
