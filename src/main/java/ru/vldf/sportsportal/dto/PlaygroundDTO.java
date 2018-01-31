package ru.vldf.sportsportal.dto;

import ru.vldf.sportsportal.model.playground.PlaygroundEntity;

public class PlaygroundDTO {
    private Integer id;
    private String name;
    private String address;

    public PlaygroundDTO(PlaygroundEntity playground) {
        id = playground.getId();
        name = playground.getName();
        address = playground.getAddress();
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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}