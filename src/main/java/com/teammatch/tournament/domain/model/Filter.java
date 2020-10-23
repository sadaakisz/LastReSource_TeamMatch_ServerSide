package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "filters")
public class Filter extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String game;

    @NotNull
    private Integer age;

    @NotNull
    private String region;

    @NotNull
    private String gameStyle;

    @NotNull
    private Integer accountLevel;

    @NotNull
    private Float rating;

    public Long getId() {
        return id;
    }

    public Filter setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGame() {return game;}

    public Filter setGame(String game){
        this.game = game;
        return this;
    }

    public Integer getAge() {return age;}

    public Filter setAge(Integer age){
        this.age = age;
        return this;
    }

    public String getRegion(){return region;}

    public Filter setRegion(String region){
        this.region = region;
        return this;
    }

    public String getGameStyle(){return gameStyle;}

    public Filter setGameStyle(String gameStyle){
        this.gameStyle = gameStyle;
        return this;
    }

    public Integer getAccountLevel(){return accountLevel;}

    public Filter setAccountLevel(Integer accountLevel){
        this.accountLevel = accountLevel;
        return this;
    }

    public Float getRating(){return rating;}

    public Filter setRating(Float rating){
        this.rating = rating;
        return this;
    }


}
