package com.transporteur.gestionquincallerie.software.controllers;


import com.jfoenix.controls.JFXButton;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Component
public class AccueilController implements BootInitializable{

    
    
    private ApplicationContext springContext;
    
   
    
  @FXML
    private ImageView imgLogo;
  
  @FXML
    private BorderPane paneBorder;

    @FXML
    private JFXButton btnApprov;

    @FXML
    private JFXButton btnAide;

    @FXML
    private JFXButton btnLivraison;

    @FXML
    private JFXButton btnStock;

    @FXML
    private JFXButton btnAprpos;

    @FXML
    private Label lblSlogan;
    
    @Autowired
    private ApprovController approv;
    @Autowired
    private LivraisonController livra;
    @Autowired
    private StockController stock;

    public BorderPane getPaneBorder() {
        return paneBorder;
    }

    public void setPaneBorder(BorderPane paneBorder) {
        this.paneBorder = paneBorder;
    }

   
    
    public void setCenterLayout(Node node) {
		this.paneBorder.setCenter(node);
		this.paneBorder.autosize();
	}
    
    @FXML
    void livraison(ActionEvent event) throws IOException {
        setCenterLayout(livra.initView());
        livra.initConstruct();
    }
     @FXML
    void approvisioner(ActionEvent event) throws IOException {
         setCenterLayout(approv.initView());
         approv.initConstruct();
    }
    //fx:controller="com.transporteur.gestionquincallerie.software.controllers.AccueilController"
    @FXML
    void stock(ActionEvent event) throws IOException {
        setCenterLayout(stock.initView());
        stock.initConstruct();
    }

    @FXML
    void aPropos(ActionEvent event) {

    }

    @FXML
    void aide(ActionEvent event) {
        System.out.println("cool les gars");
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
			loader.setLocation(getClass().getResource("/fxml/accueil.fxml"));
			loader.setController(springContext.getBean(this.getClass()));
			return loader.load();
		} catch (IOException e) {
			System.err.println("can't load accueil");
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

