package com.teammatch.tournament.resource;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSponsorResource {


    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String url;

    public String getName() {
        return name;
    }

    public SaveSponsorResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SaveSponsorResource setUrl(String url) {
        this.url = url;
        return this;
    }
}
