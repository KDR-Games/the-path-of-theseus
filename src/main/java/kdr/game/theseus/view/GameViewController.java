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

import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import kdr.game.theseus.ObservableMap;
import kdr.game.theseus.model.Constants;
import kdr.game.theseus.model.ExitReachedException;
import kdr.game.theseus.model.Player;
import kdr.game.theseus.model.WorldMap;

public class GameViewController extends ViewController {

	private Button[][] buttons;
	private WorldMap world;
	private ObservableMap map;
	private Player player;
	private ArrayList<Button> inventoryBag;
	
	@FXML
	private TilePane pane;
	@FXML
	private ProgressBar healthProgressBar;
	@FXML
	private ProgressBar staminaProgressBar;
	@FXML
	private Label currentHealthLabel;
	@FXML 
	private Label currentStaminaLabel;
	@FXML
	private TextArea smallMessageTextArea;
	@FXML
	private TitledPane playerInformationContainer;
	@FXML
	private Label playerMaxHealthLabel;
	@FXML
	private Label playerStrengthLabel;
	@FXML
	private Label playerAgilityLabel;
	@FXML
	private Label playerEnduranceLabel;
	@FXML
	private Label playerSlashingLabel;
	@FXML
	private Label playerPiercingLabel;
	@FXML
	private Label playerBluntLabel;
	@FXML
	private Label playerArmorLabel;
	@FXML
	private Label playerDamageLabel;
	@FXML
	private Label playerSpeedLabel;
	@FXML
	private Button playerMaxHealthButton;
	@FXML
	private Button playerStrengthButton;
	@FXML
	private Button playerAgilityButton;
	@FXML
	private Button playerEnduranceButton;
	@FXML
	private Button playerSlashingButton;
	@FXML
	private Button playerPiercingButton;
	@FXML
	private Button playerBluntButton;
	@FXML
	private TitledPane enemyInformationContainer;
	@FXML
	private Label enemyMaxHealthLabel;
	@FXML
	private Label enemyStrengthLabel;
	@FXML
	private Label enemyAgilityLabel;
	@FXML
	private Label enemyEnduranceLabel;
	@FXML
	private Label enemySlashingLabel;
	@FXML
	private Label enemyPiercingLabel;
	@FXML
	private Label enemyBluntLabel;
	@FXML
	private TitledPane equipmentContainer;
	@FXML
	private Button headArmorButton;
	@FXML
	private Button chestArmorButton;
	@FXML
	private Button legArmorButton;
	@FXML
	private Button handArmorButton;
	@FXML
	private Button rightHandWeaponButton;
	@FXML
	private Button leftHandWeaponButton;
	@FXML
	private Button deleteButton;
	@FXML
	private TextArea bigMessageTextArea;
	@FXML
	private TabPane bagTabPane;
	
	
	
	public void initialize() {
		map = new ObservableMap();
		buttons = new Button[Constants.ObservableMapSize][Constants.ObservableMapSize];
		pane.setPrefSize(Constants.ButtonSize * Constants.ObservableMapSize, 
				Constants.ButtonSize * Constants.ObservableMapSize);
		pane.setMinSize(Constants.ButtonSize * Constants.ObservableMapSize, 
				Constants.ButtonSize * Constants.ObservableMapSize);
		pane.setMaxSize(Constants.ButtonSize * Constants.ObservableMapSize, 
				Constants.ButtonSize * Constants.ObservableMapSize);
		for(int i = 0; i < Constants.ObservableMapSize; i++) {
			for(int j = 0; j < Constants.ObservableMapSize; j++) {
				Button b = new Button("");
				b.setPrefSize(Constants.ButtonSize, Constants.ButtonSize);
				buttons[i][j] = b;
				buttons[i][j].setOnKeyPressed((KeyEvent ke) -> {
					if(map.canMove(ke.getCode())) {
						try {
							map.move(ke.getCode());
						} catch (ExitReachedException e) {
							gameOver();
						}
					}
				});
				pane.getChildren().add(b);
			}
		}
		map.setButtons(buttons);
	}

	public void setUp() {
		player = mainApp.getPlayer();
		world = new WorldMap();
		player.setMap(map);
		try {
			map.setCurrentLevel(world.getFirstLevel());
		} catch (ExitReachedException ex) {
			ex.printStackTrace();
		}
		playerInformationContainer.setText(player.getName());
		equipmentContainer.setText(player.getName());
		GridPane bag1 = (GridPane) bagTabPane.getTabs().get(0).getContent();
		GridPane bag2 = (GridPane) bagTabPane.getTabs().get(1).getContent();
		GridPane bag3 = (GridPane) bagTabPane.getTabs().get(2).getContent();
		inventoryBag = new ArrayList<Button>();
		for(Node node : bag1.getChildren()) {
			try {
				inventoryBag.add((Button) node);
			} catch(ClassCastException ex) {
				// ex.printStackTrace();
			}
		}
		for(Node node : bag2.getChildren()) {
			try {
				inventoryBag.add((Button) node);
			} catch(ClassCastException ex) {
				// ex.printStackTrace();
			}
		}
		for(Node node : bag3.getChildren()) {
			try {
				inventoryBag.add((Button) node);
			} catch(ClassCastException ex) {
				// ex.printStackTrace();
			}
		}
	}
	
	@FXML
	private void close() {
		Alert exitPrompt = new Alert(AlertType.CONFIRMATION);
		exitPrompt.setTitle("Exit game");
		exitPrompt.setHeaderText("This game has no save functionality, any progress will be lost.");
		exitPrompt.setContentText("Are you sure, you want to exit?");
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
		exitPrompt.getButtonTypes().setAll(buttonYes, buttonNo);
		
		Optional<ButtonType> result = exitPrompt.showAndWait();
		if(result.get() == buttonYes) {
			Platform.exit();
		}		
	}
	
	@FXML
	private void maxHealthUpgraded() {
		
	}
	
	@FXML
	private void strengthUpgraded() {
		
	}
	
	@FXML
	private void agilityUpgraded() {
		
	}
	
	@FXML
	private void enduranceUpgraded() {
		
	}
	
	@FXML
	private void slashingUpgraded() {
		
	}
	
	@FXML
	private void piercingUpgraded() {
		
	}
	
	@FXML
	private void bluntUpgraded() {
		
	}
	
	private void gameOver() {
		Alert exitPrompt = new Alert(AlertType.CONFIRMATION);
		exitPrompt.setTitle("Congratulations!");
		exitPrompt.setHeaderText("You reached the exit!");
		exitPrompt.setContentText("Are you sure, you want to exit?");
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
		exitPrompt.getButtonTypes().setAll(buttonYes, buttonNo);
		
		Optional<ButtonType> result = exitPrompt.showAndWait();
		if(result.get() == buttonYes) {
			Platform.exit();
		}
	}
}
