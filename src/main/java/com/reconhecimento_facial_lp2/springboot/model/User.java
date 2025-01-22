package com.reconhecimento_facial_lp2.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false, unique = true)
    String faceHash;

    public String getFaceHash() {
        return faceHash;
    }
    public String getName() {
        return name;
    }
    public void setFaceHash(String faceHash) {
        this.faceHash = faceHash;
    }
    public void setName(String name) {
        this.name = name;
    }
}
