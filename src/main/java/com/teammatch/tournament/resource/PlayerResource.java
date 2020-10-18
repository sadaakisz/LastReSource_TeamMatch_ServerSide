package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;
import com.teammatch.tournament.domain.model.Player;

import java.util.Date;

public class PlayerResource extends AuditModel{

    private Long id;

    private Integer level;

    private Integer hoursPlayed;

    private Float killDeathRatio;

    public Long getId() {
        return id;
    }

    public PlayerResource setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public PlayerResource setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getHoursPlayed() {
        return hoursPlayed;
    }

    public PlayerResource setHoursPlayed(Integer hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
        return this;
    }
    
    public Float getKillDeathRatio(){return  killDeathRatio;}

    public PlayerResource setkillDeathRatio(Float killDeathRatio){
        this.killDeathRatio = killDeathRatio;
        return this;
    }

}
