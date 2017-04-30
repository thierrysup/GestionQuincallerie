package com.transporteur.gestionquincallerie.software.controllers;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import com.transporteur.gestionquincallerie.software.entity.Fournisseur;
import com.transporteur.gestionquincallerie.software.entity.Livraison;
import com.transporteur.gestionquincallerie.software.entity.Produit;
import com.transporteur.gestionquincallerie.software.services.impl.LivraisonServiceImp;
import com.transporteur.gestionquincallerie.software.services.impl.ProduitServiceImp;
import java.io.IOException;
import java.net.URL;
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

/**
 *
 * @author thierry
 */

@Component
public class LivraisonController implements BootInitializable{

    private ApplicationContext springContext;
    
    @FXML
    private JFXTextField edtNom;
    
     @FXML
    private TableView<Livraison> tableV;
     
    @FXML
    private TableColumn<Livraison,String> design;

    @FXML
    private TableColumn<Livraison,Integer> qte;

    @FXML
    private TableColumn<Livraison,String> nomClient;
    
    @FXML
    private TableColumn<Livraison,String>  adrColumn;

    @FXML
    private JFXTextField edtAdresse;

    @FXML
    private JFXComboBox<String> cbxClient;

    @FXML
    private JFXComboBox<String> cbxProduit;
    
    @FXML
    private JFXComboBox<String> cbxClientAdresse;


    @FXML
    private JFXButton btnImprimer;

    @FXML
    private JFXTextField edtQte;

    @FXML
    private JFXCheckBox chxautre;
    
    @FXML
    private BorderPane paneBLivr;

    @FXML
    private JFXButton btnValider;
    
    @Autowired
    private AccueilController accueil;

    @Autowired
    private ProduitServiceImp pServ ;
   
    @Autowired
    private LivraisonServiceImp lServ;
    
    private Livraison livr;
    
    private ObservableList<Livraison> livraisonsData = FXCollections.observableArrayList();
    
    private ObservableList<Produit> produitsData = FXCollections.observableArrayList();
    
    private ObservableList<Livraison> printList = FXCollections.observableArrayList();
    

    
    private void clearFields(){
        
        chxautre.setSelected(false);
        edtNom.setDisable(true);
        edtNom.setText("");
        edtNom.setPromptText("Nom client");
        
        edtAdresse.setDisable(true);
        edtAdresse.setText("");
        edtAdresse.setPromptText("Adresse");
        
        cbxClient.setDisable(false);
        cbxClient.setPromptText("Liste des clients");
        
        cbxClientAdresse.setDisable(false);
        cbxClientAdresse.setPromptText("Liste adresses clients");
        
        cbxProduit.setPromptText("Liste des produits");
        
        edtQte.setText("");
        edtQte.setPromptText("Quantité");
    }
    
    private ObservableList<String> loadNameLivr(ObservableList<Livraison> list){
        int i = 0, j = 0;
        boolean insert =true;
        List<String> tab = new  ArrayList<String>() ;
          while (i<list.size()) {     
             for(j=0;(j< i)&&(j<tab.size());j++){
              if(tab.get(j).equals(list.get(i).getNomClient())){
                 insert = false;
                 break;
              }else{
                 insert =true;
              }
           }
            
           if(i== 0 || insert == true){
            tab.add(list.get(i).getNomClient());
           }
            i++;
        }
        ObservableList<String> result = FXCollections.observableArrayList();
          result.addAll(tab);
          return result;
    }
    
    private ObservableList<String> loadAdresseLivr(ObservableList<Livraison> list){
        int i = 0,j=0;
        boolean insert = true;
        List<String> tab = new  ArrayList<String>() ;
          while (i<list.size()) {
            for(j=0;(j< i)&&(j<tab.size());j++){
              if(tab.get(j).equals(list.get(i).getAdresseClient())){
                 insert = false;
                 break;
              }else{
                 insert =true;
              }
           }
            
           if(i== 0 || insert == true){
            tab.add(list.get(i).getAdresseClient());
           }
            i++;
        }
        ObservableList<String> result = FXCollections.observableArrayList();
          result.addAll(tab);
          return result;
    }
    
