package com.teammatch.tournament.resource.Tournament;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveTournamentMoreEnrollmentResource {
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
    private Double enrollmentPlayer;
    private Double commissionOrganizer;
    private Double potChampion;

    public String getName() {
        return name;
    }

    public SaveTournamentMoreEnrollmentResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveTournamentMoreEnrollmentResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPrize() {
        return prize;
    }

    public SaveTournamentMoreEnrollmentResource setPrize(String prize) {
        this.prize = prize;
        return this;
    }

    public Boolean getPublicTournament() {
        return publicTournament;
    }

    public SaveTournamentMoreEnrollmentResource setPublicTournament(Boolean publicTournament) {
        this.publicTournament = publicTournament;
        return this;
    }

    public String getCode() {
        return code;
    }

    public SaveTournamentMoreEnrollmentResource setCode(String code) {
        this.code = code;
        return this;
    }

    public Date getDateOfTournament() {
        return dateOfTournament;
    }

    public SaveTournamentMoreEnrollmentResource setDateOfTournament(Date dateOfTournament) {
        this.dateOfTournament = dateOfTournament;
        return this;
    }

    public Integer getMaxTeams() {
        return maxTeams;
    }

    public SaveTournamentMoreEnrollmentResource setMaxTeams(Integer maxTeams) {
        this.maxTeams = maxTeams;
        return this;
    }

    public Double getEnrollmentPlayer() {
        return enrollmentPlayer;
    }

    public SaveTournamentMoreEnrollmentResource setEnrollmentPlayer(Double enrollmentPlayer) {
        this.enrollmentPlayer = enrollmentPlayer;
        return this;
    }



    public Double getPotChampion() {
        return potChampion;
    }

    public SaveTournamentMoreEnrollmentResource setPotChampion(Double potChampion) {
        this.potChampion = potChampion;
        return this;
    }

    public Double getCommissionOrganizer() {
        return commissionOrganizer;
    }

    public SaveTournamentMoreEnrollmentResource setCommissionOrganizer(Double commissionOrganizer) {
        this.commissionOrganizer = commissionOrganizer;
        return this;
    }
}
