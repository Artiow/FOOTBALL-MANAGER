package ru.vldf.sportsportal.dto.common;

import ru.vldf.sportsportal.domain.common.UserEntity;

import java.sql.Date;

public class UserDTO {
    private Integer id;
    private String login;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;
    private String phone;

    private UserRoleDTO role;

    public UserDTO() {

    }

    public UserDTO(UserEntity user) {
        id = user.getId();
        login = user.getLogin();
        email = user.getEMail();
        password = user.getPassword();
        name = user.getName();
        surname = user.getSurname();
        patronymic = user.getPatronymic();
        if (user.getBirthday() != null) birthday = ((Date) user.getBirthday().clone()); else birthday = null;
        phone = user.getPhone();

        if (user.getRole() != null) role = new UserRoleDTO(user.getRole());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEMail() {
        return email;
    }

    public void setEMail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public UserRoleDTO getRole() {
        return role;
    }

    public void setRole(UserRoleDTO role) {
        this.role = role;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        return id != null ? id.equals(userDTO.id) : userDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}