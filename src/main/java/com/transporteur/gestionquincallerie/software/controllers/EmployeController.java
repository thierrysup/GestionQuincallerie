/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transporteur.gestionquincallerie.software.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.java.swing.plaf.windows.resources.windows;
import com.transporteur.gestionquincallerie.software.MainApplication;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import static com.transporteur.gestionquincallerie.software.controllers.AuthentificationController.emp;
import com.transporteur.gestionquincallerie.software.entity.Employe;
import com.transporteur.gestionquincallerie.software.entity.Role;
import com.transporteur.gestionquincallerie.software.services.impl.EmployeServiceImp;
import java.io.IOException;
import java.net.URL;
import java.util.AbstractList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
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
public class EmployeController implements BootInitializable{

    private ApplicationContext springContext;
    
    @FXML
    private BorderPane panCmpt;
    
    @FXML
    private TableView<Employe> tableV;

    @FXML
    private TableColumn<Employe, Long> idColumn;

    @FXML
    private TableColumn<Employe, String> nameColumn;

    @FXML
    private TableColumn<Employe, String> adressColumn;

    @FXML
    private TableColumn<Employe, String> roleColumn;
    
    @FXML
    private TableColumn<Employe,String> loginColumn;
    
    @FXML
    private TableColumn actionColumn;
    
    @FXML
    private JFXButton btnsearchCmpt;

     @FXML
    private JFXButton btnDelCompte;
    
    @FXML
    private JFXButton btnPrintCompte;

    @FXML
    private JFXTextField edtNomCmpt;

    @FXML
    private JFXComboBox<String> cbxCmpt;

    @FXML
    private JFXButton btnAddCompte;
    
    @Autowired
    public EmployeServiceImp eServ;
    
    @Autowired
    private AccueilController accueil;
    
    private ObservableList<Employe> employesData = FXCollections.observableArrayList();
    
    private ObservableList<String> roles = FXCollections.observableArrayList("USER","ADMIN");
    
    public ObservableList<Employe> printList = FXCollections.observableArrayList();
    
    public Employe empl;
    
    public static Stage stageS;
 
    private void clearFields(){ 
        edtNomCmpt.setText("");
        edtNomCmpt.setPromptText("Nom");
        cbxCmpt.setValue(null);
        cbxCmpt.setPromptText("ROLE"); 
       
    }
    
      
    @FXML
    void delete(ActionEvent event) {

    }
    
