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

    public FournisseurServiceImp() {
    }

    
    
    public FournisseurServiceImp(FournisseurIDao fournisseurIDao, ProduitIDao pImp) {
        this.fournisseurIDao = fournisseurIDao;
        this.pImp = pImp;
    }

    
    public FournisseurIDao getFournisseurIDao() {
        return fournisseurIDao;
    }

    public void setFournisseurIDao(FournisseurIDao fournisseurIDao) {
        this.fournisseurIDao = fournisseurIDao;
    }
    
    
    
    @Override
    public Fournisseur createFournisseur(Fournisseur fournisseur) throws ServiceException {
         try {
             
             Produit pd = pImp.findById(fournisseur.getProduit().getId());
             pd.setQte(pd.getQte() + fournisseur.getQte());
             pImp.update(pd); 
             
            return fournisseurIDao.create(fournisseur);
        } catch (DataAccessException ex) {
            Logger.getLogger(FournisseurServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le create");
        }
    }

    @Override
    public Fournisseur findFournisseurById(Long id) throws ServiceException {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return fournisseurIDao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(FournisseurServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findByID");
        }
    }

    @Override
    public Fournisseur updateFournisseur(Fournisseur fournisseur) throws ServiceException {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           return fournisseurIDao.update(fournisseur);
        } catch (DataAccessException ex) {
            Logger.getLogger(FournisseurServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le Update");
        }
    }

    @Override
    public List<Fournisseur> findAllFournisseur() throws ServiceException {
         try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return fournisseurIDao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(FournisseurServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findAll");
        }
    }

    @Override
    public List<Fournisseur> findFournisseurtByName(String name) throws ServiceException {
        List<Fournisseur> result = new ArrayList<>();
        try {
               for (Fournisseur fournisseur : fournisseurIDao.findAll()) {
                if((name.isEmpty() || fournisseur.getNomFourn().toLowerCase().contains(name.toLowerCase()))
                        &&(fournisseur.isStatus() == true)
                        )
                    result.add(fournisseur);
            }
            
        } catch (DataAccessException ex) {
            Logger.getLogger(FournisseurServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findProductByName");
        }
        return result;
    }

    @Override
    public void deleteFournisseurById(Fournisseur fournisseur) throws ServiceException {
         try {
            Fournisseur four = fournisseurIDao.findById(fournisseur.getId());
            if (four == null) {
                throw new ServiceException("Customer with id " + fournisseur.getId() + " not found");
            }
            four.setStatus(false);
            fournisseurIDao.update(four);
        } catch (DataAccessException ex) {
            Logger.getLogger(FournisseurServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le deleteLivraisonById");
        }
    }
  }
    
