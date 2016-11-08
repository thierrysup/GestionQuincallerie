/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.services.impl;

import com.douwe.generic.dao.DataAccessException;
import com.transporteur.gestionquincallerie.software.dao.LivraisonIDao;
import com.transporteur.gestionquincallerie.software.dao.ProduitIDao;
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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thierry
 */
@Service
public class LivraisonServiceImp implements LivraisonIService{

   
    @Resource
    private LivraisonIDao livraisonIDao;
    
    @Resource
    private ProduitIDao pImp;
    
    
    @Override
    public Livraison createLivraison(Livraison livraison) throws ServiceException {
        Produit pd = pImp.findOne(livraison.getProduit().getId());
        pd.setQte(pd.getQte() - livraison.getQte());
        pImp.save(pd);
        return livraisonIDao.save(livraison);
    }

    @Override
    public Livraison findLivraisonById(Long id) throws ServiceException {
        return livraisonIDao.findOne(id);
    }

    @Override
    public Livraison updateLivraison(Livraison livraison) throws ServiceException {
        return livraisonIDao.save(livraison);
        
    }

    @Override
    public List<Livraison> findAllLivraison() throws ServiceException {
        return livraisonIDao.findAll();
    }

    @Override
    public List<Livraison> findLivraisontByName(String name) throws ServiceException {
        List<Livraison> result = new ArrayList<>();
        for (Livraison livraison : livraisonIDao.findAll()) {
            if((name.isEmpty() || livraison.getNomClient().toLowerCase().contains(name.toLowerCase()))
                    &&(livraison.isStatus() == true)
                    )
                result.add(livraison);
        }
        return result;
    }

    @Override
    public void deleteLivraisontById(Livraison livraison) throws ServiceException {
        Livraison livr = livraisonIDao.findOne(livraison.getId());
        if (livr == null) {
            throw new ServiceException("Customer with id " + livraison.getId() + " not found");
        }
        livr.setStatus(false);
        livraisonIDao.save(livr);
    }
    
    }
    

