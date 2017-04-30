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
import com.transporteur.gestionquincallerie.software.entity.Fournisseur;
import com.transporteur.gestionquincallerie.software.services.FournisseurIService;
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
public class ApprovAdminController implements BootInitializable{

    private ApplicationContext springContext;
    
    public static Stage stageF;
    
    @FXML
    private BorderPane paneBStock;

    @FXML
    private JFXButton btnRecherche;

    @FXML
    private JFXButton btnImprimer;

    @FXML
    private TableView<Fournisseur> tableV;

    @FXML
    private TableColumn<Fournisseur, Long> idColumn;

    @FXML
    private TableColumn<Fournisseur, String> desigColumn;

    @FXML
    private TableColumn<Fournisseur, String> fourColumn;

    @FXML
    private TableColumn<Fournisseur, Date> dateColumn;

    @FXML
    private TableColumn<Fournisseur, String> adresColumn;

    @FXML
    private TableColumn<Fournisseur, Float> prixColumn;

    @FXML
    private TableColumn<Fournisseur, Integer> qteColumn;

    @FXML
    private TableColumn actionColumn;

    @FXML
    private JFXTextField edtQMinProduit;

    @FXML
    private JFXTextField edtQMaxProduit;

    @FXML
    private JFXTextField edtNomFournisseur;

   @FXML
    private JFXDatePicker edtTo;

    @FXML
    private JFXDatePicker edtFrom;

    @FXML
    private ImageView ibtn;
    
    @Autowired
    private FournisseurIService fServ;
    
    @Autowired
    private AccueilController accueil;
    
     private ObservableList<Fournisseur> fournisseurData = FXCollections.observableArrayList();
    
    private ObservableList<Fournisseur> printList = FXCollections.observableArrayList();
    
    private Fournisseur four;
    
    private void clearFields(){ 
        edtQMinProduit.setText("");
        edtQMinProduit.setPromptText("Quantité Minimale");
        edtQMaxProduit.setText("");
        edtQMaxProduit.setPromptText("Quantité Maximale");
        edtNomFournisseur.setText("");
        edtNomFournisseur.setPromptText("Nom Fournisseur");  
        edtTo.setValue(null);
        edtTo.setPromptText("To");
        edtFrom.setValue(null);
        edtFrom.setPromptText("From");
    }

    public void setLoyoutAdminFournisseur(Node node){
        this.paneBStock.setCenter(node);
        this.paneBStock.setTop(null);
        this.paneBStock.autosize();
    }
    
    public void loading(){    
          printList.clear();
          tableV.getItems().clear();
          printList.addAll(fServ.findAllFournisseur()) ; 
          tableV.getItems().addAll(printList);
          loadTableView();
    }
    
