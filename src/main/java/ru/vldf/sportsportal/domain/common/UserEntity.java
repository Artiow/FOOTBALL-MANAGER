package ru.vldf.sportsportal.domain.common;

import ru.vldf.sportsportal.domain.tourney.PlayerOwnershipEntity;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.domain.tourney.TeamEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "common", catalog = "sportsportal")
public class UserEntity {
    private Integer id;
    private String login;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;
    private String phone;

    private UserRoleEntity role;

    private PlayerOwnershipEntity playerOwnership;

    private Collection<TeamEntity> teams;

    public UserEntity() {

    }

    public UserEntity(UserDTO user, UserRoleEntity role) {
        id = user.getId();
        login = user.getLogin();
        email = user.getEmail();
        password = user.getPassword();
        name = user.getName();
        surname = user.getSurname();
        patronymic = user.getPatronymic();
        if (user.getBirthday() != null) birthday = ((Date) user.getBirthday().clone()); else birthday = null;
        phone = user.getPhone();

        this.role = role;
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
    @Column(name = "login", nullable = false, length = 45)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 125)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "patronymic", nullable = true, length = 45)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronomic) {
        this.patronymic = patronomic;
    }

    @Basic
    @Column(name = "birthday", nullable = false)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 16)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    ==================================================================================
//    === ONE-TO-ONE REFERENCES

    @OneToOne(mappedBy = "user")
    public PlayerOwnershipEntity getPlayerOwnership() {
        return playerOwnership;
    }

    public void setPlayerOwnership(PlayerOwnershipEntity playerOwnership) {
        this.playerOwnership = playerOwnership;
    }

    @OneToMany(mappedBy = "captain")
    public Collection<TeamEntity> getTeams() {
        return teams;
    }

    public void setTeams(Collection<TeamEntity> teams) {
        this.teams = teams;
    }


//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(
            name = "role_id",
            referencedColumnName = "id",
            nullable = false
    )
    public UserRoleEntity getRole() {
        return role;
    }

    public void setRole(UserRoleEntity role) {
        this.role = role;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}