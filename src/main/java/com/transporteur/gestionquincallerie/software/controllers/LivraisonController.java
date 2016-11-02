package com.transporteur.gestionquincallerie.software.controllers;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import static com.transporteur.gestionquincallerie.software.controllers.AccueilController.accueilStage;
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
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

//@Component
@Controller
public class LivraisonController implements BootInitializable{

    private ApplicationContext springContext;
    
    @FXML
    private JFXTextField edtNom;

    @FXML
    private JFXTextField edtAdresse;

    @FXML
    private JFXComboBox<?> cbxClient;

    @FXML
    private JFXComboBox<?> cbxProduit;

    @FXML
    private JFXButton btnImprimer;

    @FXML
    private JFXTextField edtQte;

    @FXML
    private JFXCheckBox chxautre;

    @FXML
    private JFXButton btnValider;


    @FXML
    void valider(ActionEvent event) {

    }
    
    @FXML
    void oClient(ActionEvent event) {
         if(!chxautre.isSelected()){
            edtNom.setDisable(true);
            edtNom.setText("");
            edtAdresse.setDisable(true);
            edtAdresse.setText("");
            cbxClient.setDisable(false);
        }else{
            edtNom.setDisable(false);
            edtAdresse.setDisable(false);
            cbxClient.setDisable(true);
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
			//loader.setController(springContext.getBean(this.getClass()));
			return loader.load();
		} catch (IOException e) {
			System.err.println("can't load livraison");
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

