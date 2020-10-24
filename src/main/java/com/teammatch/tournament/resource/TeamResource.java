package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;
import com.teammatch.tournament.domain.model.Team;

public class TeamResource extends AuditModel {
    private Long id;
    private String name;
    private int teamSize;
    private Float hoursPlayed;
    private Float levelAverage;

    public Long getId() {
        return id;
    }

    public TeamResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TeamResource setTitle(String name) {
        this.name = name;
        return this;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public TeamResource setTeamSize(int teamSize) {
        this.teamSize = teamSize;
        return this;

    }

    public Float getHoursPlayed() {
        return hoursPlayed;
    }

    public TeamResource setContent(Float hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
        return this;
    }

    public Float getLevelAverage() {
        return levelAverage;
    }

    public TeamResource setLevelAverage(Float levelAverage) {
        this.levelAverage = levelAverage;
        return this;
    }
}
