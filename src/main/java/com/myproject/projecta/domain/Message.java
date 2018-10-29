package com.myproject.projecta.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    //message ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mid", nullable = false, updatable = false)
    long mid;
    //message
    @Column(name = "message", nullable = false)
    String message;
    //encode
    @Column(name = "encode", nullable = false)
    String encode;
    //encrypted message
    @Column(name = "encryptedMsg", nullable = false)
    String encryptedMsg;
    //visibility for other users
    @Column(name = "visibility", nullable = false)
    Boolean visibility;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id")
    User user;

    public Message() {

    }

    public Message(String message, String encode, String encryptedMsg, Boolean visibility, User user) {
        this.message = message;
        this.encode = encode;
        this.encryptedMsg = encryptedMsg;
        this.visibility = visibility;
        this.user = user;
    }

}
