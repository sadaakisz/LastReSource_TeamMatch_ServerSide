package com.teammatch.tournament.resource;


import org.hibernate.validator.constraints.Length;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SavePlayerResource {

    @Length(min = 3, message = "Name needs to have between 3 to 50 characters")
    private String username;

    @NotNull(message = "Missing password")
    @Length(min = 6, max = 16, message = "Password needs to have between 3 to 50 characters")
    private String password;
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;


    @Lob
    private String description;

    @NotNull
    private String gender;

    @NotNull
    @Size(max = 100)
    private String emailAddress;

    @NotNull
    private String phoneNumber;


    private Date birthDate;



    @NotNull(message = "Missing Player level")
    private Integer level;
    @NotNull
    private Integer hoursPlayed;
    @NotNull
    private Float killDeathRatio;


    public Integer getLevel() {
        return level;
    }

    public SavePlayerResource setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getHoursPlayed() {
        return hoursPlayed;
    }

    public SavePlayerResource setHoursPlayed(Integer hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
        return this;
    }

    public Float getKillDeathRatio() {
        return killDeathRatio;
    }

    public SavePlayerResource setKillDeathRatio(Float killDeathRatio) {
        this.killDeathRatio = killDeathRatio;
        return this;
    }


    public String getUsername() {
        return username;
    }

    public SavePlayerResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SavePlayerResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public SavePlayerResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SavePlayerResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SavePlayerResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public SavePlayerResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public SavePlayerResource setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SavePlayerResource setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public SavePlayerResource setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
