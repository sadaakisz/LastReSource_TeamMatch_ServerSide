package com.teammatch.tournament.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveTournamentResource {

    @NotNull(message = "Missing Tournament name")
    @NotBlank
    @Size(min = 3, message = "Name needs to have between 3 to 50 characters")
    private String name;
    @NotNull
    private Date fecha_inicio;
    @NotNull
    private Date fecha_fin;
    @NotNull
    private Date hora_inicio;

    public String getName() {
        return name;
    }

    public SaveTournamentResource setName(String name) {
        this.name = name;
        return this;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public SaveTournamentResource setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
        return this;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public SaveTournamentResource setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
        return this;
    }

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public SaveTournamentResource setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
        return this;
    }
}

