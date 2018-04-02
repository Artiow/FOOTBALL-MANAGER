package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.model.tourney.TeamTourneyEntity;

public class TeamTourneyConfirmDTO extends TeamTourneyDTO {
    private boolean confirmed;

    public TeamTourneyConfirmDTO() {
        super();
        confirmed = false;
    }

    public TeamTourneyConfirmDTO(TeamTourneyEntity teamTourney){
        super(teamTourney);
        confirmed = false;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
