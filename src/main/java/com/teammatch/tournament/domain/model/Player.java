package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "players")
public class Player extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public Player setId(Long id) {
        this.id = id;
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
}