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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thierry
 */
@Service
public class ProduitServiceImp implements ProduitIService{

    @Resource
    private ProduitIDao produitIDao;
    
    @Override
    public Produit createProduit(Produit produit) throws ServiceException {
        return produitIDao.save(produit);
    }

    @Override
    public Produit findProduitById(Long id) throws ServiceException {
        return produitIDao.findOne(id);
    }

    @Override
    public Produit updateProduit(Produit produit) throws ServiceException {
        return produitIDao.save(produit);
    }

    @Override
    public List<Produit> findAllProduit() throws ServiceException {
         List<Produit> result = new ArrayList<>();
         for (Produit produit : produitIDao.findAll()) {
             if((produit.isStatus() == true)
                     )
                 result.add(produit);
         }
        return result;
    }

    @Override
    public List<Produit> findProduitByCriteria(Integer qteMin,Integer qteMax,String name) throws ServiceException {
      
        List<Produit> result = new ArrayList<>();
  
            for (Produit produit : produitIDao.findAll()) {
                if((name.isEmpty() == true || produit.getNom().toLowerCase().contains(name.toLowerCase()))
                        && ((qteMin == 0) || produit.getQte() >= qteMin)
                        && ((qteMax ==  0) || produit.getQte() <= qteMax)
                        &&(produit.isStatus()== true))
                    
                    result.add(produit);
            }
           
        return result;
        
    }

    @Override
    public void deleteProduitById(Produit produit) throws ServiceException {
        Produit prod = produitIDao.findOne(produit.getId());
        if (prod == null) {
            throw new ServiceException("Customer with id " + produit.getId() + " not found");
        }
        prod.setStatus(false);
        produitIDao.save(prod);
    }

    @Override
    public Produit findProduitByName(String name) throws SecurityException {
           for (Produit produit : produitIDao.findAll()) {
              if((name.isEmpty() || produit.getNom().toLowerCase().contains(name.toLowerCase()))
                      &&(produit.isStatus() == true)
                      )
                  return produit;
          }
        return null;
    }
    
}
