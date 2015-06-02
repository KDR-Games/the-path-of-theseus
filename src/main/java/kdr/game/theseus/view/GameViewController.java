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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import kdr.game.theseus.Constants;
import kdr.game.theseus.Enemy;
import kdr.game.theseus.GameController;
import kdr.game.theseus.HighScoresDAO;
import kdr.game.theseus.model.HighScore;
import kdr.game.theseus.model.StatValue;

public class GameViewController extends ViewController {

	private Button[][] buttons;
	private ArrayList<Button> inventoryBag;

	@FXML
	private TilePane pane;
	@FXML
	private ProgressBar healthProgressBar;
	@FXML
	private ProgressBar xpProgressBar;
	@FXML
	private Label currentHealthLabel;
	@FXML 
	private Label currentXpLabel;
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
				pane.getChildren().add(b);
			}
		}
	}

	/**
	 * A helper function, called right after this controller is instantiated.
	 * @param game - the controller to set
	 */
	@Override
	public void setGameController(GameController game) {
		this.game = game;
		game.setView(this);
		updateHealthAndXpBar();
		showUpgradePointsIfAvailable();
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

	/**
	 * @return the buttons which serve as the tiles of tiles map.
	 */
	public Button[][] getMapButtons() {
		return buttons;
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
		game.getPlayer().upgradeMaxHealth();
		playerMaxHealthLabel.setText("" + game.getPlayer().getStats().getMaxHealth());
		showUpgradePointsIfAvailable();
	}

	@FXML
	private void strengthUpgraded() {
		game.getPlayer().upgradeStrength();
		playerStrengthLabel.setText(game.getPlayer().getStats().getStrength().toString());
		showUpgradePointsIfAvailable();
	}

	@FXML
	private void agilityUpgraded() {
		game.getPlayer().upgradeAgility();
		playerAgilityLabel.setText(game.getPlayer().getStats().getAgility().toString());
		showUpgradePointsIfAvailable();
	}

	@FXML
	private void enduranceUpgraded() {
		game.getPlayer().upgradeEndurance();
		playerEnduranceLabel.setText(game.getPlayer().getStats().getEndurance().toString());
		showUpgradePointsIfAvailable();
	}

	@FXML
	private void slashingUpgraded() {
		game.getPlayer().upgradeProficiencySlashing();
		playerSlashingLabel.setText(game.getPlayer().getProficiencies().getSlashing().toString());
		showUpgradePointsIfAvailable();
	}

	@FXML
	private void piercingUpgraded() {
		game.getPlayer().upgradeProficiencyPiercing();
		playerPiercingLabel.setText(game.getPlayer().getProficiencies().getPiercing().toString());
		showUpgradePointsIfAvailable();
	}

	@FXML
	private void bluntUpgraded() {
		game.getPlayer().upgradeProficiencyBlunt();
		playerBluntLabel.setText(game.getPlayer().getProficiencies().getBlunt().toString());
		showUpgradePointsIfAvailable();
	}

	/**
	 * A prompt pops up, with a congratulation text.
	 * The current statistics are written in the database.
	 * The high scores can be viewed now.
	 */
	public void gameOver() {
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
	
	@FXML
	private void showHighScores() {
		ArrayList<HighScore> highScores = new HighScoresDAO().getHighScores();
		TextArea table = new TextArea();
		table.setText("Name                Kills               Distance            ");
		for(HighScore score : highScores) {
			table.appendText(score.toString());
		}
		
		// TODO: show a proper high score list
	}

	public void showUpgradePointsIfAvailable() {
		boolean hasFreePoints = game.getPlayer().getFreePoints() > 0;
		playerMaxHealthButton.setVisible(hasFreePoints);
		if(!game.getPlayer().getStats().getStrength().isMax()) {
			playerStrengthButton.setVisible(hasFreePoints);
		} else {
			playerStrengthButton.setVisible(false);
		}
		if(!game.getPlayer().getStats().getAgility().isMax()) {
			playerAgilityButton.setVisible(hasFreePoints);
		} else {
			playerAgilityButton.setVisible(false);
		}
		if(!game.getPlayer().getStats().getEndurance().isMax()) {
			playerEnduranceButton.setVisible(hasFreePoints);
		} else {
			playerEnduranceButton.setVisible(false);
		}
		if(!game.getPlayer().getProficiencies().getSlashing().isMax()) {
			playerSlashingButton.setVisible(hasFreePoints);
		} else {
			playerSlashingButton.setVisible(false);
		}
		if(!game.getPlayer().getProficiencies().getPiercing().isMax()) {
			playerPiercingButton.setVisible(hasFreePoints);
		} else {
			playerPiercingButton.setVisible(false);
		}
		if(!game.getPlayer().getProficiencies().getBlunt().isMax()) {
			playerBluntButton.setVisible(hasFreePoints);
		} else {
			playerBluntButton.setVisible(false);
		}

		playerInformationContainer.setText(game.getPlayer().getName() + " - Level " + game.getPlayer().getLevel());
		equipmentContainer.setText(game.getPlayer().getName() + " - Level " + game.getPlayer().getLevel());
	}

	public void updateHealthAndXpBar(){
		xpProgressBar.setProgress(game.getPlayer().getExperienceInPercent());
		currentXpLabel.setText(game.getPlayer().getExperience() + 
				"/" + Constants.XpLevels[game.getPlayer().getLevel()]);
		healthProgressBar.setProgress(game.getPlayer().getHealthInPercent());
		currentHealthLabel.setText("" + game.getPlayer().getHealth());
	}

	public void showEnemyStats(Enemy enemy) {
		if(enemy != null) {
			enemyInformationContainer.setText(enemy.getName());
			enemyMaxHealthLabel.setText(enemy.getStats().getMaxHealth() + "");
			enemyStrengthLabel.setText(enemy.getStats().getStrength().toString());
			enemyAgilityLabel.setText(enemy.getStats().getAgility().toString());
			enemyEnduranceLabel.setText(enemy.getStats().getEndurance().toString());
			if(enemy.getProficiencies().getSlashing() != StatValue.None) {
				enemySlashingLabel.setText(enemy.getProficiencies().getSlashing().toString());
			} else {
				enemySlashingLabel.setText("-");
			}
			if(enemy.getProficiencies().getPiercing() != StatValue.None) {
				enemyPiercingLabel.setText(enemy.getProficiencies().getPiercing().toString());
			} else {
				enemyPiercingLabel.setText("-");
			}
			if(enemy.getProficiencies().getBlunt() != StatValue.None) {
				enemyBluntLabel.setText(enemy.getProficiencies().getBlunt().toString());
			} else {
				enemyBluntLabel.setText("-");
			}
		} else {
			enemyInformationContainer.setText("-");
			enemyMaxHealthLabel.setText("-");
			enemyStrengthLabel.setText("-");
			enemyAgilityLabel.setText("-");
			enemyEnduranceLabel.setText("-");
			enemySlashingLabel.setText("-");
			enemyPiercingLabel.setText("-");
			enemyBluntLabel.setText("-");
		}
	}
}
