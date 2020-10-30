package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "enrollment_tournaments")
public class TournamentMoreEnrollment extends Tournament{
    private Double enrollmentPlayer;
    private Double commissionOrganizer;
    private Double potChampion;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "player_enrollment_tournaments", joinColumns = {@JoinColumn(name = "player_id")},
            inverseJoinColumns = {@JoinColumn(name="enrollment_tournament_id")})
    @JsonIgnore
    private List<Player> players;

    public boolean isInPlayer(Player player){       // Business methods
        return (this.getPlayers().contains(player));
    }

    public TournamentMoreEnrollment addToPlayer(Player player) {
        if(!this.isInPlayer(player)) {
            this.getPlayers().add(player);
        }
        return this;
    }

    public TournamentMoreEnrollment deleteFromPlayer(Player player) {
        if(this.isInPlayer(player)) {
            this.getPlayers().remove(player);
        }
        return this;
    }

    public Double getEnrollmentPlayer() {
        return enrollmentPlayer;
    }

    public TournamentMoreEnrollment setEnrollmentPlayer(Double enrollmentPlayer) {
        this.enrollmentPlayer = enrollmentPlayer;
        return this;
    }



    public Double getPotChampion() {
        return potChampion;
    }

    public TournamentMoreEnrollment setPotChampion(Double potChampion) {
        this.potChampion = potChampion;
        return this;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public TournamentMoreEnrollment setPlayers(List<Player> players) {
        this.players = players;
        return this;
    }

    public Double getCommissionOrganizer() {
        return commissionOrganizer;
    }

    public TournamentMoreEnrollment setCommissionOrganizer(Double commissionOrganizer) {
        this.commissionOrganizer = commissionOrganizer;
        return this;
    }
}
