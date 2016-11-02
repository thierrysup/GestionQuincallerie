package com.transporteur.gestionquincallerie.software.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.transporteur.gestionquincallerie.software.MainApplication;
import static com.transporteur.gestionquincallerie.software.MainApplication.stage;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import com.transporteur.gestionquincallerie.software.entity.Employe;
import com.transporteur.gestionquincallerie.software.services.EmployeIService;
import com.transporteur.gestionquincallerie.software.services.impl.EmployeServiceImp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

//@Component
@Controller
public class AuthentificationController implements BootInitializable{

    private ApplicationContext springContext;
    
    public static Stage secondStage;

    @FXML
    private JFXPasswordField edtmdp;

    @FXML
    private JFXTextField edtlogin;

    @FXML
    private JFXButton btnconnexion;

    @FXML
    private JFXButton btnannuler;
    
    @Autowired
    private EmployeServiceImp empserv;
    
    @FXML
    void connexion(ActionEvent event) throws IOException {
//       String pass = String.valueOf(edtmdp.getText().hashCode());
//       if((!edtlogin.getText().isEmpty())&&(!edtmdp.getText().isEmpty())){
//           Employe emp = empserv.findEmployeByLogin(edtlogin.getText());
//           if((emp != null)&&(emp.getPassWord().equals(pass))){
//             acc.initView();
//           }
//       } now we are waitting Employe Controller and Fxml to introduise.
            if((edtlogin.getText().equals("root"))&&(edtmdp.getText().equals("root"))){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/accueil.fxml"));         
                secondStage = new Stage();
                //loader.setController(new AccueilController());
                stage.close();
                secondStage.setScene(new Scene((Parent) loader.load()));
                secondStage.show();
             
                System.out.println("cool connexion "+edtlogin.getText());
            }else{
                System.out.println("error of login or password "+edtlogin.getText());
            }
    }

    @FXML
    void annuler(ActionEvent event) {
        System.exit(0);
        //Platform.exit();
    }
    
    @FXML
    void home(ActionEvent event) {

    }

    @Override
    public void initConstruct() {
    }

    @Override
    public void stage(Stage primaryStage) {
    }

    @Override
    public Node initView(){
        try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/authentification.fxml"));
			//loader.setController(springContext.getBean(this.getClass()));
			return loader.load();
		} catch (IOException e) {
			System.err.println("can't load authentification");
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

