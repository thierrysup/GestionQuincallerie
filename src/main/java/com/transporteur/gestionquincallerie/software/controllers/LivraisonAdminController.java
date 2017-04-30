/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import com.transporteur.gestionquincallerie.software.entity.Livraison;
import com.transporteur.gestionquincallerie.software.services.LivraisonIService;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
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
public class LivraisonAdminController implements BootInitializable{
    
    private ApplicationContext springContext;
    
    public static Stage stageL;
    
    @FXML
    private BorderPane paneBStock;

    @FXML
    private JFXButton btnRecherche;

    @FXML
    private JFXButton btnImprimer;

   @FXML
    private TableView<Livraison> tableV;

    @FXML
    private TableColumn<Livraison, Long> idColumn;

    @FXML
    private TableColumn<Livraison, String> clientColumn;

    @FXML
    private TableColumn<Livraison, String> adresColumn;

    @FXML
    private TableColumn<Livraison, String> prodColumn1;

    @FXML
    private TableColumn<Livraison, Integer> qteColumn;

    @FXML
    private TableColumn<Livraison, Date> dateColumn;

    @FXML
    private TableColumn actionColumn;

    @FXML
    private JFXTextField edtQMinProduit;

    @FXML
    private JFXTextField edtQMaxProduit;

    @FXML
    private JFXTextField edtNomProduit;

    @FXML
    private JFXDatePicker edtDateTo;

    @FXML
    private JFXDatePicker edtDateFrom;

    @FXML
    private ImageView ibtn;
    
    @Autowired
    private LivraisonIService lServ;
    
    @Autowired
    private AccueilController accueil;
    
    private ObservableList<Livraison> livraisonsData = FXCollections.observableArrayList();
    
    private ObservableList<Livraison> printList = FXCollections.observableArrayList();
    
    private Livraison livr;
    
    private void clearFields(){ 
        edtQMinProduit.setText("");
        edtQMinProduit.setPromptText("Quantité Minimale");
        edtQMaxProduit.setText("");
        edtQMaxProduit.setPromptText("Quantité Maximale");
        edtNomProduit.setText("");
        edtNomProduit.setPromptText("Nom produit");  
        edtDateTo.setValue(null);
        edtDateTo.setPromptText("To");
        edtDateFrom.setValue(null);
        edtDateFrom.setPromptText("From");
    }

    public void setLoyoutAdminLivraison(Node node){
        this.paneBStock.setCenter(node);
        this.paneBStock.setTop(null);
        this.paneBStock.autosize();
    
    }
    
    public void loading(){
         
          printList.clear();
          tableV.getItems().clear();
          printList.addAll(lServ.findAllLivraison()) ; 
          tableV.getItems().addAll(printList);
          loadTableView();
    }

    
    @FXML
    void home(ActionEvent event) {
        setLoyoutAdminLivraison(accueil.initView());
         tableV.getItems().clear();
         tableV.setItems(null);
         printList.clear();
    }

    @FXML
    void imprimer(ActionEvent event) {

    }
    
