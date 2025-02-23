package com.cultcat.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuari")
public class Usuari {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String googleId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nomUsuari;

    @Column(nullable = false)
    private String nom;

    private String urlImatge;

    @Column(nullable = false)
    private int punts;

    public Usuari() {}

    public Usuari(String googleId, String email, String nomUsuari, String nom, String urlImatge, int punts) {
        this.googleId = googleId;
        this.email = email;
        this.nomUsuari = nomUsuari;
        this.nom = nom;
        this.urlImatge = urlImatge;
        this.punts = punts;
    }

    public long getId() {
        return id;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrlImatge() {
        return urlImatge;
    }

    public void setUrlImatge(String urlImatge) {
        this.urlImatge = urlImatge;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getPunts() {
        return punts;
    }
}
