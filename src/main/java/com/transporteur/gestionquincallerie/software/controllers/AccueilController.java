package com.transporteur.gestionquincallerie.software.controllers;


import com.jfoenix.controls.JFXButton;
import com.transporteur.gestionquincallerie.software.config.BootInitializable;
import static com.transporteur.gestionquincallerie.software.controllers.AuthentificationController.emp;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
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
    
    public static Stage stage ;
    
    @FXML
    private SplitMenuButton sMBConfig;

    @FXML
    private Label labelUser;
    
    @Autowired
    private EmployeServiceImp eServ;
    
    @Autowired
    private ApprovController approv;
    
    @Autowired
    private LivraisonController livra;
    @Autowired
    private AuthentificationController auth;
   
    @Autowired
    private StockController stock;
    
    @Autowired
    private StockAdminController stockAdmin;
    
    @Autowired
    private ApprovAdminController approvAdmin;
    
    @Autowired
    private LivraisonAdminController livraisonAdmin;
    
    
    @Autowired
    private EmployeController cmp;

    public static String op;
    
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
    void paramConfig(ActionEvent event) {
          
    }
    public void addOptionAdminSelect(){
        
          sMBConfig.getItems().add(new MenuItem("Fourniseur"));
          sMBConfig.getItems().add(new MenuItem("Livraison"));
          sMBConfig.getItems().add(new MenuItem("Produit"));
          sMBConfig.getItems().add(new MenuItem("Employe"));
        
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
    public void loadOption(String text)throws Exception{
                        stage = new Stage();
                        Parent root =FXMLLoader.load(getClass().getResource("/fxml/"+text+".fxml"));
                        Scene sc = new Scene(root);                       
                        stage.setScene(sc);
                        stage.setResizable(false);
                        stage.setTitle(text);
                        stage.show();
    }
    public void loadConfig(String text)throws Exception{
                        stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+text+".fxml"));
                       // Parent root =FXMLLoader.load(getClass().getResource("/fxml/"+text+".fxml"));
                        Parent root =loader.load();
                        ConfigurationController ctrl =loader.getController();
                        ctrl.seteServ(eServ);
                        ctrl.setUser(emp);
                        ctrl.templateConfig();
                        ctrl.setCtrl(this);
                        Scene sc = new Scene(root);
                        stage.setScene(sc);
                        stage.setResizable(false);
                        stage.setTitle(text);
                        stage.initModality((Modality.APPLICATION_MODAL));
                        stage.show();
    }
 
    public void loadLogout(){
         setCenterLayout(auth.initView());
         auth.initConstruct();
    }  
            
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        labelUser.setText("Welcome :"+emp.getNomEmp());
             sMBConfig.getItems().get(0).setOnAction(e -> {
                try {
                    loadConfig("configuration");
                } catch (Exception ex) {
                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            sMBConfig.getItems().get(1).setOnAction(e -> {
                loadLogout();
            });
            if(String.valueOf(emp.getRole()).contains("ADMIN")){
            addOptionAdminSelect();
            sMBConfig.getItems().get(2).setOnAction(e -> {
                try {
                    setCenterLayout(approvAdmin.initView());
                } catch (Exception ex) {
                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            sMBConfig.getItems().get(3).setOnAction(e -> {
                try {
                    setCenterLayout(livraisonAdmin.initView());
                } catch (Exception ex) {
                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            sMBConfig.getItems().get(4).setOnAction(e -> {
               try {
                    setCenterLayout(stockAdmin.initView());
                } catch (Exception ex) {
                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            sMBConfig.getItems().get(5).setOnAction(e -> {
                setCenterLayout(cmp.initView());
            });
         }
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.springContext = ac;
    }

}

