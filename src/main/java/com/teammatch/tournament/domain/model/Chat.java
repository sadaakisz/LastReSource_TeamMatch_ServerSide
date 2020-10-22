package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "chats")
public class Chat extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "chats")
    private List<Player> participants;

    public Long getId() {
        return id;
    }

    public Chat setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Player> getParticipants() {
        return participants;
    }

    public Chat setParticipants(List<Player> participants) {
        this.participants = participants;
        return this;
    }
}
