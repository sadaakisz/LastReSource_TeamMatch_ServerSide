package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;
import java.util.Date;

public class TournamentResource extends AuditModel {

    private Long id;
    private String name;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Date hora_inicio;

    public Long getId() {
        return id;
    }

    public TournamentResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TournamentResource setName(String name) {
        this.name = name;
        return this;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public TournamentResource setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
        return this;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public TournamentResource setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
        return this;
    }

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public TournamentResource setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
        return this;
    }
}
