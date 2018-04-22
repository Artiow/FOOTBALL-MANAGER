package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.dto.tourney.TourDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tour", schema = "tourney", catalog = "sportsportal")
public class TourEntity {
    private Integer id;
    private Integer num;

    private TourneyEntity tourney;

    private Collection<GameEntity> games;

    public TourEntity() {

    }

    public TourEntity(TourDTO tour, TourneyEntity tourney) {
        id = tour.getId();
        num = tour.getNum();

        this.tourney = tourney;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "num", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "tour")
    public Collection<GameEntity> getGames() {
        return games;
    }

    public void setGames(Collection<GameEntity> games) {
        this.games = games;
    }


//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(name = "tourney_id", referencedColumnName = "id", nullable = false)
    public TourneyEntity getTourney() {
        return tourney;
    }

    public void setTourney(TourneyEntity tourney) {
        this.tourney = tourney;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourEntity that = (TourEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TourEntity{" +
                "id=" + id +
                '}';
    }
}
