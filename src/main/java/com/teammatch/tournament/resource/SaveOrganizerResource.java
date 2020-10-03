package com.teammatch.tournament.resource;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveOrganizerResource {

    @NotNull(message = "Missing Organizer name")
    @NotBlank
    @Length(min = 3, message = "Name needs to have between 3 to 50 characters")
    private String username;

    @NotNull(message = "Missing password")
    @Length(min = 6, max = 16, message = "Password needs to have between 3 to 50 characters")
    private String password;

    public String getUsername() {
        return username;
    }

    public SaveOrganizerResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveOrganizerResource setPassword(String password) {
        this.password = password;
        return this;
    }
}
