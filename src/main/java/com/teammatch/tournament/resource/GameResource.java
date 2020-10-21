package com.teammatch.tournament.resource;


import com.teammatch.tournament.domain.model.AuditModel;
import com.teammatch.tournament.domain.model.Game;

import java.util.Date;
public class GameResource extends AuditModel{

    private Long id;

    private String name;

    private String platform;

    private Integer maxSquadMembers;

    public Long getId() {
        return id;
    }

    public GameResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GameResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public GameResource setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public Integer getMaxSquadMembers(){return  maxSquadMembers;}

    public GameResource setMaxSquadMembers(Integer maxSquadMembers){
        this.maxSquadMembers = maxSquadMembers;
        return this;
    }
}
