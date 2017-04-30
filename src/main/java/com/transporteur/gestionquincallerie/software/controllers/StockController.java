package com.transporteur.gestionquincallerie.software.controllers;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import com.transporteur.gestionquincallerie.software.entity.Produit;
import com.transporteur.gestionquincallerie.software.services.ProduitIService;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author thierry
 */

@Component
public class StockController implements BootInitializable{

    private ApplicationContext springContext;

    @FXML
    private BorderPane paneBStock;

    
    @FXML
    private JFXButton btnRecherche;

    @FXML
    private JFXTextField edtNomProduit;
    
    @FXML
    private JFXTextField edtQMinProduit;

    @FXML
    private JFXTextField edtQMaxProduit;


    @FXML
    private JFXButton btnImprimer;
    
    @FXML
    private TableView<Produit> tableV;

    @FXML
    private TableColumn<Produit, Long> idColumn;

    @FXML
    private TableColumn<Produit, String> desigColumn;

    @FXML
    private TableColumn<Produit,Integer > qteColumn;

    @Autowired
    private ProduitIService pServ;
    
    @Autowired
    private AccueilController accueil;
    
    private ObservableList<Produit> produitsData = FXCollections.observableArrayList();
    
    private ObservableList<Produit> printList = FXCollections.observableArrayList();
    
    private Produit pro;
    
    private void clearFields(){ 
        edtQMinProduit.setText("");
        edtQMinProduit.setPromptText("Quantité Minimale");
        edtQMaxProduit.setText("");
        edtQMaxProduit.setPromptText("Quantité Maximale");
        edtNomProduit.setText("");
        edtNomProduit.setPromptText("Nom produit");   
    }

    @FXML
   private void rechercher(ActionEvent event) {
          printList.clear();
          if((edtQMinProduit.getText().length() != 0)&&(edtQMaxProduit.getText().length() != 0)){
           printList.addAll(pServ.findProduitByCriteria(Integer.parseInt(edtQMinProduit.getText()),Integer.parseInt(edtQMaxProduit.getText()), edtNomProduit.getText())) ;
          }else{
            if((edtQMinProduit.getText().length() != 0)&&(edtQMaxProduit.getText().length() == 0)){
               printList.addAll(pServ.findProduitByCriteria(Integer.parseInt(edtQMinProduit.getText()),0, edtNomProduit.getText())) ;
            }else{
               if((edtQMinProduit.getText().length() == 0)&&(edtQMaxProduit.getText().length() != 0)) 
                   printList.addAll(pServ.findProduitByCriteria(0,Integer.parseInt(edtQMaxProduit.getText()), edtNomProduit.getText())) ;
               else
                   printList.addAll(pServ.findProduitByCriteria(0,0, edtNomProduit.getText())) ; 
            }
          }
              
          tableV.getItems().clear();
          tableV.getItems().addAll(printList);
          loadTableView();
          clearFields();
    }
    private void loading(){
         // printList.addAll(pServ.findProduitByCriteria(Integer.parseInt(edtQMinProduit.getText()),Integer.parseInt(edtQMaxProduit.getText()), edtNomProduit.getText())) ;
          printList.addAll(pServ.findAllProduit()) ; 
          tableV.getItems().addAll(printList);
          loadTableView();
    }
            
     public void setCenterLayoutStock(Node node) {
           this.paneBStock.setCenter(node);
           this.paneBStock.setTop(null);
           this.paneBStock.autosize();
    }
    
    private void loadTableView(){
       idColumn.setCellValueFactory(new PropertyValueFactory<Produit, Long>("Id"));
       desigColumn.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
       qteColumn.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("qte"));
    }
     
    @FXML
    void imprimer(ActionEvent event) {

    }
    
    
    @FXML
   private void home(ActionEvent event) throws IOException {
         setCenterLayoutStock(accueil.initView());
         tableV.getItems().clear();
         tableV.setItems(null);
         printList.clear();
    }
//fx:controller="com.transporteur.gestionquincallerie.software.controllers.StockController"
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
			loader.setLocation(getClass().getResource("/fxml/stock.fxml"));
			loader.setController(springContext.getBean(this.getClass()));
			return loader.load();
		} catch (IOException e) {
			System.err.println("can't load stock");
			e.printStackTrace();
			return null;
		}
    
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

