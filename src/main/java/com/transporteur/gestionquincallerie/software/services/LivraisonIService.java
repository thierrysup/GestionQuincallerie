/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.services;

import com.transporteur.gestionquincallerie.software.entity.Livraison;
import java.util.Date;
import java.util.List;
import org.hibernate.service.spi.ServiceException;

/**
 *
 * @author thierry
 */
public interface LivraisonIService {
    public Livraison createLivraison(Livraison livraison) throws ServiceException;
    public Livraison findLivraisonById(Long id) throws ServiceException;
    public Livraison updateLivraison(Livraison livraison) throws ServiceException;
    public List<Livraison> findAllLivraison() throws ServiceException;
    public List<Livraison> findLivraisontByName(String name) throws ServiceException;
    public void deleteLivraisontById(Livraison livraison) throws ServiceException;
   public List<Livraison> findLivraisonByCriteria(int qteMin,int qteMax,String nomProduit,Date debut,Date fin) throws ServiceException;
}
