/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.dao;

import com.douwe.generic.dao.IDao;
import com.transporteur.gestionquincallerie.software.entity.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author thierry
 */
public interface LivraisonIDao extends JpaRepository<Livraison,Long>{
    
}
