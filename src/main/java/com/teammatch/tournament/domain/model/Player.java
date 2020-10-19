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


}