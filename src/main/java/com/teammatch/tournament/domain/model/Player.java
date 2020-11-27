package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "players")
public class Player extends Profile{
    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;
    @NotNull
    private Integer level;

    @NotNull
    private Integer hoursPlayed;

    @NotNull
    private Float killDeathRatio;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "players")
    @JsonIgnore
    private List<Game> games;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "player_chats", joinColumns = {@JoinColumn(name = "player_id")}, inverseJoinColumns = {@JoinColumn(name="chat_id")})
    @JsonIgnore
    private List<Chat> chats;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "player_teams", joinColumns = {@JoinColumn(name = "player_id")}, inverseJoinColumns = {@JoinColumn(name="team_id")})
    @JsonIgnore
    private List<Team> teams;

    @ManyToMany(fetch= FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="player_filters", joinColumns = {@JoinColumn(name="player_id")}, inverseJoinColumns = {@JoinColumn(name="filter_id")})
    @JsonIgnore
    private List<Filter> filters;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "players")
    @JsonIgnore
    private List<FreeTournament> freeTournaments;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "players")
    @JsonIgnore
    private List<ProfessionalTournament> ProfessionalTournaments;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "players")
    @JsonIgnore
    private List<TournamentMoreEnrollment> tournamentMoreEnrollments;

    public Player() {
    }

    public Player(@NotNull String username, @NotNull String password, @NotNull Integer level, @NotNull Integer hoursPlayed, @NotNull Float killDeathRatio) {
        this.username = username;
        this.password = password;
        this.level = level;
        this.hoursPlayed = hoursPlayed;
        this.killDeathRatio = killDeathRatio;
    }


    public boolean isInChat(Chat chat){       // Business methods
        return (this.getChats().contains(chat));
    }

    public Player addToChat(Chat chat) {
        if(!this.isInChat(chat)) {
            this.getChats().add(chat);
        }
        return this;
    }

    public Player deleteFromChat(Chat chat) {
        if(this.isInChat(chat)) {
            this.getChats().remove(chat);
        }
        return this;
    }

    public boolean isInTeam(Team team){       // Business methods
        return (this.getChats().contains(team));
    }

    public Player addToTeam(Team team) {
        if(!this.isInTeam(team)) {
            this.getTeams().add(team);
        }
        return this;
    }

    public Player deleteFromTeam(Team team) {
        if(this.isInTeam(team)) {
            this.getTeams().remove(team);
        }
        return this;
    }





    public List<Chat> getChats() {
        return chats;
    }

    public Player setChats(List<Chat> chats) {
        this.chats = chats;
        return this;
    }

    public List<Game> getGames() {
        return games;
    }

    public Player setGames(List<Game> games) {
        this.games = games;
        return this;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Player setTeams(List<Team> teams) {
        this.teams = teams;
        return this;
    }

    public List<Filter> getFilters(){return filters;}

    public Player setFilters(List<Filter> filters) {
        this.filters = filters;
        return this;
    }

    public List<FreeTournament> getFreeTournaments() {
        return freeTournaments;
    }

    public Player setFreeTournaments(List<FreeTournament> freeTournaments) {
        this.freeTournaments = freeTournaments;
        return this;
    }

    public List<ProfessionalTournament> getProfessionalTournaments() {
        return ProfessionalTournaments;
    }

    public Player setProfessionalTournaments(List<ProfessionalTournament> professionalTournaments) {
        ProfessionalTournaments = professionalTournaments;
        return this;
    }

    public List<TournamentMoreEnrollment> getTournamentMoreEnrollments() {
        return tournamentMoreEnrollments;
    }

    public Player setTournamentMoreEnrollments(List<TournamentMoreEnrollment> tournamentMoreEnrollments) {
        this.tournamentMoreEnrollments = tournamentMoreEnrollments;
        return this;
    }



    public String getPassword() {
        return password;
    }

    public Player setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Player setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public Player setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getHoursPlayed() {
        return hoursPlayed;
    }

    public Player setHoursPlayed(Integer hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
        return this;
    }

    public Float getKillDeathRatio() {
        return killDeathRatio;
    }

    public Player setKillDeathRatio(Float killDeathRatio) {
        this.killDeathRatio = killDeathRatio;
        return this;
    }
}