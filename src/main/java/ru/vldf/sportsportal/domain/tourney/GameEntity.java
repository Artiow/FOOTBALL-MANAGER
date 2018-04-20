package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;

@Entity
@Table(name = "game", schema = "tourney", catalog = "sportsportal")
public class GameEntity {
    private Integer id;
    private String timegrid;

    private CompositionEntity red;
    private CompositionEntity blue;
    private TourneyEntity tourney;

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

    @OneToOne
    @JoinColumn(
            name = "red_id",
            referencedColumnName = "id"
    )
    public CompositionEntity getRed() {
        return red;
    }

    public void setRed(CompositionEntity red) {
        this.red = red;
    }

    @OneToOne
    @JoinColumn(
            name = "blue_id",
            referencedColumnName = "id"
    )
    public CompositionEntity getBlue() {
        return blue;
    }

    public void setBlue(CompositionEntity blue) {
        this.blue = blue;
    }

    @OneToOne
    @JoinColumn(
            name = "tourney_id",
            referencedColumnName = "id"
    )
    public TourneyEntity getTourney() {
        return tourney;
    }

    public void setTourney(TourneyEntity tourney) {
        this.tourney = tourney;
    }

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
