package ru.vldf.sportsportal.dto.user;

import ru.vldf.sportsportal.domain.user.UserRoleEntity;

public class UserRoleDTO {
    private Integer id;
    private String code;
    private String description;

    public UserRoleDTO() {

    }

    public UserRoleDTO(UserRoleEntity role) {
        id = role.getId();
        code = role.getCode();
        description = role.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
