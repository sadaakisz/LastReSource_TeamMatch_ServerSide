package com.teammatch.tournament.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
public class SaveFilterResource {
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

    public String getGame() {return game;}

    public SaveFilterResource setGame(String game){
        this.game = game;
        return this;
    }

    public Integer getAge() {return age;}

    public SaveFilterResource setAge(Integer age){
        this.age = age;
        return this;
    }

    public String getRegion(){return region;}

    public SaveFilterResource setRegion(String region){
        this.region = region;
        return this;
    }

    public String getGameStyle(){return gameStyle;}

    public SaveFilterResource setGameStyle(String gameStyle){
        this.gameStyle = gameStyle;
        return this;
    }

    public Integer getAccountLevel(){return accountLevel;}

    public SaveFilterResource setAccountLevel(Integer accountLevel){
        this.accountLevel = accountLevel;
        return this;
    }

    public Float getRating(){return rating;}

    public SaveFilterResource setRating(Float rating){
        this.rating = rating;
        return this;
    }
}
