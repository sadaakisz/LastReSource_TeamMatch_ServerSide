package com.teammatch.tournament.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "organizers")
public class Organizer extends Profile {




    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;



    public String getUsername() {
        return username;
    }

    public Organizer setUsername(String username) {
        this.username = username;
        return this;
    }


    public String getPassword() {
        return password;
    }

    public Organizer setPassword(String password) {
        this.password = password;
        return this;
    }
}