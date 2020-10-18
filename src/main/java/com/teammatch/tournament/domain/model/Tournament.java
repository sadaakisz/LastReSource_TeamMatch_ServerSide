package com.teammatch.tournament.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;
@MappedSuperclass
public class Tournament extends AuditModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    @Lob
    private String description;
    @NotNull
    private String prize;

    private Boolean publicTournament;
    @NotNull
    private String code;

    private Date dateOfTournament;
    @NotNull
    private Integer maxTeams;









   // private Date startDate;


   // private Date endDate;


    //private Date startHour;

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


    public Organizer getOrganizer() {
        return organizer;
    }

    public Tournament setOrganizer(Organizer organizer) {
        this.organizer = organizer;
        return this;
    }





    public String getDescription() {
        return description;
    }

    public Tournament setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPrize() {
        return prize;
    }

    public Tournament setPrize(String prize) {
        this.prize = prize;
        return this;
    }

    public Boolean getPublicTournament() {
        return publicTournament;
    }

    public Tournament setPublicTournament(Boolean publicTournament) {
        this.publicTournament = publicTournament;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Tournament setCode(String code) {
        this.code = code;
        return this;
    }

    public Date getDateOfTournament() {
        return dateOfTournament;
    }

    public Tournament setDateOfTournament(Date dateOfTournament) {
        this.dateOfTournament = dateOfTournament;
        return this;
    }

    public Integer getMaxTeams() {
        return maxTeams;
    }

    public Tournament setMaxTeams(Integer maxTeams) {
        this.maxTeams = maxTeams;
        return this;
    }
}