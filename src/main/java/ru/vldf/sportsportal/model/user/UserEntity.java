package ru.vldf.sportsportal.model.user;

import ru.vldf.sportsportal.dto.user.UserDTO;
import ru.vldf.sportsportal.model.tourney.TeamCompositionEntity;
import ru.vldf.sportsportal.model.tourney.TeamEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "User", schema = "sportsportal")
public class UserEntity {
    private Integer id;
    private String login;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;

    private UserRoleEntity role;

    private Collection<TeamEntity> teams;
    private Collection<TeamCompositionEntity> compositions;

    public UserEntity() {

    }

    public UserEntity(UserDTO userDTO, UserRoleEntity role) {
        id = userDTO.getId();
        login = userDTO.getLogin();
        email = userDTO.getEmail();
        password = userDTO.getPassword();
        name = userDTO.getName();
        surname = userDTO.getSurname();
        patronymic = userDTO.getPatronymic();
        phone = userDTO.getPhone();

        this.role = role;
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
    @Column(name = "Login", nullable = false, length = 45)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "Email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Password", nullable = false, length = 125)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setPatronymic(String patronomic) {
        this.patronymic = patronomic;
    }

    @Basic
    @Column(name = "Phone", length = 10)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

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
            name = "UserRole_ID",
            referencedColumnName = "ID",
            nullable = false
    )
    public UserRoleEntity getRole() {
        return role;
    }

    public void setRole(UserRoleEntity role) {
        this.role = role;
    }

//    ==================================================================================
//    === MANY-TO-MANY REFERENCES

    @ManyToMany
    @JoinTable(
            name = "TeamMembershipForUser",
            joinColumns = @JoinColumn(name = "TeamUser_ID"),
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

        UserEntity that = (UserEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return login;
    }
}