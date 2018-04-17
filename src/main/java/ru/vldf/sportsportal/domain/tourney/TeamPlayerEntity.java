package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.dto.tourney.TeamPlayerDTO;
import ru.vldf.sportsportal.domain.common.UserEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "TeamPlayer", schema = "public", catalog = "sportsportal")
public class TeamPlayerEntity {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;

    private UserEntity user;

    private Collection<TeamCompositionMembershipEntity> memberships;

    public TeamPlayerEntity() {

    }

    public TeamPlayerEntity(UserEntity user) {
        name = user.getName();
        surname = user.getSurname();
        patronymic = user.getPatronymic();
        birthday = (Date) user.getBirthday().clone();

        this.user = user;
    }

    public TeamPlayerEntity(TeamPlayerDTO playerDTO, UserEntity user) {
        id = playerDTO.getId();
        name = playerDTO.getName();
        surname = playerDTO.getSurname();
        patronymic = playerDTO.getPatronymic();
        birthday = (Date) playerDTO.getBirthday().clone();

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

    @Basic
    @Column(name = "Birthday", nullable = false)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToOne(mappedBy = "player")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "player")
    public Collection<TeamCompositionMembershipEntity> getMemberships() {
        return memberships;
    }

    public void setMemberships(Collection<TeamCompositionMembershipEntity> memberships) {
        this.memberships = memberships;
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
