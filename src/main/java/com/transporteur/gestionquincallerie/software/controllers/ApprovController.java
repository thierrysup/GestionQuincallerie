package com.transporteur.gestionquincallerie.software.controllers;

import com.douwe.generic.dao.DataAccessException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import com.transporteur.gestionquincallerie.software.entity.Fournisseur;
import com.transporteur.gestionquincallerie.software.entity.Produit;
import com.transporteur.gestionquincallerie.software.services.impl.FournisseurServiceImp;
import com.transporteur.gestionquincallerie.software.services.impl.ProduitServiceImp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private TableColumn<Fournisseur, Float> prixAchat;


    @FXML
    private TableColumn<Fournisseur, String> fourni;
    
    public static final String DEST = System.currentTimeMillis()+"ficheApprovisionnement.pdf";

     
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
    private JFXComboBox<String> cbxAdresseFournisseur;

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
     
    @FXML
    void genererLaFiche(ActionEvent event) throws DocumentException, FileNotFoundException{
        File fichier = new File(DEST);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fichier));
        document.open();
        //Paragraph p = new Paragraph();
        String date = String.valueOf(new Date());
        Paragraph p1 = new Paragraph("Fiche d'approvisionnemt du "+date);
       
        p1.setAlignment(Element.ALIGN_CENTER);
        Font font = new Font(Font.FontFamily.HELVETICA,Float.valueOf(25));
        p1.setFont(font);
        
        PdfPTable table = new PdfPTable(5);
        table.addCell(new Phrase("Designation"));
        table.addCell(new Phrase("Quantite"));
        table.addCell(new Phrase("Prix unité"));
        table.addCell(new Phrase("Prix d'achat"));
        table.addCell(new Phrase("Fournisseur"));
        table.addCell(new Phrase("Adresse fournisseur"));
        ObservableList<Fournisseur> listeApprovs = this.printList;
        for(Fournisseur fournisseur: listeApprovs){
            table.addCell(new Phrase(fournisseur.getDesignation()));
            table.addCell(new Phrase(String.valueOf(fournisseur.getQte())));
            table.addCell(new Phrase(String.valueOf(fournisseur.getPrixUnitaire())));
            table.addCell(new Phrase(String.valueOf(fournisseur.getPrixUnitaire()*fournisseur.getQte())));
            table.addCell(new Phrase(fournisseur.getNomFourn()));
            table.addCell(new Phrase(fournisseur.getAdresseFour()));
        }
        document.addTitle("Approvisionnement");
        
        