    public void loadFournisseurEdit(String text)throws Exception{
                        stageF = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+text+".fxml"));
                        Parent root =loader.load();
                        EditApprovAdminController ctrls =loader.getController();
                        ctrls.setfServ(fServ);
                        ctrls.setFour(four);
                        ctrls.initTemplate(); 
                        ctrls.setFcont(this);
                        Scene sc = new Scene(root);
                        stageF.setScene(sc);
                        stageF.setResizable(false);
                        stageF.setTitle(text);
                        stageF.initModality((Modality.APPLICATION_MODAL));
                        stageF.show();
                        
                       
    }
    
     private class ButtonCell extends TableCell<Fournisseur, Boolean> {
        SplitMenuButton cellButton = new SplitMenuButton(new MenuItem("DELETE"),new MenuItem("EDITION"));
        
        
                       
      //  final Button edtButton = new Button("Edition");
        
        ButtonCell(){
            
//        	//Action when the button is pressed
            cellButton.setText("OPERATION");
            
            
             cellButton.getItems().get(0).setOnAction(e -> {
                try {
                    Fournisseur currentFournisseur = (Fournisseur) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                        fServ.deleteFournisseurById(currentFournisseur);
                	printList.remove(currentFournisseur);
                        loading();
                } catch (Exception ex) {
                    Logger.getLogger(ApprovAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            cellButton.getItems().get(1).setOnAction(e -> {
                try {
                     Fournisseur currentFournisseur = (Fournisseur) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                        //remove selected item from the table list
                       four=currentFournisseur; 
                       loadFournisseurEdit("editApprov");
                       // loading();
                       
                } catch (Exception ex) {
                    Logger.getLogger(ApprovAdminController.class.getName()).log(Level.SEVERE, null, ex);
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

    
     private void loadTableView(){
//       printList.add(livr);
//       tableV.getItems().add(livr);
    
       idColumn.setCellValueFactory(new PropertyValueFactory<Fournisseur,Long>("Id"));
       desigColumn.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("designation"));
       qteColumn.setCellValueFactory(new PropertyValueFactory<Fournisseur,Integer>("qte"));
       fourColumn.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("nomFourn"));
       adresColumn.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("adresseFour"));
       dateColumn.setCellValueFactory(new PropertyValueFactory<Fournisseur,Date>("dateFour"));
       prixColumn.setCellValueFactory(new PropertyValueFactory<Fournisseur,Float>("prixAchat"));
       
        actionColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Fournisseur, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Fournisseur, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        actionColumn.setCellFactory(
                new Callback<TableColumn<Fournisseur, Boolean>, TableCell<Fournisseur, Boolean>>() {

            @Override
            public TableCell<Fournisseur, Boolean> call(TableColumn<Fournisseur, Boolean> p) {
                return new ApprovAdminController.ButtonCell();
            }
        
        });
        
    }
    
    @FXML
    void home(ActionEvent event) {
        setLoyoutAdminFournisseur(accueil.initView());
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
           if((edtTo.getValue() != null)&&(edtFrom.getValue() != null)){
             printList.addAll(fServ.findFournisseurtByCriteria(Integer.parseInt(edtQMinProduit.getText()), Integer.parseInt(edtQMaxProduit.getText()), edtNomFournisseur.getText(),Date.from(edtTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(edtFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
           }else{
           if((edtTo.getValue() == null)&&(edtFrom.getValue() != null)){
                printList.addAll(fServ.findFournisseurtByCriteria(Integer.parseInt(edtQMinProduit.getText()), Integer.parseInt(edtQMaxProduit.getText()), edtNomFournisseur.getText(),null, Date.from(edtFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
           }else{
               if((edtTo.getValue() != null)&&(edtFrom.getValue() == null))
                printList.addAll(fServ.findFournisseurtByCriteria(Integer.parseInt(edtQMinProduit.getText()), Integer.parseInt(edtQMaxProduit.getText()), edtNomFournisseur.getText(),Date.from(edtTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),null));
               else
                printList.addAll(fServ.findFournisseurtByCriteria(Integer.parseInt(edtQMinProduit.getText()), Integer.parseInt(edtQMaxProduit.getText()), edtNomFournisseur.getText(),null,null));
           }        
           }
          }else{
            if((edtQMinProduit.getText().length() != 0)&&(edtQMaxProduit.getText().length() == 0)){

                if((edtTo.getValue() != null)&&(edtFrom.getValue() != null)){
                 printList.addAll(fServ.findFournisseurtByCriteria(Integer.parseInt(edtQMinProduit.getText()), 0, edtNomFournisseur.getText(),Date.from(edtTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(edtFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
               }else{
               if((edtTo.getValue() == null)&&(edtFrom.getValue() != null)){
                    printList.addAll(fServ.findFournisseurtByCriteria(Integer.parseInt(edtQMinProduit.getText()), 0, edtNomFournisseur.getText(),null, Date.from(edtFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
               }else{
                   if((edtTo.getValue() != null)&&(edtFrom.getValue() == null))
                    printList.addAll(fServ.findFournisseurtByCriteria(Integer.parseInt(edtQMinProduit.getText()),0, edtNomFournisseur.getText(),Date.from(edtTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),null));
                   else
                    printList.addAll(fServ.findFournisseurtByCriteria(Integer.parseInt(edtQMinProduit.getText()), 0, edtNomFournisseur.getText(),null,null));
               }

               }
            }else{
               if((edtQMinProduit.getText().length() == 0)&&(edtQMaxProduit.getText().length() != 0)){                   
                    if((edtTo.getValue() != null)&&(edtFrom.getValue() != null)){
                     printList.addAll(fServ.findFournisseurtByCriteria(0,Integer.parseInt(edtQMaxProduit.getText()), edtNomFournisseur.getText(),Date.from(edtTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(edtFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                   }else{
                   if((edtTo.getValue() == null)&&(edtFrom.getValue() != null)){
                        printList.addAll(fServ.findFournisseurtByCriteria(0,Integer.parseInt(edtQMaxProduit.getText()), edtNomFournisseur.getText(),null, Date.from(edtFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                   }else{
                       if((edtTo.getValue() != null)&&(edtFrom.getValue() == null))
                        printList.addAll(fServ.findFournisseurtByCriteria(0,Integer.parseInt(edtQMaxProduit.getText()), edtNomFournisseur.getText(),Date.from(edtTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),null));
                       else
                        printList.addAll(fServ.findFournisseurtByCriteria(0,Integer.parseInt(edtQMaxProduit.getText()), edtNomFournisseur.getText(),null,null));
                   }

                   }     
               }else{
                   System.out.println("test Null "+edtNomFournisseur.getText());
                if((edtTo.getValue() != null)&&(edtFrom.getValue() != null)){
                     printList.addAll(fServ.findFournisseurtByCriteria(0,0, edtNomFournisseur.getText(),Date.from(edtTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(edtFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                   }else{
                   if((edtTo.getValue() == null)&&(edtFrom.getValue() != null)){
                        printList.addAll(fServ.findFournisseurtByCriteria(0,0, edtNomFournisseur.getText(),null, Date.from(edtFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                   }else{
                       if((edtTo.getValue() != null)&&(edtFrom.getValue() == null)){
                        printList.addAll(fServ.findFournisseurtByCriteria(0,0, edtNomFournisseur.getText(),Date.from(edtTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),null));
                       }else{
                           System.out.println("test Null "+edtNomFournisseur.getText());
                        printList.addAll(fServ.findFournisseurtByCriteria(0,0, edtNomFournisseur.getText(),null,null));
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
			loader.setLocation(getClass().getResource("/fxml/approvAdmin.fxml"));
			loader.setController(springContext.getBean(this.getClass()));
			return loader.load();
		} catch (IOException e) {
			System.err.println("can't load fournisseur");
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
        this.springContext =ac;
    }
    
}
