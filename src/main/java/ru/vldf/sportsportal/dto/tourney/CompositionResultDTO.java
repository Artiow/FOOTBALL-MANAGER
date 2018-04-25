package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.CompositionResultEntity;
import ru.vldf.sportsportal.domain.tourney.GameEntity;

public class CompositionResultDTO {
    private Integer id;
    private Integer goal;

    private GameDTO game;
    private CompositionDTO composition;

    public CompositionResultDTO() {

    }

    public CompositionResultDTO(CompositionResultEntity result) {
        id = result.getId();
        goal = result.getGoal();

        GameEntity gameEntity = result.getGame();
        if (gameEntity != null) game = new GameDTO(gameEntity);
        CompositionEntity compositionEntity = result.getComposition();
        if (compositionEntity != null) composition = new CompositionDTO(compositionEntity);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public GameDTO getGame() {
        return game;
    }

    public void setGame(GameDTO game) {
        this.game = game;
    }

    public CompositionDTO getComposition() {
        return composition;
    }

    public void setComposition(CompositionDTO composition) {
        this.composition = composition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositionResultDTO that = (CompositionResultDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResultTeamDTO{" +
                "id=" + id +
                '}';
    }
}
