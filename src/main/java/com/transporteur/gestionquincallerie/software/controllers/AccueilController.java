package com.transporteur.gestionquincallerie.software.controllers;


import com.jfoenix.controls.JFXButton;
import static com.transporteur.gestionquincallerie.software.MainApplication.stage;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import static com.transporteur.gestionquincallerie.software.controllers.AuthentificationController.secondStage;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


//@Component
@Controller
public class AccueilController implements BootInitializable{

    
    
    private ApplicationContext springContext;
    
    public static Stage accueilStage;
    
  @FXML
    private ImageView imgLogo;

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

    @FXML
    void approvisioner(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/approvisionnement.fxml"));         
                accueilStage = new Stage();
            //    loader.setController(new ApprovController());
                secondStage.close();
                accueilStage.setScene(new Scene((Parent) loader.load()));
                accueilStage.show();
             
    }
    
    @FXML
    void livraison(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/livraison.fxml"));         
                accueilStage = new Stage();
            //    loader.setController(new ApprovController());
                secondStage.close();
                accueilStage.setScene(new Scene((Parent) loader.load()));
                accueilStage.show();
    }
    
    @FXML
    void stock(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/stock.fxml"));         
                accueilStage = new Stage();
            //    loader.setController(new ApprovController());
                secondStage.close();
                accueilStage.setScene(new Scene((Parent) loader.load()));
                accueilStage.show();

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
			//loader.setController(springContext.getBean(this.getClass()));
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

