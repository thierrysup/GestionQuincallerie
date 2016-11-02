/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.services.impl;

import com.douwe.generic.dao.DataAccessException;
import com.transporteur.gestionquincallerie.software.dao.EmployeIDao;
import com.transporteur.gestionquincallerie.software.entity.Employe;
import com.transporteur.gestionquincallerie.software.services.EmployeIService;
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
public class EmployeServiceImp implements EmployeIService{

    @Resource
    private EmployeIDao employeIDao;

    public EmployeServiceImp() {
    }

    
    
    public EmployeServiceImp(EmployeIDao employeIDao) {
        this.employeIDao = employeIDao;
    }

    
    
    public EmployeIDao getEmployeIDao() {
        return employeIDao;
    }

    public void setEmployeIDao(EmployeIDao employeIDao) {
        this.employeIDao = employeIDao;
    }
    
    
    
    @Override
    public Employe createEmploye(Employe employe) throws ServiceException {
          try {
            return employeIDao.create(employe);
        } catch (DataAccessException ex) {
            Logger.getLogger(EmployeServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le create");
        }
    }

    @Override
    public Employe findEmployeById(Long id) throws ServiceException {
         try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return employeIDao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(EmployeServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findByID");
        }
    }

    @Override
    public Employe updateEmploye(Employe employe) throws ServiceException {
         try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           return employeIDao.update(employe);
        } catch (DataAccessException ex) {
            Logger.getLogger(EmployeServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le Update");
        }
    }

    @Override
    public List<Employe> findAllEmploye() throws ServiceException {
         try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return employeIDao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(EmployeServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findAll");
        }
    }

    @Override
    public List<Employe> findEmployeByName(String name) throws ServiceException {
         List<Employe> result = new ArrayList<>();
        try {
               for (Employe employe : employeIDao.findAll()) {
                if((name.isEmpty() || employe.getNomEmp().toLowerCase().contains(name.toLowerCase()))
                        &&(employe.isStatus() == true)
                        )
                    result.add(employe);
            }
            
        } catch (DataAccessException ex) {
            Logger.getLogger(EmployeServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findProductByName");
        }
        return result;
    }

    @Override
    public void deleteEmployeById(Employe employe) throws ServiceException {
        try {
            Employe emp = employeIDao.findById(employe.getId());
            if (emp == null) {
                throw new ServiceException("Customer with id " + employe.getId() + " not found");
            }else{
            emp.setStatus(false);
            employeIDao.update(emp);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(EmployeServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le deleteLivraisonById");
        }
    }

    @Override
    public Employe findEmployeByLogin(String login) throws ServiceException {
        try {
                
               for (Employe employe : employeIDao.findAll()) {
                if((login.isEmpty() || employe.getLogin().toLowerCase().contains(login.toLowerCase()))
                        &&(employe.isStatus() == true)
                        )
                    return employe;
                 }
            
        } catch (DataAccessException ex) {
            Logger.getLogger(EmployeServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("imposssible de faire le findProductByName");
        }
        return null;
    }
    
}
