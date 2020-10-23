package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message extends AuditModel{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name= "sent_date", nullable = false, updatable = false)
    @CreatedDate
    private Date sentDate;
    @NotNull
    @JsonProperty
    private Boolean isHyperLink;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Chat chat;


    public Long getId() {
        return id;
    }

    public Message setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public Message setSentDate(Date sentDate) {
        this.sentDate = sentDate;
        return this;
    }

    public Boolean getHyperLink() {
        return isHyperLink;
    }

    public Message setHyperLink(Boolean hyperLink) {
        isHyperLink = hyperLink;
        return this;
    }

    public Chat getChat() {
        return chat;
    }

    public Message setChat(Chat chat) {
        this.chat = chat;
        return this;
    }
}
