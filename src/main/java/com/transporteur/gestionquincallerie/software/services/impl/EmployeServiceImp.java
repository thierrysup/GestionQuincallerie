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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thierry
 */
@Service
public class EmployeServiceImp implements EmployeIService{

    @Resource
    private EmployeIDao employeIDao;

    @Override
    public Employe createEmploye(Employe employe) throws ServiceException {
        return employeIDao.save(employe);
    }

    @Override
    public Employe findEmployeById(Long id) throws ServiceException {
        return employeIDao.findOne(id);
    }

    @Override
    public Employe updateEmploye(Employe employe) throws ServiceException {
        return employeIDao.save(employe);
    }

    @Override
    public List<Employe> findAllEmploye() throws ServiceException {
        return employeIDao.findAll();
    }

    @Override
    public List<Employe> findEmployeByName(String name) throws ServiceException {
         List<Employe> result = new ArrayList<>();
         for (Employe employe : employeIDao.findAll()) {
             if((name.isEmpty() || employe.getNomEmp().toLowerCase().contains(name.toLowerCase()))
                     &&(employe.isStatus() == true)
                     )
                 result.add(employe);
         }
        return result;
    }

    @Override
    public void deleteEmployeById(Employe employe) throws ServiceException {
        Employe emp = employeIDao.findOne(employe.getId());
        if (emp == null) {
            throw new ServiceException("Customer with id " + employe.getId() + " not found");
        }else{
            emp.setStatus(false);
            employeIDao.save(emp);
        }
    }

    @Override
    public Employe findEmployeByLogin(String login) throws ServiceException {
        for (Employe employe : employeIDao.findAll()) {
            if((login.isEmpty() || employe.getLogin().toLowerCase().contains(login.toLowerCase()))
                    &&(employe.isStatus() == true)
                    )
                return employe;
        }
        return null;
    }
    
}
