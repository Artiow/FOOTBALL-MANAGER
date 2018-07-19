package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.common.UserEntity;
import ru.vldf.sportsportal.domain.tourney.PlayerEntity;
import ru.vldf.sportsportal.domain.tourney.PlayerOwnershipEntity;
import ru.vldf.sportsportal.dto.common.UserDTO;

public class PlayerOwnershipDTO {
    private Integer id;

    private UserDTO user;
    private PlayerDTO player;

    public PlayerOwnershipDTO() {

    }

    public PlayerOwnershipDTO(PlayerOwnershipEntity ownership) {
        id = ownership.getId();

        UserEntity userEntity = ownership.getUser();
        if (userEntity != null) user = new UserDTO(userEntity);
        PlayerEntity playerEntity = ownership.getPlayer();
        if (playerEntity != null) player = new PlayerDTO(playerEntity);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerOwnershipDTO that = (PlayerOwnershipDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlayerOwnershipDTO{" +
                "id=" + id +
                '}';
    }
}
