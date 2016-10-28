package com.transporteur.gestionquincallerie.software;

import com.transporteur.gestionquincallerie.software.controllers.AuthentificationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class MainApplication extends Application{
	
	private ApplicationContext springContext = null;
	
	private static String[] argument;	
        
        private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
            
            
            stage =primaryStage;
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
			primaryStage.setTitle("Login");
			primaryStage.show();
		});
		task.setOnFailed(e -> {
			System.exit(0);
			Platform.exit();
		});
		task.run();
	}

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

        
        
	public static void main(String[] args) {
		MainApplication.argument = args;
		launch(args);
	}

}
