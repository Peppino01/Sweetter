package com.azienda.sweetter.model.entity;

import com.azienda.sweetter.model.enums.Roles;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role() {

    }

    public Role(Roles role) {
        this.role = role.name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}