     public void loadEmployeSet(String text)throws Exception{
                        stageS = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+text+".fxml"));
                       // Parent root =FXMLLoader.load(getClass().getResource("/fxml/"+text+".fxml"));
                        Parent root =loader.load();
                        CreateEmployeController ctrl =loader.getController();
                        ctrl.seteServ(eServ);
                        ctrl.setCtrl(this);
                        Scene sc = new Scene(root);
                        stageS.setScene(sc);
                        stageS.setResizable(false);
                        stageS.setTitle(text);
                        stageS.initModality((Modality.APPLICATION_MODAL));
                        stageS.show();
                        
                       
    }
     public void loadEmployeEdit(String text)throws Exception{
                        stageS = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+text+".fxml"));
                       // Parent root =FXMLLoader.load(getClass().getResource("/fxml/"+text+".fxml"));
                        Parent root =loader.load();
                        EditEmployeController ctrls =loader.getController();
                        ctrls.seteServ(eServ);
                        ctrls.setEmployeSet(empl);
                        ctrls.initTemplate(); 
                        ctrls.setCtrl(this);
                        System.out.println(" tu est "+empl.getNomEmp());
                        
                        System.out.println(" tu est en sortie"+empl.getNomEmp());
                        Scene sc = new Scene(root);
                        stageS.setScene(sc);
                        stageS.setResizable(false);
                        stageS.setTitle(text);
                        stageS.initModality((Modality.APPLICATION_MODAL));
                        stageS.show();
                        
                       
    }
 
    
    @FXML
    void ajouter(ActionEvent event) throws Exception {
        loadEmployeSet("createCompte");

    }
    public void loading(){
        printList.clear();
        tableV.getItems().clear();
//        System.out.println("nom est petit  "+empl.getNomEmp());
        for (Employe employe : eServ.findAllEmploye()) {
             if(((employe.isStatus() == true)&&(employe.getId() !=emp.getId()))
                     )
                 printList.add(employe);
         }
         tableV.getItems().addAll(printList);
         loadTableView();
         cbxCmpt.setItems(roles);
    
    
    }
      private class ButtonCell extends TableCell<Employe, Boolean> {
        SplitMenuButton cellButton = new SplitMenuButton(new MenuItem("DELETE"),new MenuItem("EDITION"));
        
        
                       
      //  final Button edtButton = new Button("Edition");
        
        ButtonCell(){
            
//        	//Action when the button is pressed
            cellButton.setText("OPERATION");
            
            
             cellButton.getItems().get(0).setOnAction(e -> {
                try {
                    Employe currentEmploye = (Employe) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());                     
                        eServ.deleteEmployeById(currentEmploye);
                	printList.remove(currentEmploye);
                        loading();       
                } catch (Exception ex) {
                    Logger.getLogger(EmployeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            cellButton.getItems().get(1).setOnAction(e -> {
                try {
                     Employe currentEmploye = (Employe) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                         empl=currentEmploye;
                         System.out.println("coucouc "+currentEmploye.getNomEmp());
                        loadEmployeEdit("editCompte");
                } catch (Exception ex) {
                    Logger.getLogger(EmployeController.class.getName()).log(Level.SEVERE, null, ex);
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
       idColumn.setCellValueFactory(new PropertyValueFactory<Employe, Long>("Id"));
       nameColumn.setCellValueFactory(new PropertyValueFactory<Employe,String>("nomEmp"));
       adressColumn.setCellValueFactory(new PropertyValueFactory<Employe,String>("adresse"));
       roleColumn.setCellValueFactory(new PropertyValueFactory<Employe,String>("role"));
       loginColumn.setCellValueFactory(new PropertyValueFactory<Employe,String>("login"));
      
        actionColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Employe, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Employe, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        actionColumn.setCellFactory(
                new Callback<TableColumn<Employe, Boolean>, TableCell<Employe, Boolean>>() {

            @Override
            public TableCell<Employe, Boolean> call(TableColumn<Employe, Boolean> p) {
                return new ButtonCell();
            }
        
        });
       
    }
       public void setLoyoutCenterCmpt(Node node){
        this.panCmpt.setCenter(node);
        this.panCmpt.setTop(null);
        this.panCmpt.autosize();
    
    }

    @FXML
   private void home(ActionEvent event) {   
        setLoyoutCenterCmpt(accueil.initView());
         tableV.getItems().clear();
         tableV.setItems(null);
         printList.clear();
    }

    @FXML
    void imprimerCmpt(ActionEvent event) {

    }
  

    @FXML
    void searchCmpt(ActionEvent event) {
         printList.clear();
         if(cbxCmpt.getValue() == null){
           if(!edtNomCmpt.getText().isEmpty()){
               
               for (Employe employe : eServ.findEmployeByCriteria(edtNomCmpt.getText(), "")) {
                    if(((employe.isStatus() == true)&&(employe.getId() !=emp.getId()))
                            )
                        printList.add(employe);
                }
             //printList.addAll(eServ.findEmployeByCriteria(edtNomCmpt.getText(), "")) ; 
           }else{
               for (Employe employe : eServ.findAllEmploye()) {
                    if(((employe.isStatus() == true)&&(employe.getId() !=emp.getId()))
                            )
                        printList.add(employe);
                }
             //printList.addAll(eServ.findAllEmploye());
           }
         }else{
             for (Employe employe : eServ.findEmployeByCriteria(edtNomCmpt.getText(),cbxCmpt.getValue())) {
                    if(((employe.isStatus() == true)&&(employe.getId() !=emp.getId()))
                            )
                        printList.add(employe);
                }
          // printList.addAll(eServ.findEmployeByCriteria(edtNomCmpt.getText(),cbxCmpt.getValue())) ;
         }
         clearFields();
         tableV.getItems().clear();
         tableV.getItems().addAll(printList);
         loadTableView();
        
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
			loader.setLocation(getClass().getResource("/fxml/comptes.fxml"));
			loader.setController(springContext.getBean(this.getClass()));
			return loader.load();
                       // fx:controller="com.transporteur.gestionquincallerie.software.controllers.EmployeController"
		} catch (IOException e) {
			System.err.println("can't load stock");
			e.printStackTrace();
			return null;
		}
    
    }
    private void actionView(){
          btnDelCompte.setOnAction(e -> {
            Employe selectedItem = tableV.getSelectionModel().getSelectedItem();
            eServ.deleteEmployeById(selectedItem);
            tableV.getItems().remove(selectedItem);
         });
    
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
