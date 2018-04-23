package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.dto.tourney.TourneyDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tourney", schema = "tourney", catalog = "sportsportal")
public class TourneyEntity {
    private Integer id;
    private String name;
    private Integer currentTour = 0;
    private Integer nextTour = 1;

    private TourneyStatusEntity status;

    private Collection<TourEntity> tours;
    private Collection<CompositionEntity> compositions;

    public TourneyEntity() {

    }

    public TourneyEntity(TourneyDTO tourney, TourneyStatusEntity status) {
        id = tourney.getId();
        name = tourney.getName();
        currentTour= tourney.getCurrentTour();

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

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "current_tour", nullable = false)
    public Integer getCurrentTour() {
        return currentTour;
    }

    public void setCurrentTour(Integer currentTour) {
        this.currentTour = currentTour;
    }

    @Basic
    @Column(name = "next_tour", nullable = false)
    public Integer getNextTour() {
        return nextTour;
    }

    public void setNextTour(Integer nextTour) {
        this.nextTour = nextTour;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "tourney")
    public Collection<TourEntity> getTours() {
        return tours;
    }

    public void setTours(Collection<TourEntity> tours) {
        this.tours = tours;
    }

    @OneToMany(mappedBy = "tourney")
    public Collection<CompositionEntity> getCompositions() {
        return compositions;
    }

    public void setCompositions(Collection<CompositionEntity> compositions) {
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
