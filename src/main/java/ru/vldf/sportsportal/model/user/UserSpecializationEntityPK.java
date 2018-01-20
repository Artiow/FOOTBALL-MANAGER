package ru.vldf.sportsportal.model.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class UserSpecializationEntityPK implements Serializable {
    private Integer userId;
    private Integer sportId;

    @Id
    @Column(name = "User_ID", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

        UserSpecializationEntityPK that = (UserSpecializationEntityPK) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (sportId != null ? !sportId.equals(that.sportId) : that.sportId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (sportId != null ? sportId.hashCode() : 0);
        return result;
    }
}
