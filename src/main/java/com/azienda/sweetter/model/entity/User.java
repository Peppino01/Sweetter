package com.azienda.sweetter.model.entity;

import com.azienda.sweetter.model.entity.dto.RegisterDTO;
import com.azienda.sweetter.model.enums.ExceptionMessage;
import com.azienda.sweetter.model.enums.Roles;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class User implements Comparable<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique=true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private Date dateOfBirth;

    private String gender;

    @Column(nullable = false)
    private String nationality;

    @Column(unique = true)
    private String phoneNumber;

    private String specialization;

    private String biography;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "postOwner")
    private List<Post> posts;

    @ManyToMany(mappedBy = "likesList")
    private List<Post> likedPost;

    @ManyToMany(mappedBy = "unlikesList")
    private List<Post> unlikedPost;

    public User() {

    }

    public User(String username, String password, String email, String name, String surname,
                Date dateOfBirth, String nationality, Boolean isActive, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.isActive = isActive;
        this.role = role;
    }

    public User(RegisterDTO registerDTO, Role role) {
        this.username = registerDTO.getUsername();
        this.password = registerDTO.getPassword();
        this.email = registerDTO.getEmail();
        this.name = registerDTO.getName();
        this.surname = registerDTO.getSurname();
        this.dateOfBirth = registerDTO.getDateOfBirth();
        this.nationality = registerDTO.getNationality();
        this.isActive = true;
        this.role = role;
    }

    @Override
    public int compareTo(User other) {
        return this.getId() - other.getId();
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getBiography() {
        return biography;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Role getRole() {
        return role;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Post> getLikedPost() {
        return likedPost;
    }

    public List<Post> getUnlikedPost() {
        return unlikedPost;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setLikedPost(List<Post> likedPost) {
        this.likedPost = likedPost;
    }

    public void setUnlikedPost(List<Post> unlikedPost) {
        this.unlikedPost = unlikedPost;
    }
}