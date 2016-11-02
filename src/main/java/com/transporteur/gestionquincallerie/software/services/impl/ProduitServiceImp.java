/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.services.impl;

import com.douwe.generic.dao.DataAccessException;
import com.transporteur.gestionquincallerie.software.dao.ProduitIDao;
import com.transporteur.gestionquincallerie.software.entity.Produit;
import com.transporteur.gestionquincallerie.software.services.ProduitIService;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author thierry
 */
@Service
public class ProduitServiceImp implements ProduitIService{

    @Resource
    private ProduitIDao produitIDao;

    public ProduitServiceImp(ProduitIDao produitIDao) {
        this.produitIDao = produitIDao;
    }

    public ProduitServiceImp() {
    }

    
    
    public ProduitIDao getProduitIDao() {
        return produitIDao;
    }

    public void setProduitIDao(ProduitIDao produitIDao) {
        this.produitIDao = produitIDao;
    }
    
    
    @Override
    public Produit createProduit(Produit produit) throws ServiceException {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return produitIDao.create(produit);
        } catch (DataAccessException ex) {
            Logger.getLogger(ProduitServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le create");
        }
    }

    @Override
    public Produit findProduitById(Long id) throws ServiceException {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return produitIDao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(ProduitServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findByID");
        }
    }

    @Override
    public Produit updateProduit(Produit produit) throws ServiceException {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           return produitIDao.update(produit);
        } catch (DataAccessException ex) {
            Logger.getLogger(ProduitServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le Update");
        }
    }

    @Override
    public List<Produit> findAllProduit() throws ServiceException {
         try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return produitIDao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(ProduitServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findAll");
        }
    }

    @Override
    public List<Produit> findProduitByName(String name) throws ServiceException {
          List<Produit> result = new ArrayList<>();
        try {
            for (Produit produit : produitIDao.findAll()) {
                if((name.isEmpty() || produit.getNom().toLowerCase().contains(name.toLowerCase()))
                        &&(produit.isStatus() == true)
                        )
                    result.add(produit);
            }
            
        } catch (DataAccessException ex) {
            Logger.getLogger(ProduitServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findProductByCriteria");
        }
        return result;
    }

    @Override
    public void deleteProduitById(Produit produit) throws ServiceException {
        try {
            Produit prod = produitIDao.findById(produit.getId());
            if (prod == null) {
                throw new ServiceException("Customer with id " + produit.getId() + " not found");
            }
            prod.setStatus(false);
            produitIDao.update(prod);
        } catch (DataAccessException ex) {
            Logger.getLogger(ProduitServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le deleteProductByUser");
        }
    }
    
}
