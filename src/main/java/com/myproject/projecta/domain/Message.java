package com.myproject.projecta.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class Message {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mid", nullable = false, updatable = false)
    //message ID
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

    public Message() {

    }
    public Message(String message, String encode, String encryptedMsg, Boolean visibility) {
        this.message = message;
        this.encode = encode;
        this.encryptedMsg = encryptedMsg;
        this.visibility = visibility;
    }

}
