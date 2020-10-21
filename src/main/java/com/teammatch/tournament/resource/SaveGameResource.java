package com.teammatch.tournament.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
public class SaveGameResource {
    @NotNull(message = "Missing Game name")
    private String name;
    @NotNull
    private String platform;
    @NotNull
    private Integer maxSquadMembers;

    public String getName() {
        return name;
    }

    public SaveGameResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public SaveGameResource setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public Integer getMaxSquadMembers(){return  maxSquadMembers;}

    public SaveGameResource setMaxSquadMembers(Integer maxSquadMembers){
        this.maxSquadMembers = maxSquadMembers;
        return this;
    }
}
