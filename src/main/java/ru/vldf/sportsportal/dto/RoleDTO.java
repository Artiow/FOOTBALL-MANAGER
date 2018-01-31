package ru.vldf.sportsportal.dto;

import ru.vldf.sportsportal.model.user.RoleEntity;

public class RoleDTO {
    private Integer id;
    private String code;
    private String name;

    public RoleDTO(RoleEntity role) {
        id = role.getId();
        code = role.getCode();
        name = role.getName();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
