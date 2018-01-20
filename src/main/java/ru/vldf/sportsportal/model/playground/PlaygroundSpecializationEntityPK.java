package ru.vldf.sportsportal.model.playground;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class PlaygroundSpecializationEntityPK implements Serializable {
    private Integer playgroundId;
    private Integer sportId;

    @Id
    @Column(name = "Playground_ID", nullable = false)
    public Integer getPlaygroundId() {
        return playgroundId;
    }

    public void setPlaygroundId(Integer playgroundId) {
        this.playgroundId = playgroundId;
    }

    @Id
    @Column(name = "Sport_ID", nullable = false)
    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaygroundSpecializationEntityPK that = (PlaygroundSpecializationEntityPK) o;

        if (playgroundId != null ? !playgroundId.equals(that.playgroundId) : that.playgroundId != null) return false;
        if (sportId != null ? !sportId.equals(that.sportId) : that.sportId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playgroundId != null ? playgroundId.hashCode() : 0;
        result = 31 * result + (sportId != null ? sportId.hashCode() : 0);
        return result;
    }
}