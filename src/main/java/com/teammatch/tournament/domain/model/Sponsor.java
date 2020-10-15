package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Sponsor extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String url;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sponsor_tournaments",
            joinColumns = {@JoinColumn(name = "sponsor_id")},
            inverseJoinColumns = {@JoinColumn(name = "tournament_id")})
    @JsonIgnore
    private List<Tournament> tournaments;

    public boolean hasTournamentWith(Tournament tournament) {
        return (this.getTournaments().contains(tournament));
    }

    public Sponsor tournamentWith(Tournament tournament) {
        if(!this.hasTournamentWith(tournament)) {
            this.getTournaments().add(tournament);
        }
        return this;
    }

    public Sponsor unTournamentWith(Tournament tournament) {
        if(this.hasTournamentWith(tournament)) {
            this.getTournaments().remove(tournament);
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public Sponsor setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Sponsor setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Sponsor setUrl(String url) {
        this.url = url;
        return this;
    }


    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public Sponsor setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
        return this;
    }

}
