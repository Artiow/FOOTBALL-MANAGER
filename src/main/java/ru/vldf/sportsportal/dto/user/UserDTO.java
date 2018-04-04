package ru.vldf.sportsportal.dto.user;

import ru.vldf.sportsportal.model.user.UserEntity;

public class UserDTO {
    private Integer id;
    private String login;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;

    private UserRoleDTO role;

    public UserDTO() {

    }

    public UserDTO(UserEntity user) {
        id = user.getId();
        login = user.getLogin();
        email = user.getEmail();
        password = user.getPassword();
        name = user.getName();
        surname = user.getSurname();
        patronymic = user.getPatronymic();
        phone = user.getPhone();

        role = new UserRoleDTO(user.getRole());
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(UserRoleDTO role) {
        this.role = role;
    }

    public UserRoleDTO getRole() {
        return role;
    }

    @Override
    public String toString() {
        return login;
    }
}