package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;
import java.util.Date;

public class TournamentResource extends AuditModel {

    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date startHour;

    public Long getId() {
        return id;
    }

    public TournamentResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TournamentResource setName(String name) {
        this.name = name;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public TournamentResource setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public TournamentResource setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Date getStartHour() {
        return startHour;
    }

    public TournamentResource setStartHour(Date startHour) {
        this.startHour = startHour;
        return this;
    }
}
