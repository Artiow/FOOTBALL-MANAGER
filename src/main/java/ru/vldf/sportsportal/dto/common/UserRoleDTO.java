package ru.vldf.sportsportal.dto.common;

import ru.vldf.sportsportal.domain.common.UserRoleEntity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleDTO that = (UserRoleDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
