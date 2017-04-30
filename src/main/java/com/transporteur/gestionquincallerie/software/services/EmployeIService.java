/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.services;

import com.transporteur.gestionquincallerie.software.entity.Employe;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

/**
 *
 * @author thierry
 */
public interface EmployeIService {
    public Employe createEmploye(Employe employe) throws ServiceException;
    public Employe findEmployeById(Long id) throws ServiceException;
    public Employe updateEmploye(Employe employe) throws ServiceException;
    public List<Employe> findAllEmploye() throws ServiceException;
    public List<Employe> findEmployeByName(String name) throws ServiceException;
    public Employe findEmployeByLogin(String login) throws ServiceException;
    public List<Employe> findEmployeByCriteria(String name,String role) throws ServiceException;
    public void deleteEmployeById(Employe employe) throws ServiceException;

}
