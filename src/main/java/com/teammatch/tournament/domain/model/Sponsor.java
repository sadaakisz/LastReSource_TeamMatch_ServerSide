package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "sponsors")
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
    @JoinTable(name = "sponsor_professional_tournaments",
            joinColumns = {@JoinColumn(name = "sponsor_id")},
            inverseJoinColumns = {@JoinColumn(name = "professional_tournament_id")})
    @JsonIgnore
    private List<ProfessionalTournament> professionalTournaments;

    public boolean hasProfessionalTournamentWith(ProfessionalTournament  professionalTournament) {
        return (this.getProfessionalTournaments().contains(professionalTournament));
    }

    public Sponsor professionalTournamentWith(ProfessionalTournament professionalTournament) {
        if(!this.hasProfessionalTournamentWith(professionalTournament)) {
            this.getProfessionalTournaments().add(professionalTournament);
        }
        return this;
    }

    public Sponsor unProfessionalTournamentWith(ProfessionalTournament professionalTournament) {
        if(this.hasProfessionalTournamentWith(professionalTournament)) {
            this.getProfessionalTournaments().remove(professionalTournament);
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


    public List<ProfessionalTournament> getProfessionalTournaments() {
        return professionalTournaments;
    }

    public Sponsor setProfessionalTournaments(List<ProfessionalTournament> professionalTournaments) {
        this.professionalTournaments = professionalTournaments;
        return this;
    }

}
