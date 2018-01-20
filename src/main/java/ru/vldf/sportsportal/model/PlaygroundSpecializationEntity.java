package ru.vldf.sportsportal.model;

import javax.persistence.*;

@Entity
@Table(name = "PlaygroundSpecialization", schema = "sportsportal")
public class PlaygroundSpecializationEntity {
    @EmbeddedId
    private PlaygroundSpecializationEntityPK pk;

    @ManyToOne
    @MapsId("playgroundId")
    private PlaygroundEntity playground;

    @ManyToOne
    @MapsId("sportId")
    private SportEntity sport;

    public PlaygroundSpecializationEntityPK getPk() {
        return pk;
    }

    public void setPk(PlaygroundSpecializationEntityPK pk) {
        this.pk = pk;
    }

//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(name = "Playground_ID", referencedColumnName = "ID", nullable = false)
    public PlaygroundEntity getPlayground() {
        return playground;
    }

    public void setPlayground(PlaygroundEntity playground) {
        this.playground = playground;
    }

    @ManyToOne
    @JoinColumn(name = "Sport_ID", referencedColumnName = "ID", nullable = false)
    public SportEntity getSport() {
        return sport;
    }

    public void setSport(SportEntity sport) {
        this.sport = sport;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaygroundSpecializationEntity that = (PlaygroundSpecializationEntity) o;

        if (pk != null ? !pk.equals(that.pk) : that.pk != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playground.getId() != null ? playground.getId().hashCode() : 0;
        result = 31 * result + (sport.getId() != null ? sport.getId().hashCode() : 0);
        return result;
    }
}