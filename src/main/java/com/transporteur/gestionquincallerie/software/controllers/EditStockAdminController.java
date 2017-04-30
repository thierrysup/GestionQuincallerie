/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import static com.transporteur.gestionquincallerie.software.controllers.StockAdminController.stageN;
import com.transporteur.gestionquincallerie.software.entity.Produit;
import com.transporteur.gestionquincallerie.software.services.ProduitIService;
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
public class EditStockAdminController implements BootInitializable{

    private ApplicationContext springContext;
    
    @FXML
    private JFXTextField edtNomProduct;

    @FXML
    private JFXTextField edtQteProduct;

    @FXML
    private JFXTextField edtPriceProduct;

    @FXML
    private JFXButton btnMOdi;

    @FXML
    private JFXButton btnAnnuler;
    
     private ProduitIService pServ;
     
     public StockAdminController pcont;
     
     private Produit pro;
    
     @FXML
    void exitEditProduit(ActionEvent event) {
         stageN.close();
    }

    @FXML
    void setProduit(ActionEvent event) {
          pro.setNom(edtNomProduct.getText());
          pro.setPrixVente(Float.valueOf(edtPriceProduct.getText()));
          pro.setQte(Integer.valueOf(edtQteProduct.getText()));
          
          pServ.updateProduit(pro);
          pcont.loading();
        stageN.close();
    }

    public void setpServ(ProduitIService pServ) {
        this.pServ = pServ;
    }

    public void setPro(Produit pro) {
        this.pro = pro;
    }

    public void setPcont(StockAdminController pcont) {
        this.pcont = pcont;
    }
    public void initTemplate(){
        System.out.println("adresse coold"+pro);
       edtNomProduct.setText(pro.getNom());
       edtPriceProduct.setText(String.valueOf(pro.getPrixVente()));
       edtQteProduct.setText(String.valueOf(pro.getQte()));
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
