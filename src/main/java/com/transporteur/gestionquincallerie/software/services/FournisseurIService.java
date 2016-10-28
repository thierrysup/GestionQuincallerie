/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.services;

import com.transporteur.gestionquincallerie.software.entity.Fournisseur;
import java.util.List;
import org.hibernate.service.spi.ServiceException;

/**
 *
 * @author thierry
 */
public interface FournisseurIService {
    
    public Fournisseur createFournisseur(Fournisseur fournisseur) throws ServiceException;
    public Fournisseur findFournisseurById(Long id) throws ServiceException;
    public Fournisseur updateFournisseur(Fournisseur fournisseur) throws ServiceException;
    public List<Fournisseur> findAllFournisseur() throws ServiceException;
    public List<Fournisseur> findFournisseurtByName(String name) throws ServiceException;
    public void deleteFournisseurById(Fournisseur fournisseur) throws ServiceException;

}
