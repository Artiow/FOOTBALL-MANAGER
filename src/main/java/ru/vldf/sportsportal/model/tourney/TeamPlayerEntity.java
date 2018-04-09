package ru.vldf.sportsportal.model.tourney;

import ru.vldf.sportsportal.dto.tourney.TeamPlayerDTO;
import ru.vldf.sportsportal.model.user.UserEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "TeamPlayer", schema = "sportsportal")
public class TeamPlayerEntity {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;

    private UserEntity user;

    private Collection<TeamCompositionEntity> compositions;

    public TeamPlayerEntity() {

    }

    public TeamPlayerEntity(UserEntity user) {
        name = user.getName();
        surname = user.getSurname();
        patronymic = user.getPatronymic();
    }

    public TeamPlayerEntity(TeamPlayerDTO playerDTO, UserEntity user) {
        id = playerDTO.getId();
        name = playerDTO.getName();
        surname = playerDTO.getSurname();
        patronymic = playerDTO.getPatronymic();

        this.user = user;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "Surname", nullable = false, length = 45)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "Patronymic", length = 45)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

//    ==================================================================================
//    === ONE-TO-ONE REFERENCES

    @OneToOne(mappedBy = "player")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

//    ==================================================================================
//    === MANY-TO-MANY REFERENCES

    @ManyToMany
    @JoinTable(
            name = "TeamMembership",
            joinColumns = @JoinColumn(name = "TeamPlayer_ID"),
            inverseJoinColumns = @JoinColumn(name = "TeamComposition_ID")
    )
    public Collection<TeamCompositionEntity> getCompositions() {
        return compositions;
    }

    public void setCompositions(Collection<TeamCompositionEntity> compositions) {
        this.compositions = compositions;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamPlayerEntity that = (TeamPlayerEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return name + ' ' + surname + ' ' + patronymic;
    }
}
