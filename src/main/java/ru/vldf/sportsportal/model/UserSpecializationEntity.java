package ru.vldf.sportsportal.model;

import javax.persistence.*;

@Entity
@Table(name = "UserSpecialization", schema = "sportsportal")
public class UserSpecializationEntity {
    @EmbeddedId
    private UserSpecializationEntityPK pk;

    @ManyToOne
    @MapsId("userId")
    private UserEntity user;

    @ManyToOne
    @MapsId("sportId")
    private SportEntity sport;

    public UserSpecializationEntityPK getPk() {
        return pk;
    }

    public void setPk(UserSpecializationEntityPK pk) {
        this.pk = pk;
    }

//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(name = "User_ID", referencedColumnName = "ID", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity playground) {
        this.user = playground;
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

        UserSpecializationEntity that = (UserSpecializationEntity) o;

        if (pk != null ? !pk.equals(that.pk) : that.pk != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user.getId() != null ? user.getId().hashCode() : 0;
        result = 31 * result + (sport.getId() != null ? sport.getId().hashCode() : 0);
        return result;
    }
}
