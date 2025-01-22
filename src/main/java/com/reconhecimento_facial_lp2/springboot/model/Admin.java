package com.reconhecimento_facial_lp2.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String senha;

    public Admin(){

    }
    public Admin(String senha) {
        this.senha = senha;
    }
    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
