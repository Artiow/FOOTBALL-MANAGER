package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.TmpEntity;

public class TmpDTO {
    private CompositionDTO red;
    private CompositionDTO blue;

    public TmpDTO() {

    }

    public TmpDTO(TmpEntity tmp) {
        if (tmp.getRed() != null) red = new CompositionDTO(tmp.getRed());
        if (tmp.getBlue() != null) blue = new CompositionDTO(tmp.getBlue());
    }

    public CompositionDTO getRed() {
        return red;
    }

    public void setRed(CompositionDTO red) {
        this.red = red;
    }

    public CompositionDTO getBlue() {
        return blue;
    }

    public void setBlue(CompositionDTO blue) {
        this.blue = blue;
    }
}
