package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="teams")
public class Team extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private int teamSize;

    @NotNull
    private Float levelAverage;

    @NotNull
    private Float hoursPlayed;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "team_players",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id")})
    @JsonIgnore
    private List<Player> players;

    public boolean isTeamWith(Player player) {
        return (this.getPlayers().contains(player));
    }

    public Team teamWith(Player player) {
        if(!this.isTeamWith(player)) {
            this.getPlayers().add(player);
        }
        return this;
    }

    public Team unTeamWith(Player player) {
        if(this.isTeamWith(player)) {
            this.getPlayers().remove(player);
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public Team setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public Team setTeamSize(int teamSize) {
        this.teamSize = teamSize;
        return this;
    }

    public Float getLevelAverage() {
        return levelAverage;
    }

    public Team setLevelAverage(Float levelAverage) {
        this.levelAverage = levelAverage;
        return this;
    }

    public Float getHoursPlayed() {
        return hoursPlayed;
    }

    public Team setHoursPlayed(Float hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
        return this;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Team setPlayers(List<Player> players) {
        this.players = players;
        return this;
    }

}