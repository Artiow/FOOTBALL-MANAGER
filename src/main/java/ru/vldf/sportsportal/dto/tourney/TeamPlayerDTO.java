package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.TeamPlayerEntity;

import java.sql.Date;

public class TeamPlayerDTO {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;

    public TeamPlayerDTO() {

    }

    public TeamPlayerDTO(TeamPlayerEntity player) {
        id = player.getId();
        name = player.getName();
        surname = player.getSurname();
        patronymic = player.getPatronymic();
        birthday = (Date) player.getBirthday().clone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
