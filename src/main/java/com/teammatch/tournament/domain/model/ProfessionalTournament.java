package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="professional_tournaments")
public class ProfessionalTournament extends Tournament {
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "professionalTournaments")
    @JsonIgnore
    private List<Sponsor> sponsors;

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public ProfessionalTournament setSponsors(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
        return this;
    }
}
