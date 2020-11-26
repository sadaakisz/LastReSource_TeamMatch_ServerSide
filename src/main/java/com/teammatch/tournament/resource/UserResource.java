package com.teammatch.tournament.resource;

public class UserResource {
    public String getUsername() {
        return username;
    }

    public UserResource setUsername(String username) {
        this.username = username;
        return this;
    }

    private String username;
}
