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
    //FIRST NAME
    @Column(name = "firstname", nullable = false)
    private String firstname;
    //PASSWORD
    @Column(name = "password", nullable = false)
    private String password;
    //ROLE
    @Column(name = "role", nullable = false)
    private String role;
    //EMAIL
    @Column(name = "email", nullable = false)
    private String email;

    public User() {
        super();
    }
    public User(String username, String firstname, String password, String role, String email) {
        this.username = username;
        this.firstname = firstname;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    @Override
    public String toString() {
        return username + " : " + firstname + " : " + email + " : " + password + " : " + role;
    }
}
