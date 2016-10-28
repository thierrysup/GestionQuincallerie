/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.services.impl;

import com.douwe.generic.dao.DataAccessException;
import com.transporteur.gestionquincallerie.software.dao.LivraisonIDao;
import com.transporteur.gestionquincallerie.software.entity.Livraison;
import com.transporteur.gestionquincallerie.software.entity.Produit;
import com.transporteur.gestionquincallerie.software.services.LivraisonIService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thierry
 */
@Service
public class LivraisonServiceImp implements LivraisonIService{

    @Autowired
    @Resource
    private LivraisonIDao livraisonIDao;
    
    @Resource
    @Autowired
    private ProduitServiceImp pImp;

    public LivraisonIDao getLivraisonIDao() {
        return livraisonIDao;
    }

    public void setLivraisonIDao(LivraisonIDao livraisonIDao) {
        this.livraisonIDao = livraisonIDao;
    }
    
    
    
    @Override
    public Livraison createLivraison(Livraison livraison) throws ServiceException {
         try {
             
             Produit pd = pImp.findProduitById(livraison.getProduit().getId());
             pd.setQte(pd.getQte() - livraison.getQte());
             pImp.updateProduit(pd); 
             
             return livraisonIDao.create(livraison);
        } catch (DataAccessException ex) {
            Logger.getLogger(LivraisonServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le create");
        }
    }

    @Override
    public Livraison findLivraisonById(Long id) throws ServiceException {
         try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return livraisonIDao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(LivraisonServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findByID");
        }
    }

    @Override
    public Livraison updateLivraison(Livraison livraison) throws ServiceException {
       try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           return livraisonIDao.update(livraison);
        } catch (DataAccessException ex) {
            Logger.getLogger(LivraisonServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le Update");
        }
        
    }

    @Override
    public List<Livraison> findAllLivraison() throws ServiceException {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return livraisonIDao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(LivraisonServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findAll");
        }
    }

    @Override
    public List<Livraison> findLivraisontByName(String name) throws ServiceException {
        List<Livraison> result = new ArrayList<>();
        try {
            for (Livraison livraison : livraisonIDao.findAll()) {
                if((name.isEmpty() || livraison.getNomClient().toLowerCase().contains(name.toLowerCase()))
                        &&(livraison.isStatus() == true)
                        )
                    result.add(livraison);
            }
            
        } catch (DataAccessException ex) {
            Logger.getLogger(LivraisonServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findProductByName");
        }
        return result;
    }

    @Override
    public void deleteLivraisontById(Livraison livraison) throws ServiceException {
        try {
            Livraison livr = livraisonIDao.findById(livraison.getId());
            if (livr == null) {
                throw new ServiceException("Customer with id " + livraison.getId() + " not found");
            }
            livr.setStatus(false);
            livraisonIDao.update(livr);
        } catch (DataAccessException ex) {
            Logger.getLogger(LivraisonServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le deleteLivraisonById");
        }
    }
    
    }
    

