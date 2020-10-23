package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;
import com.teammatch.tournament.domain.model.Filter;

import javax.validation.constraints.NotNull;
import java.util.Date;
public class FilterResource extends AuditModel{
    private Long id;

    private String game;

    private Integer age;

    private String region;

    private String gameStyle;

    private Integer accountLevel;

    private Float rating;

    public Long getId() {
        return id;
    }

    public FilterResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGame() {return game;}

    public FilterResource setGame(String game){
        this.game = game;
        return this;
    }

    public Integer getAge() {return age;}

    public FilterResource setAge(Integer age){
        this.age = age;
        return this;
    }

    public String getRegion(){return region;}

    public FilterResource setRegion(String region){
        this.region = region;
        return this;
    }

    public String getGameStyle(){return gameStyle;}

    public FilterResource setGameStyle(String gameStyle){
        this.gameStyle = gameStyle;
        return this;
    }

    public Integer getAccountLevel(){return accountLevel;}

    public FilterResource setAccountLevel(Integer accountLevel){
        this.accountLevel = accountLevel;
        return this;
    }

    public Float getRating(){return rating;}

    public FilterResource setRating(Float rating){
        this.rating = rating;
        return this;
    }
}
