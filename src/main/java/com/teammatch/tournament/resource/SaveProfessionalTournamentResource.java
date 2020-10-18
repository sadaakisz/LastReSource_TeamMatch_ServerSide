package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveProfessionalTournamentResource  {
    @NotNull(message = "Missing Tournament name")
    @NotBlank
    @Size(min = 3, message = "Name needs to have between 3 to 50 characters")
    private String name;



    private String description;
    @NotNull
    private String prize;

    private Boolean publicTournament;
    @NotNull
    private String code;

    private Date dateOfTournament;
    @NotNull
    private Integer maxTeams;

    public String getName() {
        return name;
    }

    public SaveProfessionalTournamentResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveProfessionalTournamentResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPrize() {
        return prize;
    }

    public SaveProfessionalTournamentResource setPrize(String prize) {
        this.prize = prize;
        return this;
    }

    public Boolean getPublicTournament() {
        return publicTournament;
    }

    public SaveProfessionalTournamentResource setPublicTournament(Boolean publicTournament) {
        this.publicTournament = publicTournament;
        return this;
    }

    public String getCode() {
        return code;
    }

    public SaveProfessionalTournamentResource setCode(String code) {
        this.code = code;
        return this;
    }

    public Date getDateOfTournament() {
        return dateOfTournament;
    }

    public SaveProfessionalTournamentResource setDateOfTournament(Date dateOfTournament) {
        this.dateOfTournament = dateOfTournament;
        return this;
    }

    public Integer getMaxTeams() {
        return maxTeams;
    }

    public SaveProfessionalTournamentResource setMaxTeams(Integer maxTeams) {
        this.maxTeams = maxTeams;
        return this;
    }
}
