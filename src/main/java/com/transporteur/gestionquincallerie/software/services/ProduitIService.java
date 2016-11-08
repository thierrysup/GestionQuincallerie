/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.services;

import com.transporteur.gestionquincallerie.software.entity.Produit;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thierry
 */
public interface ProduitIService {
    public Produit createProduit(Produit produit) throws ServiceException;
    public Produit findProduitById(Long id) throws ServiceException;
    public Produit updateProduit(Produit produit) throws ServiceException;
    public List<Produit> findAllProduit() throws ServiceException;
    public List<Produit> findProduitByCriteria(Integer qteMin,Integer qteMax,String name) throws ServiceException;
    public void deleteProduitById(Produit produit) throws ServiceException;
    public Produit findProduitByName(String name)throws SecurityException;
}
