package com.transporteur.gestionquincallerie.software.config;

import org.springframework.context.ApplicationContextAware;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

public interface BootInitializable extends Initializable, ApplicationContextAware {
	
	public void initConstruct();
	
	public void stage(Stage primaryStage);
	
	public Node initView();

}
