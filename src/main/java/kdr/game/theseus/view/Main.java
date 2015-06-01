/*
 * The MIT License (MIT)
 * Copyright (c) 2015, KDR-Games
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package kdr.game.theseus.view;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kdr.game.theseus.GameController;
import kdr.game.theseus.Player;

public class Main extends Application {

	private Stage primaryStage;
	private GameController game;
    
    public static Logger logger = LoggerFactory.getLogger(Main.class);

	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("The path of Theseus");

        showLogin();
    }
    
	/**
	 * Shows the login window.
	 */
    public void showLogin() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fxml/TheseusLoginLayout.fxml"));
            BorderPane loginLayout = (BorderPane) loader.load();
            
            LoginViewController controller = loader.getController();
            game = new GameController(this);
            controller.setGameController(game);
            
            Scene scene = new Scene(loginLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.show();
            logger.info("Login screen set up and shown.");
            primaryStage.setOnCloseRequest((WindowEvent e)-> {
            	logger.info("Quit.");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the game window. A character needs to be set up properly for this.
     */
    public void showGame() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fxml/TheseusGameLayout.fxml"));
            BorderPane gameLayout = (BorderPane) loader.load();            

            GameViewController controller = loader.getController();
            controller.setGameController(game);
            
            Scene scene = new Scene(gameLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.show();
            logger.info("Game screen set up and shown.");
            primaryStage.setOnCloseRequest((WindowEvent e)-> {
            	Alert exitPrompt = new Alert(AlertType.CONFIRMATION);
        		exitPrompt.setTitle("Exit game");
        		exitPrompt.setHeaderText("Are you sure, you want to exit?");
        		exitPrompt.setContentText("This is a rouge-like game, your progress won't be saved.");
        		ButtonType buttonYes = new ButtonType("Yes");
        		ButtonType buttonNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        		exitPrompt.getButtonTypes().setAll(buttonYes, buttonNo);
        		
        		Optional<ButtonType> result = exitPrompt.showAndWait();
        		if(result.get() == buttonYes) {
        			Platform.exit();
        		}
            	logger.info("Quit.");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		logger.info("Application started.");
		launch(args);
	}
}