//        p.setAlignment(Element.ALIGN_TOP);
//        p.setFont(font);
//        p.add(date);
//        document.add(p);
        document.add(p1);
        document.add(new Paragraph("\n"));
        document.add(table);
        document.close();    
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
        edtPrixAhat.setPromptText("Prix d'unité");
        edtProduit.setText("");
        edtProduit.setPromptText("Nom du produit");
        edtProduit.setDisable(true);
        edtQteProduit.setText("");
        edtQteProduit.setPromptText("Quantite du produit");
        cbxFournisseur.setDisable(false);
        cbxFournisseur.setPromptText("Liste des fournisseurs");
        cbxAdresseFournisseur.setDisable(false);
        cbxAdresseFournisseur.setPromptText("Adresse fournisseur");
        cbxProduit.setDisable(false);
        cbxProduit.setPromptText("Listes des produits");

    }

    @FXML
    private void valider(ActionEvent event) {
       if(chxPlusProduit.isSelected()){
           if(chxfour.isSelected()){
             fo = new Fournisseur();
                   fo.setAdresseFour(edtAddrFournisseur.getText());
                   fo.setDesignation(edtProduit.getText());
                   fo.setNomFourn(adtNomFournisseur.getText());
                   fo.setDateFour(new Date());
                   fo.setPrixUnitaire(Float.parseFloat(edtPrixAhat.getText()));
                   fo.setQte(Integer.parseInt(edtQteProduit.getText()));
                   fo.setStatus(true);
              Produit p = new Produit();
                   p.setNom(edtProduit.getText());
                   p.setQte(0);
                   p.setStatus(true);
              Produit pr =pServ.createProduit(p);
                   fo.setProduit(pr);
            }else{
             fo = new Fournisseur();
                   fo.setAdresseFour(cbxAdresseFournisseur.getValue());
                   fo.setDesignation(edtProduit.getText());
                   fo.setNomFourn(cbxFournisseur.getValue());
                   fo.setDateFour(new Date());
                   fo.setPrixUnitaire(Float.parseFloat(edtPrixAhat.getText()));
                   fo.setQte(Integer.parseInt(edtQteProduit.getText()));
                   fo.setStatus(true);
              Produit p = new Produit();
                   p.setNom(edtProduit.getText());
                   p.setQte(0);
                   p.setStatus(true);
              Produit pr =pServ.createProduit(p);
                   fo.setProduit(pr);
            }
       }else{
            if(chxfour.isSelected()){
             fo = new Fournisseur();
                   fo.setAdresseFour(edtAddrFournisseur.getText());
                   fo.setDesignation(cbxProduit.getValue());
                   fo.setNomFourn(adtNomFournisseur.getText());
                   fo.setDateFour(new Date());
                   
                   fo.setPrixUnitaire(Float.parseFloat(edtPrixAhat.getText()));
                   fo.setQte(Integer.parseInt(edtQteProduit.getText()));
                   fo.setStatus(true);
                   fo.setProduit(pServ.findProduitByName(cbxProduit.getValue()));
            }else{
             fo = new Fournisseur();
                   fo.setAdresseFour(cbxAdresseFournisseur.getValue());
                   fo.setDesignation(cbxProduit.getValue());
                   fo.setNomFourn(cbxFournisseur.getValue());
                   fo.setDateFour(new Date());
                   fo.setPrixUnitaire(Float.parseFloat(edtPrixAhat.getText()));
                   fo.setQte(Integer.parseInt(edtQteProduit.getText()));
                   fo.setStatus(true);
                   fo.setProduit(pServ.findProduitByName(cbxProduit.getValue()));
            } 
       }
        fServ.createFournisseur(fo);
              clearFields();
              loading();
              loadTableView();
    }
    
    public void loadTableView(){
       printList.add(fo);
       tableV.getItems().add(fo);
       idColum.setCellValueFactory(new PropertyValueFactory<Fournisseur, Long>("Id"));
       designation.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("designation"));
       qte.setCellValueFactory(new PropertyValueFactory<Fournisseur,Integer>("qte"));
       prix.setCellValueFactory(new PropertyValueFactory<Fournisseur,Float>("prix"));
       prixAchat.setCellValueFactory(new PropertyValueFactory<Fournisseur,Float>("prixAchat"));
       fourni.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("nomFourn"));
    }

    @Override
    public void initConstruct() {
       tableV.getItems().clear();
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
            cbxFournisseur.setPromptText("Liste des fournisseurs");
            cbxAdresseFournisseur.setDisable(false);
            cbxAdresseFournisseur.setPromptText("Adresse fournisseur");
        }else{
           edtAddrFournisseur.setDisable(false);
           adtNomFournisseur.setDisable(false);;
           cbxFournisseur.setPromptText("Liste des fournisseurs");
           cbxFournisseur.setDisable(true);
           cbxAdresseFournisseur.setPromptText("Adresse fournisseur");
           cbxAdresseFournisseur.setDisable(true);
          // cbxFournisseur.setValue(null);
        }
    }
    
    @FXML
    void oProduit(ActionEvent event) {
        if(!chxPlusProduit.isSelected()){
            edtProduit.setDisable(true);
            edtProduit.setPromptText("Liste des Produits");
            cbxProduit.setDisable(false);
        }else{
            edtProduit.setDisable(false);
            cbxProduit.setPromptText("Liste des Produits");
            cbxProduit.setDisable(true);
            
           // cbxProduit.setValue(null);
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
        int i = 0,j=0;
        boolean insert =true;
        List<String> tab = new  ArrayList<String>() ;
          while (i<list.size()) {
           for(j=0;(j< i)&&(j<tab.size());j++){
              if(tab.get(j).equals(list.get(i).getNomFourn())){
                 insert = false;
                 break;
              }else{
                 insert =true;
              }
           }
           if(i== 0 || insert == true){
            tab.add(list.get(i).getNomFourn()); 
           }
            i++;
        }
        ObservableList<String> result = FXCollections.observableArrayList();
          result.addAll(tab);
          return result;
    }
    
    public ObservableList<String> loadAdressFour(ObservableList<Fournisseur> list){
        int i = 0,j=0;
        boolean insert = true;
        List<String> tab = new  ArrayList<String>() ;
          while (i<list.size()) {
            for(j=0;(j< i)&&(j<tab.size());j++){
              if(tab.get(j).equals(list.get(i).getAdresseFour())){
                 insert = false;
                 break;
              }else{
                 insert =true;
              }
           }
            
           if(i== 0 || insert == true){
            tab.add(list.get(i).getAdresseFour());
           }
            
            i++;
        }
        ObservableList<String> result = FXCollections.observableArrayList();
          result.addAll(tab);
          return result;
    }
    
    private ObservableList<String> loadNameProd(ObservableList<Produit> list){
        int i = 0,j=0;
        boolean insert=true;
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
    
    private void loading(){
        
       cbxFournisseur.setItems(null);
       cbxProduit.setItems(null);
       cbxAdresseFournisseur.setItems(null);
       fournisseursData.clear();
       produitsData.clear();
       
       fournisseursData.addAll(fServ.findAllFournisseur());
       produitsData.addAll(pServ.findAllProduit());
       cbxFournisseur.setItems(loadNameFour(fournisseursData));
       cbxAdresseFournisseur.setItems(loadAdressFour(fournisseursData));
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

