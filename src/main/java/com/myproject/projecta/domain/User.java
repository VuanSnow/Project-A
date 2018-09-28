package com.myproject.projecta.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class User {
    //USER ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;
    //USERNAME
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    //PASSWORD
    @Column(name = "password", nullable = false)
    private String password;
    //ROLE
    @Column(name = "role", nullable = false)
    private String role;

    public User() {
        super();
    }
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return username + " : " + password + " : " + role;
    }
}
