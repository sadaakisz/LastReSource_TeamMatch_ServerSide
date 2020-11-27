package com.teammatch.tournament.resource.Organizer;

import com.teammatch.tournament.domain.model.AuditModel;

import java.util.Date;

public class OrganizerResource extends AuditModel {

    private Long id;
    private String username;

    private String firstName;


    private String lastName;

    private String description;

    private String gender;

    private String emailAddress;

    private String phoneNumber;

    private Date birthDate;
    public Long getId() {
        return id;
    }

    public OrganizerResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public OrganizerResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public OrganizerResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public OrganizerResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrganizerResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public OrganizerResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public OrganizerResource setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public OrganizerResource setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public OrganizerResource setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
