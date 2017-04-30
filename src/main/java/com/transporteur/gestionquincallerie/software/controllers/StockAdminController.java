/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import com.transporteur.gestionquincallerie.software.entity.Employe;
import com.transporteur.gestionquincallerie.software.entity.Produit;
import com.transporteur.gestionquincallerie.software.services.ProduitIService;
import com.transporteur.gestionquincallerie.software.services.impl.ProduitServiceImp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author thierry
 */
@Component
public class StockAdminController implements BootInitializable{
    
    private ApplicationContext springContext;
    @FXML
    private BorderPane paneBStock;

    @FXML
    private JFXButton btnRecherche;

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
    
    @FXML
    private TableColumn<Produit, Float> prixColumn;

    @FXML
    private TableColumn actionColumn;

    

    @FXML
    private JFXTextField edtQMinProduit;

    @FXML
    private JFXTextField edtQMaxProduit;

    @FXML
    private JFXTextField edtNomProduit;

    @FXML
    private ImageView ibtn;
    
    public static Stage stageN;
    
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
    void home(ActionEvent event) {
         setLoyoutAdminStock(accueil.initView());
         tableV.getItems().clear();
         tableV.setItems(null);
         printList.clear();
    }

    @FXML
    void imprimer(ActionEvent event) {

    }

    @FXML
    void rechercher(ActionEvent event) {
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
    
    private class ButtonCell extends TableCell<Produit, Boolean> {
        SplitMenuButton cellButton = new SplitMenuButton(new MenuItem("DELETE"),new MenuItem("EDITION"));
        
        
                       
      //  final Button edtButton = new Button("Edition");
        
        ButtonCell(){
            
//        	//Action when the button is pressed
            cellButton.setText("OPERATION");
            
            
             cellButton.getItems().get(0).setOnAction(e -> {
                try {
                    Produit currentProduit = (Produit) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                        pServ.deleteProduitById(currentProduit);
                	printList.remove(currentProduit);
                        loading();
                } catch (Exception ex) {
                    Logger.getLogger(StockAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            cellButton.getItems().get(1).setOnAction(e -> {
                try {
                     Produit currentProduit = (Produit) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                        //remove selected item from the table list
                       pro=currentProduit; 
                       loadProduitEdit("editProduct");
                       
                } catch (Exception ex) {
                    Logger.getLogger(StockAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });          
        }
        
       

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
                //setGraphic(edtButton);
                
            }
        }
      }

  
    
     public void loading(){
         // printList.addAll(pServ.findProduitByCriteria(Integer.parseInt(edtQMinProduit.getText()),Integer.parseInt(edtQMaxProduit.getText()), edtNomProduit.getText())) ;
         //System.out.println("size "+pServ.findAllProduit().size());
          printList.clear();
          tableV.getItems().clear();
         printList.addAll(pServ.findAllProduit()) ; 
          tableV.getItems().addAll(printList);
          loadTableView();
    }
       public void loadProduitEdit(String text)throws Exception{
                        stageN = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+text+".fxml"));
                       // Parent root =FXMLLoader.load(getClass().getResource("/fxml/"+text+".fxml"));
                        Parent root =loader.load();
                        EditStockAdminController ctrls =loader.getController();
                        ctrls.setpServ(pServ);
                        ctrls.setPro(pro);
                        ctrls.initTemplate(); 
                        ctrls.setPcont(this);
                        Scene sc = new Scene(root);
                        stageN.setScene(sc);
                        stageN.setResizable(false);
                        stageN.setTitle(text);
                        stageN.initModality((Modality.APPLICATION_MODAL));
                        stageN.show();              
    }
     
     
    private void loadTableView(){
       idColumn.setCellValueFactory(new PropertyValueFactory<Produit, Long>("Id"));
       desigColumn.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
       qteColumn.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("qte"));
       prixColumn.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prixVente"));
       actionColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Produit, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Produit, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        actionColumn.setCellFactory(
                new Callback<TableColumn<Produit, Boolean>, TableCell<Produit, Boolean>>() {

            @Override
            public TableCell<Produit, Boolean> call(TableColumn<Produit, Boolean> p) {
                return new StockAdminController.ButtonCell();
            }
        
        });
    }
    
    @Override
    public void initConstruct() {
    }
    
    public void setLoyoutAdminStock(Node node){
        this.paneBStock.setCenter(node);
        this.paneBStock.setTop(null);
        this.paneBStock.autosize();
    
    }

    @Override
    public void stage(Stage primaryStage) {
    }

    @Override
    public Node initView() {
         try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/stockAdmin.fxml"));
			loader.setController(springContext.getBean(this.getClass()));
			return loader.load();
                       // fx:controller="com.transporteur.gestionquincallerie.software.controllers.StockAdminController"
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
