/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.dao.impl;

import com.douwe.generic.dao.IDao;
import com.douwe.generic.dao.impl.GenericDao;
import com.transporteur.gestionquincallerie.software.dao.ProduitIDao;
import com.transporteur.gestionquincallerie.software.entity.Produit;
import java.io.Serializable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thierry
 */
@Component
public class ProduitDaoImp extends GenericDao<Produit,Long> implements ProduitIDao{
    
}
