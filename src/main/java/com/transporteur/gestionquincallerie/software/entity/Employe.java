/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author thierry
 */
@Entity
@Table(name = "employe")
public class Employe implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nomEmp;
    
    @Column(name = "login", nullable = false, unique = true)
    private String login;
    
    @JsonIgnore
    @Size(min = 60, max = 60)
    @Column(name = "password_hash",length = 60)
    private String passWord;
    
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Column
    private boolean status;
    @Column
    private String adresse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    } 
}
