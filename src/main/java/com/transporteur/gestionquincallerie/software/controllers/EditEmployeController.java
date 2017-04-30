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
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author thierry
 */
@Component
public class EditEmployeController implements BootInitializable{
   
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
    private JFXButton btnMOdi;

    @FXML
    private JFXButton btnAnnuler;
    
    private ObservableList<String> roles = FXCollections.observableArrayList("USER","ADMIN");
    
    @FXML
    private JFXPasswordField edtComPass;

    public EmployeServiceImp eServ;
    
    public EmployeController econt;
    
    public Employe empl;
    
    @FXML
    void exitEditEmp(ActionEvent event) {
         stageS.close();
    }

    @FXML
    void setEmploye(ActionEvent event) {
        if(cbxEmp.getValue().contains(String.valueOf(Role.ADMIN))){
            empl.setRole(Role.ADMIN);
           }
            else{
            empl.setRole(Role.USER);
           }
        eServ.updateEmploye(empl);
        econt.loading();
        stageS.close();
    }
   
    public void seteServ(EmployeServiceImp eServ) {
        this.eServ = eServ;
    }
    public void setEmployeSet(Employe empls) {
       // System.out.println("i am "+empls.getNomEmp());
        this.empl = empls;
        //System.out.println("i am deux"+this.empl.getNomEmp());
    }
     public void initTemplate(){
        System.out.println("adresse coold"+empl);
       edtLoginEmp.setText(empl.getLogin());
       edtAdresEmp.setText(empl.getAdresse());
       edtNomEmp.setText(empl.getNomEmp());
       cbxEmp.setValue(String.valueOf(empl.getRole()));
    
    }
    public void setCtrl(EmployeController ctrl){
        this.econt=ctrl;
    }
    
    @Override
    public void initConstruct() {
         // initTemplate();
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
