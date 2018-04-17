package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.PlayerEntity;

import java.sql.Date;

public class PlayerDTO {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;

    public PlayerDTO() {

    }

    public PlayerDTO(PlayerEntity player) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerDTO playerDTO = (PlayerDTO) o;

        return id != null ? id.equals(playerDTO.id) : playerDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