    public void loadLivraisonEdit(String text)throws Exception{
                        stageL = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+text+".fxml"));
                       // Parent root =FXMLLoader.load(getClass().getResource("/fxml/"+text+".fxml"));
                        Parent root =loader.load();
                        EditLivraisonAdminController ctrls =loader.getController();
                        ctrls.setlServ(lServ);
                        ctrls.setLivr(livr);
                        ctrls.initTemplate(); 
                        ctrls.setLcont(this);
                        Scene sc = new Scene(root);
                        stageL.setScene(sc);
                        stageL.setResizable(false);
                        stageL.setTitle(text);
                        stageL.initModality((Modality.APPLICATION_MODAL));
                        stageL.show();
                        
                       
    }
    
     private class ButtonCell extends TableCell<Livraison, Boolean> {
        SplitMenuButton cellButton = new SplitMenuButton(new MenuItem("DELETE"),new MenuItem("EDITION"));
        
        
                       
      //  final Button edtButton = new Button("Edition");
        
        ButtonCell(){
            
//        	//Action when the button is pressed
            cellButton.setText("OPERATION");
            
            
             cellButton.getItems().get(0).setOnAction(e -> {
                try {
                    Livraison currentLivraison = (Livraison) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                        lServ.deleteLivraisontById(currentLivraison);
                	printList.remove(currentLivraison);
                        loading();
                } catch (Exception ex) {
                    Logger.getLogger(LivraisonAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            cellButton.getItems().get(1).setOnAction(e -> {
                try {
                     Livraison currentLivraison = (Livraison) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                        //remove selected item from the table list
                       livr=currentLivraison; 
                       loadLivraisonEdit("editLivraison");
                       // loading();
                       
                } catch (Exception ex) {
                    Logger.getLogger(LivraisonAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });    
      
        }
        
       

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);                
            }
        }
      }


    @FXML
    void rechercher(ActionEvent event) {
         printList.clear();
          if((edtQMinProduit.getText().length() != 0)&&(edtQMaxProduit.getText().length() != 0)){
           if((edtDateTo.getValue() != null)&&(edtDateFrom.getValue() != null)){
             printList.addAll(lServ.findLivraisonByCriteria(Integer.parseInt(edtQMinProduit.getText()), Integer.parseInt(edtQMaxProduit.getText()), edtNomProduit.getText(),Date.from(edtDateTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(edtDateFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
           }else{
           if((edtDateTo.getValue() == null)&&(edtDateFrom.getValue() != null)){
                printList.addAll(lServ.findLivraisonByCriteria(Integer.parseInt(edtQMinProduit.getText()), Integer.parseInt(edtQMaxProduit.getText()), edtNomProduit.getText(),null, Date.from(edtDateFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
           }else{
               if((edtDateTo.getValue() != null)&&(edtDateFrom.getValue() == null))
                printList.addAll(lServ.findLivraisonByCriteria(Integer.parseInt(edtQMinProduit.getText()), Integer.parseInt(edtQMaxProduit.getText()), edtNomProduit.getText(),Date.from(edtDateTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),null));
               else
                printList.addAll(lServ.findLivraisonByCriteria(Integer.parseInt(edtQMinProduit.getText()), Integer.parseInt(edtQMaxProduit.getText()), edtNomProduit.getText(),null,null));
           }        
           }
          }else{
            if((edtQMinProduit.getText().length() != 0)&&(edtQMaxProduit.getText().length() == 0)){

                if((edtDateTo.getValue() != null)&&(edtDateFrom.getValue() != null)){
                 printList.addAll(lServ.findLivraisonByCriteria(Integer.parseInt(edtQMinProduit.getText()), 0, edtNomProduit.getText(),Date.from(edtDateTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(edtDateFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
               }else{
               if((edtDateTo.getValue() == null)&&(edtDateFrom.getValue() != null)){
                    printList.addAll(lServ.findLivraisonByCriteria(Integer.parseInt(edtQMinProduit.getText()), 0, edtNomProduit.getText(),null, Date.from(edtDateFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
               }else{
                   if((edtDateTo.getValue() != null)&&(edtDateFrom.getValue() == null))
                    printList.addAll(lServ.findLivraisonByCriteria(Integer.parseInt(edtQMinProduit.getText()),0, edtNomProduit.getText(),Date.from(edtDateTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),null));
                   else
                    printList.addAll(lServ.findLivraisonByCriteria(Integer.parseInt(edtQMinProduit.getText()), 0, edtNomProduit.getText(),null,null));
               }

               }
            }else{
               if((edtQMinProduit.getText().length() == 0)&&(edtQMaxProduit.getText().length() != 0)){                   
                    if((edtDateTo.getValue() != null)&&(edtDateFrom.getValue() != null)){
                     printList.addAll(lServ.findLivraisonByCriteria(0,Integer.parseInt(edtQMaxProduit.getText()), edtNomProduit.getText(),Date.from(edtDateTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(edtDateFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                   }else{
                   if((edtDateTo.getValue() == null)&&(edtDateFrom.getValue() != null)){
                        printList.addAll(lServ.findLivraisonByCriteria(0,Integer.parseInt(edtQMaxProduit.getText()), edtNomProduit.getText(),null, Date.from(edtDateFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                   }else{
                       if((edtDateTo.getValue() != null)&&(edtDateFrom.getValue() == null))
                        printList.addAll(lServ.findLivraisonByCriteria(0,Integer.parseInt(edtQMaxProduit.getText()), edtNomProduit.getText(),Date.from(edtDateTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),null));
                       else
                        printList.addAll(lServ.findLivraisonByCriteria(0,Integer.parseInt(edtQMaxProduit.getText()), edtNomProduit.getText(),null,null));
                   }

                   }     
               }else{
                   System.out.println("test Null "+edtNomProduit.getText());
                if((edtDateTo.getValue() != null)&&(edtDateFrom.getValue() != null)){
                     printList.addAll(lServ.findLivraisonByCriteria(0,0, edtNomProduit.getText(),Date.from(edtDateTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(edtDateFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                   }else{
                   if((edtDateTo.getValue() == null)&&(edtDateFrom.getValue() != null)){
                        printList.addAll(lServ.findLivraisonByCriteria(0,0, edtNomProduit.getText(),null, Date.from(edtDateFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                   }else{
                       if((edtDateTo.getValue() != null)&&(edtDateFrom.getValue() == null)){
                        printList.addAll(lServ.findLivraisonByCriteria(0,0, edtNomProduit.getText(),Date.from(edtDateTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),null));
                       }else{
                           System.out.println("test Null "+edtNomProduit.getText());
                        printList.addAll(lServ.findLivraisonByCriteria(0,0, edtNomProduit.getText(),null,null));
                       }
                       }

                   }
                   
                   
               }
               }
          }
              
          tableV.getItems().clear();
          tableV.getItems().addAll(printList);
          loadTableView();
          clearFields();
    }

    private void loadTableView(){
//       printList.add(livr);
//       tableV.getItems().add(livr);
    
       idColumn.setCellValueFactory(new PropertyValueFactory<Livraison,Long>("Id"));
       prodColumn1.setCellValueFactory(new PropertyValueFactory<Livraison,String>("designation"));
       qteColumn.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("qte"));
       clientColumn.setCellValueFactory(new PropertyValueFactory<Livraison,String>("nomClient"));
       adresColumn.setCellValueFactory(new PropertyValueFactory<Livraison,String>("adresseClient"));
       dateColumn.setCellValueFactory(new PropertyValueFactory<Livraison,Date>("dateLivre"));
       
        actionColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Livraison, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Livraison, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        actionColumn.setCellFactory(
                new Callback<TableColumn<Livraison, Boolean>, TableCell<Livraison, Boolean>>() {

            @Override
            public TableCell<Livraison, Boolean> call(TableColumn<Livraison, Boolean> p) {
                return new LivraisonAdminController.ButtonCell();
            }
        
        });
        
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
			loader.setLocation(getClass().getResource("/fxml/livraisonAdminFile.fxml"));
			loader.setController(springContext.getBean(this.getClass()));
			return loader.load();
		} catch (IOException e) {
			System.err.println("can't load livraison");
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
