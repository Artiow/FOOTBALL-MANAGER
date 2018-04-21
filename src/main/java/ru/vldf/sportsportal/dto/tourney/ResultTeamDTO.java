package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.ResultTeamEntity;

public class ResultTeamDTO {
    private Integer id;
    private Integer goal;

    private GameDTO game;
    private CompositionDTO composition;

    public ResultTeamDTO() {

    }

    public ResultTeamDTO(ResultTeamEntity result) {
        id = result.getId();
        goal = result.getGoal();

        if (result.getGame() != null) game = new GameDTO(result.getGame());
        if (result.getComposition() != null) composition = new CompositionDTO(result.getComposition());
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

        ResultTeamDTO that = (ResultTeamDTO) o;

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
