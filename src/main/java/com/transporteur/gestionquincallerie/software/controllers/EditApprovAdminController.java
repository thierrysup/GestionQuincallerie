/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import static com.transporteur.gestionquincallerie.software.controllers.ApprovAdminController.stageF;
import com.transporteur.gestionquincallerie.software.entity.Fournisseur;
import com.transporteur.gestionquincallerie.software.services.FournisseurIService;
import java.net.URL;
import java.util.ResourceBundle;
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
public class EditApprovAdminController implements BootInitializable{
    
    private ApplicationContext springContext;

    @FXML
    private JFXTextField edtNomFour;

    @FXML
    private JFXTextField edtQteProduct;

    @FXML
    private JFXTextField edtPriceFour;

    @FXML
    private JFXButton btnMOdi;

    @FXML
    private JFXButton btnAnnuler;

    @FXML
    private JFXTextField edtNonProduit;

    @FXML
    private JFXTextField edtAdresseFour;
    
    private FournisseurIService fServ;
     
    public ApprovAdminController fcont;
     
    private Fournisseur four;

    public void setfServ(FournisseurIService fServ) {
        this.fServ = fServ;
    }

    public void setFcont(ApprovAdminController fcont) {
        this.fcont = fcont;
    }

    public void setFour(Fournisseur four) {
        this.four = four;
    }
    
    public void initTemplate(){
       edtNomFour.setText(four.getNomFourn());
       edtAdresseFour.setText(four.getAdresseFour());
       edtQteProduct.setText(String.valueOf(four.getQte()));
       edtNonProduit.setText(four.getDesignation());
       edtPriceFour.setText(String.valueOf(four.getPrixUnitaire()));
    }
    

    @FXML
    void exitEdit(ActionEvent event) {
        stageF.close();
    }

    @FXML
    void setFournisseur(ActionEvent event) {
            four.setAdresseFour(edtAdresseFour.getText());
            four.setDesignation(edtNomFour.getText());
            four.setPrixUnitaire(Float.valueOf(edtPriceFour.getText()));
            four.setQte(Integer.valueOf(edtQteProduct.getText()));
            four.setNomFourn(edtNomFour.getText());
         
            fServ.updateFournisseur(four);
          fcont.loading();
        stageF.close();
      
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
        this.springContext =ac;
    }
}
