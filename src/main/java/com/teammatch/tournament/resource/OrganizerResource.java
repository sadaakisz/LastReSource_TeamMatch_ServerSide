package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;

public class OrganizerResource extends AuditModel {

    private Long id;
    private String username;

    public Long getId() {
        return id;
    }

    public OrganizerResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public OrganizerResource setUsername(String username) {
        this.username = username;
        return this;
    }
}
