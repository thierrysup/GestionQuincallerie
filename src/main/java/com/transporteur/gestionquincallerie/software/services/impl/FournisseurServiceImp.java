/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.services.impl;

import com.douwe.generic.dao.DataAccessException;
import com.transporteur.gestionquincallerie.software.dao.FournisseurIDao;
import com.transporteur.gestionquincallerie.software.dao.ProduitIDao;
import com.transporteur.gestionquincallerie.software.entity.Fournisseur;
import com.transporteur.gestionquincallerie.software.entity.Produit;
import com.transporteur.gestionquincallerie.software.services.FournisseurIService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thierry
 */
@Service
public class FournisseurServiceImp implements FournisseurIService{

    @Resource
    private FournisseurIDao fournisseurIDao;
    
    @Resource
    private ProduitIDao pImp;
    
    @Override
    public Fournisseur createFournisseur(Fournisseur fournisseur) throws ServiceException {
        Produit pd = pImp.findOne(fournisseur.getProduit().getId());
        pd.setQte(pd.getQte() + fournisseur.getQte());
        pImp.save(pd);
        return fournisseurIDao.save(fournisseur);
    }

    @Override
    public Fournisseur findFournisseurById(Long id) throws ServiceException {
        return fournisseurIDao.findOne(id);
    }

    @Override
    public Fournisseur updateFournisseur(Fournisseur fournisseur) throws ServiceException {
        return fournisseurIDao.save(fournisseur);
    }

    @Override
    public List<Fournisseur> findAllFournisseur() throws ServiceException {
        return fournisseurIDao.findAll();
    }

    @Override
    public List<Fournisseur> findFournisseurtByName(String name) throws ServiceException {
        List<Fournisseur> result = new ArrayList<>();
        for (Fournisseur fournisseur : fournisseurIDao.findAll()) {
            if((name.isEmpty() || fournisseur.getNomFourn().toLowerCase().contains(name.toLowerCase()))
                    &&(fournisseur.isStatus() == true)
                    )
                result.add(fournisseur);
        }
        return result;
    }

    @Override
    public void deleteFournisseurById(Fournisseur fournisseur) throws ServiceException {
        Fournisseur four = fournisseurIDao.findOne(fournisseur.getId());
        if (four == null) {
            throw new ServiceException("Customer with id " + fournisseur.getId() + " not found");
        }
        four.setStatus(false);
        fournisseurIDao.save(four);
    }
  }
    
