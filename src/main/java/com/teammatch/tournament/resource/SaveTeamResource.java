package com.teammatch.tournament.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveTeamResource {

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    private int teamSize;

    @NotNull
    private Float hoursPlayed;

    @NotNull
    private Float levelAverage;

    public String getName() {
        return name;
    }

    public SaveTeamResource setName(String name) {
        this.name = name;
        return this;
    }
    public int getTeamSize() {
        return teamSize;
    }

    public SaveTeamResource setTeamSize(int teamSize) {
        this.teamSize = teamSize;
        return this;
    }

    public Float getHoursPlayed() {
        return hoursPlayed;
    }

    public SaveTeamResource setHoursPlayed(Float hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
        return this;
    }
    public Float getLevelAverage() {
        return levelAverage;
    }

    public SaveTeamResource setLevelAverage(Float levelAverage) {
        this.levelAverage = levelAverage;
        return this;
    }
}
