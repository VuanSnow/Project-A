package com.myproject.projecta.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    //USER ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    long id;
    //USERNAME
    @Column(name = "username", nullable = false, unique = true)
    String username;
    //FIRST NAME
    @Column(name = "firstname", nullable = false)
    String firstname;
    //PASSWORD
    @Column(name = "password", nullable = false)
    String password;
    //ROLE
    @Column(name = "role", nullable = false)
    String role;
    //EMAIL
    @Column(name = "email", nullable = false)
    String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Message> messages;

    public User() {

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
