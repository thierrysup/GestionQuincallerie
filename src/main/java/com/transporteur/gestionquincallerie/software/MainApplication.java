package com.transporteur.gestionquincallerie.software;

import com.transporteur.gestionquincallerie.software.controllers.AccueilController;
import com.transporteur.gestionquincallerie.software.controllers.AuthentificationController;
import com.transporteur.gestionquincallerie.software.dao.ProduitIDao;
import com.transporteur.gestionquincallerie.software.entity.Employe;
import com.transporteur.gestionquincallerie.software.entity.Produit;
import com.transporteur.gestionquincallerie.software.entity.Role;
import com.transporteur.gestionquincallerie.software.services.impl.EmployeServiceImp;
import com.transporteur.gestionquincallerie.software.services.impl.FournisseurServiceImp;
import com.transporteur.gestionquincallerie.software.services.impl.ProduitServiceImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@Configuration 

@SpringBootApplication
public class MainApplication extends Application{
	
	private ApplicationContext springContext = null;
	
	private static String[] argument;
        
        public static Stage  stageM;
       
	@Override
	public void start(Stage primaryStage) throws Exception {
            
               this.stageM = primaryStage;
               
		Task<Object> task = new Task<Object>(){

			@Override
			protected Object call() throws Exception {
				springContext = SpringApplication.run(MainApplication.class, argument);	
				return null;
			}
			
		};
		task.setOnSucceeded(e -> {
			AuthentificationController controller = springContext.getBean(AuthentificationController.class);
			
			Parent parent = (Parent) controller.initView();
			Scene scene = new Scene(parent);
			
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("");
			primaryStage.show();
                        
		});
		task.setOnFailed(e -> {
			System.exit(0);
			Platform.exit();
		});
              
		task.run();
	}
        
        
	public static void main(String[] args) {
            
		MainApplication.argument = args;
		launch(args);
	}

}
