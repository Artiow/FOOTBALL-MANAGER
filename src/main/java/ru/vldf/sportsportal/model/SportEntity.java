package ru.vldf.sportsportal.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Sport", schema = "sportsportal")
public class SportEntity {
    private Integer id;
    private String name;

    private Collection<UserEntity> users;
    private Collection<PlaygroundEntity> playgrounds;

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
//    === MANY-TO-MANY REFERENCES

    @ManyToMany
    @JoinTable(
            name = "UserSpecialization",
            joinColumns = @JoinColumn(name = "Sport_ID"),
            inverseJoinColumns = @JoinColumn(name = "User_ID")
    )
    public Collection<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserEntity> users) {
        this.users = users;
    }

    @ManyToMany
    @JoinTable(
            name = "PlaygroundSpecialization",
            joinColumns = @JoinColumn(name = "Sport_ID"),
            inverseJoinColumns = @JoinColumn(name = "Playground_ID")
    )
    public Collection<PlaygroundEntity> getPlaygrounds() {
        return playgrounds;
    }

    public void setPlaygrounds(Collection<PlaygroundEntity> playgrounds) {
        this.playgrounds = playgrounds;
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

    @Override
    public String toString() {
        return name;
    }
}