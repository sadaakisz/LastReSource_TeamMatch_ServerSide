package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "tournaments")
public class Tournament extends AuditModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private Date fecha_inicio;

    @NotNull
    private Date fecha_fin;

    @NotNull
    private Date hora_inicio;

    @NotNull
    private Long organizer_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organizer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Organizer organizer;

    public Long getId() {
        return id;
    }

    public Tournament setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tournament setName (String name) {
        this.name = name;
        return this;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public Tournament setOrganizer(Organizer organizer) {
        this.organizer = organizer;
        return this;
    }

}