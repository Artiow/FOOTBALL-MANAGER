package ru.vldf.sportsportal.dto.lease;

import ru.vldf.sportsportal.domain.lease.PlaygroundEntity;

public class PlaygroundDTO {
    private Integer id;
    private String name;
    private String address;

    public PlaygroundDTO() {

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaygroundDTO that = (PlaygroundDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlaygroundDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}