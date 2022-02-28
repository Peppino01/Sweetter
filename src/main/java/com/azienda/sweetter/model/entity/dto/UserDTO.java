package com.azienda.sweetter.model.entity.dto;

import com.azienda.sweetter.model.entity.User;

import java.sql.Date;

public class UserDTO {
    private String username, name, surname, gender, nationality, specialization, biography;
    private Date dateOfBirth;

    public UserDTO() {}

    public UserDTO(String username, String name, String surname, Date dateOfBirth, String gender,
                   String nationality, String specialization, String biography) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.specialization = specialization;
        this.biography = biography;
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.dateOfBirth = user.getDateOfBirth();
        this.gender = user.getGender();
        this.nationality = user.getNationality();
        this.specialization = user.getSpecialization();
        this.biography = user.getBiography();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
