package com.teammatch.tournament.resource.Sponsor;

import com.teammatch.tournament.domain.model.AuditModel;

public class SponsorResource extends AuditModel {

    private Long id;
    private String name;
    private String url;

    public Long getId() {
        return id;
    }

    public SponsorResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SponsorResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SponsorResource setUrl(String url) {
        this.url = url;
        return this;
    }

}
