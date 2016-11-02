package com.transporteur.gestionquincallerie.software.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import static com.transporteur.gestionquincallerie.software.controllers.AccueilController.accueilStage;
import static com.transporteur.gestionquincallerie.software.controllers.AuthentificationController.secondStage;
import com.transporteur.gestionquincallerie.software.entity.Fournisseur;
import com.transporteur.gestionquincallerie.software.entity.Produit;
import com.transporteur.gestionquincallerie.software.services.impl.FournisseurServiceImp;
import com.transporteur.gestionquincallerie.software.services.impl.ProduitServiceImp;
import java.io.IOException;
import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;
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
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
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
    private ProduitServiceImp pServ ;
    @Autowired
    private FournisseurServiceImp fServ ;
    
    private ObservableList<Fournisseur> fournisseursData = FXCollections.observableArrayList();
    
    private ObservableList<Produit> produitsData = FXCollections.observableArrayList();



    @FXML
    void genererLaFiche(ActionEvent event) {

    }

    @FXML
    void valider(ActionEvent event) {

    }

    @Override
    public void initConstruct() {
       
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
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/accueil.fxml"));         
                secondStage = new Stage();
                //loader.setController(new AccueilController());
                accueilStage.close();
                secondStage.setScene(new Scene((Parent) loader.load()));
                secondStage.show();

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
    
    public ObservableList<String> loadNameProd(ObservableList<Produit> list){
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
    
    public void loading(){
        System.out.println("  fournisseur : "+ fServ.toString().toLowerCase());
  //     System.out.println("produit : "+pServ.findAllProduit().size());
//        fournisseursData.addAll(fServ.findAllFournisseur());
//        produitsData.addAll(pServ.findAllProduit());
//        //tableV.setItems(fournisseursData);
//        cbxFournisseur.setItems(loadNameFour(fournisseursData));
//        cbxProduit.setItems(loadNameProd(produitsData));
    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.pServ = new ProduitServiceImp();
        this.fServ = new FournisseurServiceImp();
           loading();
          Produit p =new Produit();
          p.setNom("couscous");
          p.setPrixVente(2500);
          p.setQte(50);
          p.setStatus(true);
            System.out.println(" le tetu fournisseur : "+ pServ.createProduit(p));
        
   
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.springContext = ac;
    }
}

