package com.teammatch.tournament.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveTournamentResource {

    @NotNull(message = "Missing Tournament name")
    @NotBlank
    @Size(min = 3, message = "Name needs to have between 3 to 50 characters")
    private String name;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
    @NotNull
    private Date startHour;

    public String getName() {
        return name;
    }

    public SaveTournamentResource setName(String name) {
        this.name = name;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public SaveTournamentResource setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public SaveTournamentResource setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Date getStartHour() {
        return startHour;
    }

    public SaveTournamentResource setStartHour(Date startHour) {
        this.startHour = startHour;
        return this;
    }
}

