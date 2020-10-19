package com.teammatch.tournament.resource;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SavePlayerResource {
    @NotNull(message = "Missing Player level")
    private Integer level;
    @NotNull
    private Integer hoursPlayed;
    @NotNull
    private Float killDeathRatio;


    public Integer getLevel() {
        return level;
    }

    public SavePlayerResource setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getHoursPlayed() {
        return hoursPlayed;
    }

    public SavePlayerResource setHoursPlayed(Integer hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
        return this;
    }

    public Float getKillDeathRatio() {
        return killDeathRatio;
    }

    public SavePlayerResource setKillDeathRatio(Float killDeathRatio) {
        this.killDeathRatio = killDeathRatio;
        return this;
    }




}
