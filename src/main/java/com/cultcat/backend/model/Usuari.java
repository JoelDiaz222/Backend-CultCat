package com.cultcat.backend.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuari")
public class Usuari implements UserDetails {
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "rols_usuari", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "rol")
    private Set<String> rols;

    public Usuari() {}

    public Usuari(String googleId,
                  String email,
                  String nomUsuari,
                  String nom,
                  String urlImatge,
                  int punts,
                  Set<String> rols) {
        this.googleId = googleId;
        this.email = email;
        this.nomUsuari = nomUsuari;
        this.nom = nom;
        this.urlImatge = urlImatge;
        this.punts = punts;
        this.rols = rols;
    }

    public Long getId() {
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

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public Set<String> getRols() {
        return rols;
    }

    public void setRols(Set<String> rols) {
        this.rols = rols;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rols.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
