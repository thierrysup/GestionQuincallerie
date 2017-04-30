/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import static com.transporteur.gestionquincallerie.software.controllers.AccueilController.stage;
import static com.transporteur.gestionquincallerie.software.controllers.AuthentificationController.emp;
import com.transporteur.gestionquincallerie.software.entity.Employe;
import com.transporteur.gestionquincallerie.software.services.impl.EmployeServiceImp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author thierry
 */
@Component
public class ConfigurationController implements  BootInitializable{
    
    @FXML
    private Label labUser;

    @FXML
    private JFXTextField edtLogin;
    
    @FXML
    private JFXTextField edtAdresse;

    @FXML
    private JFXPasswordField edtPassFirst;

    @FXML
    private JFXPasswordField edtPassSecond;

    
            
    @FXML
    private JFXButton btnValider;

    @FXML
    private JFXButton btnAnnuler;
    
    public AccueilController acCtrl;
    
    public EmployeServiceImp eServ;
    
    private Employe user;

    @FXML
    void arreter(ActionEvent event) {
        stage.close();
    }

     @FXML
    void valider(ActionEvent event) {
      if(edtPassFirst.getText().equals(edtPassSecond.getText())){
       user.setLogin(edtLogin.getText());
       user.setAdresse(edtAdresse.getText());
       user.setPassWord(String.valueOf(edtPassFirst.getText().hashCode()));
       eServ.updateEmploye(user);
       stage.close();
      }
    }
    
    public void templateConfig(){
          labUser.setText(user.getNomEmp());
          edtLogin.setText(user.getLogin());
          edtAdresse.setText(user.getAdresse());
    }
    

    void seteServ(EmployeServiceImp eServ) {
        this.eServ = eServ;
    }

    void setCtrl(AccueilController aThis) {
        this.acCtrl = aThis;
    }

    public void setUser(Employe user) {
        this.user = user;
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
        
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
    }
}
