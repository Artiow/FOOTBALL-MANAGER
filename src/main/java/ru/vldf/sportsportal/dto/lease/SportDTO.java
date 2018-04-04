package ru.vldf.sportsportal.dto.lease;

import ru.vldf.sportsportal.model.lease.SportEntity;

public class SportDTO {
    private Integer id;
    private String name;

    public SportDTO(SportEntity sport) {
        id = sport.getId();
        name = sport.getName();
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
}
