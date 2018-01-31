package ru.vldf.sportsportal.dto;

import ru.vldf.sportsportal.model.UserEntity;

public class UserDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;

    private RoleDTO role;

    public UserDTO(UserEntity user) {
        id = user.getId();
        name = user.getName();
        surname = user.getSurname();
        email = user.getEmail();

        role = new RoleDTO(user.getRole());
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public RoleDTO getRole() {
        return role;
    }

    @Override
    public String toString() {
        return name + ' ' + surname;
    }
}
