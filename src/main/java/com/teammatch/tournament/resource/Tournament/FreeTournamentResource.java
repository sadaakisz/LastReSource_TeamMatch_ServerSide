package com.teammatch.tournament.resource.Tournament;

import com.teammatch.tournament.domain.model.AuditModel;

import java.util.Date;

public class FreeTournamentResource extends AuditModel {

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

    public FreeTournamentResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FreeTournamentResource setName(String name) {
        this.name = name;
        return this;
    }





    public String getDescription() {
        return description;
    }

    public FreeTournamentResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPrize() {
        return prize;
    }

    public FreeTournamentResource setPrize(String prize) {
        this.prize = prize;
        return this;
    }

    public Boolean getPublicTournament() {
        return publicTournament;
    }

    public FreeTournamentResource setPublicTournament(Boolean publicTournament) {
        this.publicTournament = publicTournament;
        return this;
    }

    public String getCode() {
        return code;
    }

    public FreeTournamentResource setCode(String code) {
        this.code = code;
        return this;
    }

    public Date getDateOfTournament() {
        return dateOfTournament;
    }

    public FreeTournamentResource setDateOfTournament(Date dateOfTournament) {
        this.dateOfTournament = dateOfTournament;
        return this;
    }

    public Integer getMaxTeams() {
        return maxTeams;
    }

    public FreeTournamentResource setMaxTeams(Integer maxTeams) {
        this.maxTeams = maxTeams;
        return this;
    }
}
