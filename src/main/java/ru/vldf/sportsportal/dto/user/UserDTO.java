package ru.vldf.sportsportal.dto.user;

import ru.vldf.sportsportal.dto.tourney.TeamPlayerDTO;
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
    private TeamPlayerDTO player;

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

        if (user.getRole() != null) role = new UserRoleDTO(user.getRole());
        if (user.getPlayer() != null) player = new TeamPlayerDTO(user.getPlayer());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    public TeamPlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(TeamPlayerDTO player) {
        this.player = player;
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
        return login;
    }
}