/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.dao;

import com.transporteur.gestionquincallerie.software.entity.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author thierry
 */
public interface FournisseurIDao extends JpaRepository<Fournisseur,Long> {
    
}
