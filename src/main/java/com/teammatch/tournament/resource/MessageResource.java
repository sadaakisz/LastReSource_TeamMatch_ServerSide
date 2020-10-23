package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;

import java.util.Date;

public class MessageResource extends AuditModel {
    private Long id;
    private String content;
    private Date sentDate;
    private Boolean isHyperLink;

    public Long getId() {
        return id;
    }

    public MessageResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageResource setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public MessageResource setSentDate(Date sentDate) {
        this.sentDate = sentDate;
        return this;
    }

    public Boolean getHyperLink() {
        return isHyperLink;
    }

    public MessageResource setHyperLink(Boolean hyperLink) {
        isHyperLink = hyperLink;
        return this;
    }
}
