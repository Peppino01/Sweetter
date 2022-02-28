package com.azienda.sweetter.model.entity.dto;

import java.sql.Date;

public class RegisterDTO extends LoginDTO {
    String email, name, surname, nationality, gender, phoneNumber, specialization;
    Date dateOfBirth;

    public RegisterDTO() {}

    public RegisterDTO(String username, String password, String email, String name, String surname,
                       Date dateOfBirth, String nationality, String gender, String phoneNumber,
                       String specialization) {
        super(username, password);
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