    private ObservableList<String> loadNameProd(ObservableList<Produit> list){
        int i = 0,j=0;
        boolean insert = true;
        List<String> tab = new  ArrayList<String>() ;
          while (i<list.size()) {
              
            for(j=0;(j< i)&&(j<tab.size());j++){
              if(tab.get(j).equals(list.get(i).getNom())){
                 insert = false;
                 break;
              }else{
                 insert =true;
              }
           }
            
           if(i== 0 || insert == true){
             tab.add(list.get(i).getNom());
           }
            i++;
        }
        ObservableList<String> result = FXCollections.observableArrayList();
          result.addAll(tab);
          return result;
    }
    
    
    public void loading(){
        
       cbxProduit.setItems(null);
       cbxClient.setItems(null);
       cbxClientAdresse.setItems(null);
       livraisonsData.clear();
       produitsData.clear();
       
       livraisonsData.addAll(lServ.findAllLivraison());
       produitsData.addAll(pServ.findAllProduit());
       cbxClient.setItems(loadNameLivr(livraisonsData));
       cbxClientAdresse.setItems( loadAdresseLivr(livraisonsData));
       cbxProduit.setItems(loadNameProd(produitsData));
    }
    
    
    
    @FXML
    private void valider(ActionEvent event) {
        
        livr = new Livraison();
        livr.setDateLivre(new Date());
        livr.setDesignation(cbxProduit.getValue());
        livr.setQte(Integer.parseInt(edtQte.getText()));
        livr.setStatus(true);
        livr.setProduit(pServ.findProduitByName(String.valueOf(cbxProduit.getValue()))); 
        if(chxautre.isSelected()){
                   livr.setNomClient(edtNom.getText());
                   livr.setAdresseClient(edtAdresse.getText());                   
       }else{     
                   livr.setNomClient(cbxClient.getValue());
                   livr.setAdresseClient(cbxClientAdresse.getValue());         
        }
         lServ.createLivraison(livr);
              clearFields();
              loading();
              loadTableView();
        
    }
     public void setCenterLayoutLivr(Node node) {
        this.paneBLivr.setCenter(node);
        this.paneBLivr.setTop(null);
        this.paneBLivr.autosize();
    }
    
    @FXML
    void oClient(ActionEvent event) {
         if(!chxautre.isSelected()){
            edtNom.setDisable(true);
            edtNom.setText("");
            edtAdresse.setDisable(true);
            edtAdresse.setText("");
            cbxClient.setDisable(false);
            cbxClientAdresse.setDisable(false);
        }else{
            edtNom.setDisable(false);
            edtAdresse.setDisable(false);
            cbxClient.setPromptText("Liste des clients");
            cbxClient.setDisable(true);
            cbxClientAdresse.setPromptText("Liste adresses clients");
            cbxClientAdresse.setDisable(true);
            
        }
    }
    
   // fx:controller="com.transporteur.gestionquincallerie.software.controllers.LivraisonController"
    
    @FXML
   private void home(ActionEvent event) throws IOException {
          setCenterLayoutLivr(accueil.initView());
          tableV.getItems().clear();
          tableV.setItems(null);
    }

    @FXML
    void imprimer(ActionEvent event) {

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
			loader.setLocation(getClass().getResource("/fxml/livraison.fxml"));
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
    
   private void loadTableView(){
       printList.add(livr);
       tableV.getItems().add(livr);
    
       design.setCellValueFactory(new PropertyValueFactory<Livraison,String>("designation"));
       qte.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("qte"));
       nomClient.setCellValueFactory(new PropertyValueFactory<Livraison,String>("nomClient"));
       adrColumn.setCellValueFactory(new PropertyValueFactory<Livraison,String>("adresseClient"));
        
    }

   

}

