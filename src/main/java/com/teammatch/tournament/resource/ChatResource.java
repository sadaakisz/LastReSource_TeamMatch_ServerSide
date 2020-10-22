package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;

import java.util.List;

public class ChatResource extends AuditModel{
    private Long id;
    private List<PlayerResource> participants;

    public Long getId() {
        return id;
    }

    public ChatResource setId(Long id) {
        this.id = id;
        return this;
    }

    public List<PlayerResource> getParticipants() {
        return participants;
    }

    public ChatResource setParticipants(List<PlayerResource> participants) {
        this.participants = participants;
        return this;
    }
}
