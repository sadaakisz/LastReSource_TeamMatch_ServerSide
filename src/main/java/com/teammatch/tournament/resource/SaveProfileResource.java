package com.teammatch.tournament.resource;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveProfileResource {

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String username;

    @NotNull
    @NotBlank
    @Lob
    private String description;

    @NotNull
    private String gender;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String emailAddress;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Date birthDate;

    public String getFirstName() {
        return firstName;
    }

    public SaveProfileResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveProfileResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public SaveProfileResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveProfileResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public SaveProfileResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public SaveProfileResource setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SaveProfileResource setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public SaveProfileResource setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
