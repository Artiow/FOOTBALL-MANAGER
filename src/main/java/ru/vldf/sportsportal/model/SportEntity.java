package ru.vldf.sportsportal.model;

import ru.vldf.sportsportal.model.playground.PlaygroundSpecializationEntity;
import ru.vldf.sportsportal.model.user.UserSpecializationEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Sport", schema = "sportsportal")
public class SportEntity {
    private Integer id;
    private String name;

    private Collection<UserSpecializationEntity> userSpecializations;
    private Collection<PlaygroundSpecializationEntity> playgroundSpecializations;

    @Id
    @Column(name = "ID", nullable = false)
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

    public void setName(String name) {
        this.name = name;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "sport")
    public Collection<UserSpecializationEntity> getUserSpecializations() {
        return userSpecializations;
    }

    public void setUserSpecializations(Collection<UserSpecializationEntity> userSpecializations) {
        this.userSpecializations = userSpecializations;
    }

    @OneToMany(mappedBy = "sport")
    public Collection<PlaygroundSpecializationEntity> getPlaygroundSpecializations() {
        return playgroundSpecializations;
    }

    public void setPlaygroundSpecializations(Collection<PlaygroundSpecializationEntity> playgroundSpecializations) {
        this.playgroundSpecializations = playgroundSpecializations;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SportEntity that = (SportEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}