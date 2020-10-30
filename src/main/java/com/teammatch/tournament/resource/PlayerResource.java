package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;
import com.teammatch.tournament.domain.model.Player;

import java.util.Date;

public class PlayerResource extends AuditModel{

    private Long id;
    private String username;

    private String firstName;


    private String lastName;

    private String description;

    private String gender;

    private String emailAddress;

    private String phoneNumber;

    private Date birthDate;
    private Integer level;

    private Integer hoursPlayed;

    private Float killDeathRatio;

    public Long getId() {
        return id;
    }

    public PlayerResource setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public PlayerResource setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getHoursPlayed() {
        return hoursPlayed;
    }

    public PlayerResource setHoursPlayed(Integer hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
        return this;
    }
    
    public Float getKillDeathRatio(){return  killDeathRatio;}

    public PlayerResource setkillDeathRatio(Float killDeathRatio){
        this.killDeathRatio = killDeathRatio;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public PlayerResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PlayerResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PlayerResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlayerResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public PlayerResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public PlayerResource setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PlayerResource setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public PlayerResource setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PlayerResource setKillDeathRatio(Float killDeathRatio) {
        this.killDeathRatio = killDeathRatio;
        return this;
    }
}
