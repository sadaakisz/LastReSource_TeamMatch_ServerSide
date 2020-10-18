package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "games")
public class Game extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String platform;

    @NotNull
    private Integer maxSquadMembers;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "player_games",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id")})
    @JsonIgnore
    private List<Player> players;

    public Long getId() {
        return id;
    }

    public Game setId(Long id) {
        this.id = id;
        return this;
    }


    public String getName() {
        return name;
    }

    public Game setName(String name) {
        this.name = name;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public Game setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public Integer getMaxSquadMembers() {
        return maxSquadMembers;
    }

    public Game setMaxSquadMembers(Integer maxSquadMembers) {
        this.maxSquadMembers = maxSquadMembers;
        return this;
    }



}
