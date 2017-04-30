/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import static com.transporteur.gestionquincallerie.software.controllers.EmployeController.stageS;
import com.transporteur.gestionquincallerie.software.entity.Employe;
import com.transporteur.gestionquincallerie.software.entity.Role;
import com.transporteur.gestionquincallerie.software.services.impl.EmployeServiceImp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thierry
 */
@Component
public class CreateEmployeController implements BootInitializable{
   
    private ApplicationContext springContext;
    @FXML
    private JFXTextField edtLoginEmp;

    @FXML
    private JFXTextField edtNomEmp;

    @FXML
    private JFXTextField edtAdresEmp;

    @FXML
    private JFXPasswordField edtPassWord;

    @FXML
    private JFXComboBox<String> cbxEmp;

    @FXML
    private JFXButton btnAddValider;

    @FXML
    private JFXButton btnAnnulerAdd;

    @FXML
    private JFXPasswordField edtComPass;
    
    public EmployeServiceImp eServ;
       
    public EmployeController econt;

    
    private ObservableList<String> roles = FXCollections.observableArrayList("USER","ADMIN");
    
    public void setCtrl(EmployeController ctrl){
        this.econt=ctrl;
    }
    
     @FXML
    void addEmploye(ActionEvent event) {
        if((!edtNomEmp.getText().isEmpty())&&(!edtAdresEmp.getText().isEmpty())
                &&(!edtPassWord.getText().isEmpty())&&(!edtComPass.getText().isEmpty())
                &&(!cbxEmp.getValue().equals(null))){
         if(edtPassWord.getText().equals(edtComPass.getText())){
           Employe emp = new Employe();
           emp.setAdresse(edtAdresEmp.getText());
           emp.setLogin(edtLoginEmp.getText());
           emp.setPassWord(String.valueOf(edtPassWord.getText().hashCode()));
           emp.setNomEmp(edtNomEmp.getText());
           if(cbxEmp.getValue().contains(String.valueOf(Role.ADMIN))){
            emp.setRole(Role.ADMIN);
           }
            else{
            emp.setRole(Role.USER);
           }
           emp.setStatus(true);
           eServ.createEmploye(emp);
           econt.loading();
         }
         stageS.close();
         
        }
    }

    public void seteServ(EmployeServiceImp eServ) {
        this.eServ = eServ;
    }
    
    

   @FXML
    void exitAddEmp(ActionEvent event) {
       stageS.close();
    }

    @Override
    public void initConstruct() {
    }

    @Override
    public void stage(Stage primaryStage) {
    }

    @Override
    public Node initView() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbxEmp.setItems(roles);
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
         this.springContext = ac;
    }
}
