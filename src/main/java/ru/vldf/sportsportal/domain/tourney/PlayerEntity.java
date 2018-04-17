package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.domain.common.UserEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "player", schema = "tourney", catalog = "sportsportal")
public class PlayerEntity {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;

    private PlayerOwnershipEntity playerOwnership;

    private Collection<CompositionMembershipEntity> memberships;

    public PlayerEntity() {

    }

    public PlayerEntity(UserEntity user) {
        name = user.getName();
        surname = user.getSurname();
        patronymic = user.getPatronymic();
        birthday = (Date) user.getBirthday().clone();
    }

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
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 45)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "patronymic", nullable = false, length = 45)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "birthday", nullable = false)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

//    ==================================================================================
//    === ONE-TO-ONE REFERENCES

    @OneToOne(mappedBy = "player")
    public PlayerOwnershipEntity getPlayerOwnership() {
        return playerOwnership;
    }

    public void setPlayerOwnership(PlayerOwnershipEntity playerOwnership) {
        this.playerOwnership = playerOwnership;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "player")
    public Collection<CompositionMembershipEntity> getMemberships() {
        return memberships;
    }

    public void setMemberships(Collection<CompositionMembershipEntity> memberships) {
        this.memberships = memberships;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerEntity that = (PlayerEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
