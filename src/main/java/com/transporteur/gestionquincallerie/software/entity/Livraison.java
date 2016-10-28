/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author thierry
 */
@Entity
@Table(name = "livraison")
public class Livraison implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String nomClient;
    @Column
    private String adresseClient;
    @Column
    private int qte;
    @Column
    private boolean status;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="produit_fk")
    private Produit produit;

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLivre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getAdresse() {
        return adresseClient;
    }

    public void setAdresse(String adresse) {
        this.adresseClient = adresse;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Date getDateLivre() {
        return dateLivre;
    }

    public void setDateLivre(Date dateLivre) {
        this.dateLivre = dateLivre;
    }
    
    
}
