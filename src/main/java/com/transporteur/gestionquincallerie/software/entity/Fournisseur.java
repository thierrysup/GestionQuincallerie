/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author thierry
 */
@Entity
public class Fournisseur implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String nomFourn;
    @Column
    private String adresseFour;
    @Column
    private int qte;
    
    @Column
    private float prixAchat;
    
    @Column
    private boolean status;
    @Column
    private String designation;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="produit_fk")
    private Produit produit;

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getNomFourn() {
        return nomFourn;
    }

    public void setNomFourn(String nomFourn) {
        this.nomFourn = nomFourn;
    }

    public String getAdresseFour() {
        return adresseFour;
    }

    public void setAdresseFour(String adresseFour) {
        this.adresseFour = adresseFour;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public float getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
