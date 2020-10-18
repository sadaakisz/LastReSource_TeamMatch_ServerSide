package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;

import java.util.Date;

public class ProfessionalTournamentResource extends AuditModel {
    private Long id;
    private String name;

    private String description;

    private String prize;
    private Boolean publicTournament;
    private String code;
    private Date dateOfTournament;
    private Integer maxTeams;

    public Long getId() {
        return id;
    }

    public ProfessionalTournamentResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProfessionalTournamentResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProfessionalTournamentResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPrize() {
        return prize;
    }

    public ProfessionalTournamentResource setPrize(String prize) {
        this.prize = prize;
        return this;
    }

    public Boolean getPublicTournament() {
        return publicTournament;
    }

    public ProfessionalTournamentResource setPublicTournament(Boolean publicTournament) {
        this.publicTournament = publicTournament;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ProfessionalTournamentResource setCode(String code) {
        this.code = code;
        return this;
    }

    public Date getDateOfTournament() {
        return dateOfTournament;
    }

    public ProfessionalTournamentResource setDateOfTournament(Date dateOfTournament) {
        this.dateOfTournament = dateOfTournament;
        return this;
    }

    public Integer getMaxTeams() {
        return maxTeams;
    }

    public ProfessionalTournamentResource setMaxTeams(Integer maxTeams) {
        this.maxTeams = maxTeams;
        return this;
    }
}
