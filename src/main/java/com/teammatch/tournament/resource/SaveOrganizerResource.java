package com.teammatch.tournament.resource;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveOrganizerResource {

    @NotNull(message = "Missing Organizer name")
    @NotBlank
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














    public String getUsername() {
        return username;
    }

    public SaveOrganizerResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveOrganizerResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public SaveOrganizerResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveOrganizerResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveOrganizerResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public SaveOrganizerResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public SaveOrganizerResource setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SaveOrganizerResource setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public SaveOrganizerResource setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
