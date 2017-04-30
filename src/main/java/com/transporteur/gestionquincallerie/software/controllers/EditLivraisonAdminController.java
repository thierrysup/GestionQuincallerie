/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import static com.transporteur.gestionquincallerie.software.controllers.LivraisonAdminController.stageL;
import com.transporteur.gestionquincallerie.software.entity.Livraison;
import com.transporteur.gestionquincallerie.software.services.LivraisonIService;
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
public class EditLivraisonAdminController implements BootInitializable{

    private ApplicationContext springContext;
    
     @FXML
    private JFXTextField edtNomClient;

    @FXML
    private JFXTextField edtQteLivraison;

    @FXML
    private JFXTextField edtAdresseClient;

    @FXML
    private JFXButton btnMOdi;

    @FXML
    private JFXButton btnAnnuler;

    @FXML
    private JFXTextField edtNomProduit;
    
    private LivraisonIService lServ;
     
    public LivraisonAdminController lcont;
     
    private Livraison livr;
    

    public void setlServ(LivraisonIService lServ) {
        this.lServ = lServ;
    }

    public void setLcont(LivraisonAdminController lcont) {
        this.lcont = lcont;
    }

    public void setLivr(Livraison livr) {
        this.livr = livr;
    }
    public void initTemplate(){
       edtNomClient.setText(livr.getNomClient());
       edtAdresseClient.setText(livr.getAdresseClient());
       edtQteLivraison.setText(String.valueOf(livr.getQte()));
       edtNomProduit.setText(livr.getDesignation());
    }
    

    @FXML
    void exitEditLivraison(ActionEvent event) {
            stageL.close();
    }

    @FXML
    void setLivraison(ActionEvent event) {
        livr.setAdresseClient(edtAdresseClient.getText());
        livr.setNomClient(edtNomClient.getText());
        livr.setDesignation(edtNomProduit.getText());
        livr.setQte(Integer.valueOf(edtQteLivraison.getText()));
        
         lServ.updateLivraison(livr);
          lcont.loading();
        stageL.close();
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
        this.springContext = ac;
    }
    
}
