package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;
import com.teammatch.tournament.domain.model.Tournament;

import java.util.Date;

public class TournamentMoreEnrollmentResource extends AuditModel {
    private Long id;
    private String name;

    private String description;

    private String prize;
    private Boolean publicTournament;
    private String code;
    private Date dateOfTournament;
    private Integer maxTeams;
    private Double enrollmentPlayer;
    private Double commissionOrganizer;
    private Double potChampion;


    public Long getId() {
        return id;
    }

    public TournamentMoreEnrollmentResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TournamentMoreEnrollmentResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TournamentMoreEnrollmentResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPrize() {
        return prize;
    }

    public TournamentMoreEnrollmentResource setPrize(String prize) {
        this.prize = prize;
        return this;
    }

    public Boolean getPublicTournament() {
        return publicTournament;
    }

    public TournamentMoreEnrollmentResource setPublicTournament(Boolean publicTournament) {
        this.publicTournament = publicTournament;
        return this;
    }

    public String getCode() {
        return code;
    }

    public TournamentMoreEnrollmentResource setCode(String code) {
        this.code = code;
        return this;
    }

    public Date getDateOfTournament() {
        return dateOfTournament;
    }

    public TournamentMoreEnrollmentResource setDateOfTournament(Date dateOfTournament) {
        this.dateOfTournament = dateOfTournament;
        return this;
    }

    public Integer getMaxTeams() {
        return maxTeams;
    }

    public TournamentMoreEnrollmentResource setMaxTeams(Integer maxTeams) {
        this.maxTeams = maxTeams;
        return this;
    }

    public Double getEnrollmentPlayer() {
        return enrollmentPlayer;
    }

    public TournamentMoreEnrollmentResource setEnrollmentPlayer(Double enrollmentPlayer) {
        this.enrollmentPlayer = enrollmentPlayer;
        return this;
    }



    public Double getPotChampion() {
        return potChampion;
    }

    public TournamentMoreEnrollmentResource setPotChampion(Double potChampion) {
        this.potChampion = potChampion;
        return this;
    }

    public Double getCommissionOrganizer() {
        return commissionOrganizer;
    }

    public TournamentMoreEnrollmentResource setCommissionOrganizer(Double commissionOrganizer) {
        this.commissionOrganizer = commissionOrganizer;
        return this;
    }
}
