package ru.vldf.sportsportal.model.user;

import ru.vldf.sportsportal.dto.user.UserRoleDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "UserRole", schema = "public", catalog = "sportsportal")
public class UserRoleEntity {
    private Integer id;
    private String code;
    private String description;

    private Collection<UserEntity> users;

    public UserRoleEntity() {

    }

    public UserRoleEntity(UserRoleDTO role) {
        id = role.getId();
        code = role.getCode();
        description = role.getDescription();
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
    @Column(name = "Code", nullable = false, length = 45)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "Description", nullable = false, length = 90)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "role")
    public Collection<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserEntity> users) {
        this.users = users;
    }

//    ==================================================================================
//    === OBJECTS METHODS


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleEntity that = (UserRoleEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return code;
    }
}
