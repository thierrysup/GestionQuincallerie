/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.dao.impl;

import com.douwe.generic.dao.impl.GenericDao;
import com.transporteur.gestionquincallerie.software.dao.FournisseurIDao;
import com.transporteur.gestionquincallerie.software.entity.Fournisseur;
import org.springframework.stereotype.Component;

/**
 *
 * @author thierry
 */
@Component
public class FournisseurDaoImp extends GenericDao<Fournisseur,Long> implements FournisseurIDao{
    
}