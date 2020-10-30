package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "free_tournaments")
public class FreeTournament extends Tournament{

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "player_free_tournaments", joinColumns = {@JoinColumn(name = "player_id")},
            inverseJoinColumns = {@JoinColumn(name="free_tournament_id")})
    @JsonIgnore
    private List<Player> players;

    public boolean isInPlayer(Player player){       // Business methods
        return (this.getPlayers().contains(player));
    }

    public FreeTournament addToPlayer(Player player) {
        if(!this.isInPlayer(player)) {
            this.getPlayers().add(player);
        }
        return this;
    }

    public FreeTournament deleteFromPlayer(Player player) {
        if(this.isInPlayer(player)) {
            this.getPlayers().remove(player);
        }
        return this;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public FreeTournament setPlayers(List<Player> players) {
        this.players = players;
        return this;
    }
}
