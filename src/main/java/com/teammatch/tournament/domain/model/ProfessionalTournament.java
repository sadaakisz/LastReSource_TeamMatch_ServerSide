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


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "player_professional_tournaments", joinColumns = {@JoinColumn(name = "player_id")},
            inverseJoinColumns = {@JoinColumn(name="professional_tournament_id")})
    @JsonIgnore
    private List<Player> players;

    public boolean isInPlayer(Player player){       // Business methods
        return (this.getPlayers().contains(player));
    }

    public ProfessionalTournament addToPlayer(Player player) {
        if(!this.isInPlayer(player)) {
            this.getPlayers().add(player);
        }
        return this;
    }

    public ProfessionalTournament deleteFromPlayer(Player player) {
        if(this.isInPlayer(player)) {
            this.getPlayers().remove(player);
        }
        return this;
    }








    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public ProfessionalTournament setSponsors(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
        return this;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public ProfessionalTournament setPlayers(List<Player> players) {
        this.players = players;
        return this;
    }
}
