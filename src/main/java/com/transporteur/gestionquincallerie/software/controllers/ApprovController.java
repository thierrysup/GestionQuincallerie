package com.transporteur.gestionquincallerie.software.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import com.transporteur.gestionquincallerie.software.entity.Fournisseur;
import com.transporteur.gestionquincallerie.software.entity.Produit;
import com.transporteur.gestionquincallerie.software.services.impl.FournisseurServiceImp;
import com.transporteur.gestionquincallerie.software.services.impl.ProduitServiceImp;
import java.io.IOException;
import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class ApprovController implements BootInitializable{

     private ApplicationContext springContext;

    @FXML
    private TableView<Fournisseur> tableV;
    
    
     @FXML
    private TableColumn<Fournisseur, Long> idColum;

    @FXML
    private TableColumn<Fournisseur, String> designation;

    @FXML
    private TableColumn<Fournisseur, Integer> qte;

    @FXML
    private TableColumn<Fournisseur, Float> prix;

    @FXML
    private TableColumn<Fournisseur, String> fourni;

     
    @FXML
    private JFXCheckBox chxPlusProduit;
    
    @FXML
    private JFXCheckBox chxfour;
     
    @FXML
    private BorderPane paneBAprov;

    @FXML
    private JFXButton genererFiche;

    @FXML
    private JFXTextField edtProduit;

    @FXML
    private JFXTextField edtPrixAhat;

    @FXML
    private JFXTextField adtNomFournisseur;

    @FXML
    private JFXComboBox<String> cbxProduit;

    @FXML
    private JFXTextField edtAddrFournisseur;

    @FXML
    private JFXComboBox<String> cbxFournisseur;

    @FXML
    private JFXButton btnValider;

    @FXML
    private JFXTextField edtQteProduit;
    
    @Autowired
    private  AccueilController accueil;
    
    @Autowired
    private ProduitServiceImp pServ ;
    
    @Autowired
    private FournisseurServiceImp fServ ;
    
    
    private  Fournisseur fo;
    
    private ObservableList<Fournisseur> fournisseursData = FXCollections.observableArrayList();
    
    private ObservableList<Produit> produitsData = FXCollections.observableArrayList();
    
    private ObservableList<Fournisseur> printList = FXCollections.observableArrayList();
     
//fx:controller="com.transporteur.gestionquincallerie.software.controllers.ApprovController"
    @FXML
    void genererLaFiche(ActionEvent event) {

    }
    private void clearFields(){
        chxPlusProduit.setSelected(false);
        chxfour.setSelected(false);
        edtAddrFournisseur.setText("");
        edtAddrFournisseur.setPromptText("telephone fournsseur");
        edtAddrFournisseur.setDisable(true);
        adtNomFournisseur.setText("");
        adtNomFournisseur.setPromptText("Nom fournisseur");
        adtNomFournisseur.setDisable(true);
        edtPrixAhat.setText("");
        edtPrixAhat.setPromptText("Prix d'achat");
        edtProduit.setText("");
        edtProduit.setPromptText("Nom du produit");
        edtProduit.setDisable(true);
        edtQteProduit.setText("");
        edtQteProduit.setPromptText("Quantite du produit");
        cbxFournisseur.setDisable(false);
        cbxFournisseur.setPromptText("Liste des fournisseurs");
        cbxProduit.setDisable(false);
        cbxProduit.setPromptText("Listes des produits");

    }

    @FXML
    void valider(ActionEvent event) {
       if(chxPlusProduit.isSelected()){
           if(chxfour.isSelected()){
             fo = new Fournisseur();
                   fo.setAdresseFour(edtAddrFournisseur.getText());
                   fo.setDesignation(edtProduit.getText());
                   fo.setNomFourn(adtNomFournisseur.getText());
                   fo.setDateFour(new Date());
                   fo.setPrixAchat(Float.parseFloat(edtPrixAhat.getText()));
                   fo.setQte(Integer.parseInt(edtQteProduit.getText()));
                   fo.setStatus(true);
              Produit p = new Produit();
                   p.setNom(edtProduit.getText());
                   p.setQte(0);
                   p.setStatus(true);
              Produit pr =pServ.createProduit(p);
                   fo.setProduit(pr);
              fServ.createFournisseur(fo);
              clearFields();
              loading();
              loadTableView();
            }else{
           
           
           
            }
       
       
       
       }

    }
    
    public void loadTableView(){
       printList.add(fo);
       tableV.getItems().add(fo);
       idColum.setCellValueFactory(new PropertyValueFactory<Fournisseur, Long>("Id"));
       designation.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("designation"));
       qte.setCellValueFactory(new PropertyValueFactory<Fournisseur,Integer>("qte"));
       prix.setCellValueFactory(new PropertyValueFactory<Fournisseur,Float>("prixAchat"));
       fourni.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("nomFourn"));
    }

    @Override
    public void initConstruct() {
       tableV.getItems().clear();
   //    tableV.getItems().add(fo);
       //tableV.getItems().addAll(fServ.findAllFournisseur());
    //    System.out.println("number : "+fServ.findAllFournisseur().size());
    }
    public void setCenterLayoutApprov(Node node){
        this.paneBAprov.setCenter(node);
        this.paneBAprov.setTop(null);
        this.paneBAprov.autosize();
    
    } 
    
    @Override
    public void stage(Stage primaryStage) {
    }
    
    @FXML
    void oFournisseur(ActionEvent event) {
       if(!chxfour.isSelected()){
            edtAddrFournisseur.setDisable(true);
            edtAddrFournisseur.setText("");
            adtNomFournisseur.setDisable(true);
            adtNomFournisseur.setText("");
            cbxFournisseur.setDisable(false);
        }else{
           edtAddrFournisseur.setDisable(false);
           adtNomFournisseur.setDisable(false);
           cbxFournisseur.setDisable(true);
           cbxFournisseur.setValue(null);
    
        }
    }
    
    @FXML
    void oProduit(ActionEvent event) {
        if(!chxPlusProduit.isSelected()){
            edtProduit.setDisable(true);
            edtProduit.setText("");
            cbxProduit.setDisable(false);
        }else{
            edtProduit.setDisable(false);
            cbxProduit.setDisable(true);
            cbxProduit.setValue(null);
        }
    }
    
    @FXML
    private void home(ActionEvent event) throws IOException {
       setCenterLayoutApprov(accueil.initView());
       tableV.getItems().clear();
       tableV.setItems(null);
    }

    @Override
    public Node initView() {
        try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/approvisionnement.fxml"));
			loader.setController(springContext.getBean(this.getClass()));
			return loader.load();
		} catch (IOException e) {
			System.err.println("can't load approvisionnement");
			e.printStackTrace();
			return null;
		}
    
    }
    
    public ObservableList<String> loadNameFour(ObservableList<Fournisseur> list){
        int i = 0;
        List<String> tab = new  ArrayList<String>() ;
          while (i<list.size()) {
            tab.add(list.get(i).getNomFourn());
            i++;
        }
        ObservableList<String> result = FXCollections.observableArrayList();
          result.addAll(tab);
          return result;
    }
    
    private ObservableList<String> loadNameProd(ObservableList<Produit> list){
        int i = 0;
        List<String> tab = new  ArrayList<String>() ;
          while (i<list.size()) {
            tab.add(list.get(i).getNom());
            i++;
        }
        ObservableList<String> result = FXCollections.observableArrayList();
          result.addAll(tab);
          return result;
    }
    
    private void loading(){
        
       cbxFournisseur.setItems(null);
       cbxProduit.setItems(null);
       fournisseursData.clear();
       produitsData.clear();
       
       fournisseursData.addAll(fServ.findAllFournisseur());
       produitsData.addAll(pServ.findAllProduit());
       cbxFournisseur.setItems(loadNameFour(fournisseursData));
       cbxProduit.setItems(loadNameProd(produitsData));
       
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loading(); 
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.springContext = ac;
    }
}

