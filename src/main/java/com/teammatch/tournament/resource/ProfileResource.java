package com.teammatch.tournament.resource;

import com.teammatch.tournament.domain.model.AuditModel;
import java.util.Date;

public class ProfileResource extends AuditModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String description;
    private String gender;
    private String emailAddress;
    private String phoneNumber;
    private Date birthDate;

    public Long getId() {
        return id;
    }

    public ProfileResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfileResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ProfileResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProfileResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public ProfileResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ProfileResource setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileResource setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public ProfileResource setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
