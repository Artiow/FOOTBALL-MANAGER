package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;

@Entity
@Table(name = "tmp", schema = "tourney", catalog = "sportsportal")
public class TmpEntity {
    private Integer id;
    private String timegrid;

    private CompositionEntity red;
    private CompositionEntity blue;

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
}
