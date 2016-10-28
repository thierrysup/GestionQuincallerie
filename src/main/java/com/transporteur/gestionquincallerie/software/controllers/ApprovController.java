package com.transporteur.gestionquincallerie.software.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApprovController implements BootInitializable{

     private ApplicationContext springContext;
    
    @FXML
    private JFXCheckBox chxPlusProduit;

    @FXML
    private JFXButton genererFiche;

    @FXML
    private JFXTextField edtProduit;

    @FXML
    private JFXTextField edtPrixAhat;

    @FXML
    private JFXTextField adtNomFournisseur;

    @FXML
    private JFXComboBox<?> cbxProduit;

    @FXML
    private JFXTextField edtAddrFournisseur;

    @FXML
    private JFXComboBox<?> cbxFournisseur;

    @FXML
    private JFXButton btnValider;

    @FXML
    private JFXTextField edtQteProduit;

    @FXML
    void genererLaFiche(ActionEvent event) {

    }

    @FXML
    void valider(ActionEvent event) {

    }

    @FXML
    void autreFournisseur(ActionEvent event) {

    }


    @FXML
    void autreProduit(ActionEvent event) {

    }

    @Override
    public void initConstruct() {
    }

    @Override
    public void stage(Stage primaryStage) {
    }

    @Override
    public Node initView() {
        try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/approvisionnement.fxml"));
			//loader.setController(springContext.getBean(this.getClass()));
			return loader.load();
		} catch (IOException e) {
			System.err.println("can't load approvisionnement");
			e.printStackTrace();
			return null;
		}
    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.springContext = ac;
    }
}

