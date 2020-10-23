package com.teammatch.tournament.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveMessageResource {
    @NotBlank
    private String content;
    @NotNull
    @JsonProperty
    private Boolean isHyperLink;


    public String getContent() {
        return content;
    }

    public SaveMessageResource setContent(String content) {
        this.content = content;
        return this;
    }

    public Boolean getHyperLink() {
        return isHyperLink;
    }

    public SaveMessageResource setHyperLink(Boolean hyperLink) {
        isHyperLink = hyperLink;
        return this;
    }
}
