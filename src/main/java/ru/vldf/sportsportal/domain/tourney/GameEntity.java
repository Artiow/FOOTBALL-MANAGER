package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;

@Entity
@Table(name = "game", schema = "tourney", catalog = "sportsportal")
public class GameEntity {
    private Integer id;
    private String timegrid = "CCCCCCCCCC";

    private CompositionEntity red;
    private CompositionEntity blue;
    private TourEntity tour;

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
    @Column(name = "timegrid", nullable = true, length = 45)
    public String getTimegrid() {
        return timegrid;
    }

    public void setTimegrid(String timegrid) {
        this.timegrid = timegrid;
    }

//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(name = "tour_id", referencedColumnName = "id", nullable = false)
    public TourEntity getTour() {
        return tour;
    }

    public void setTour(TourEntity tour) {
        this.tour = tour;
    }

    @ManyToOne
    @JoinColumn(name = "red_id", referencedColumnName = "id", nullable = false)
    public CompositionEntity getRed() {
        return red;
    }

    public void setRed(CompositionEntity red) {
        this.red = red;
    }

    @ManyToOne
    @JoinColumn(name = "blue_id", referencedColumnName = "id", nullable = false)
    public CompositionEntity getBlue() {
        return blue;
    }

    public void setBlue(CompositionEntity blue) {
        this.blue = blue;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameEntity that = (GameEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "id=" + id +
                ", timegrid='" + timegrid + '\'' +
                '}';
    }
}
