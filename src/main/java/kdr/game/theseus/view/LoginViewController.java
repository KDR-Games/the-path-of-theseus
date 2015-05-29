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

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import kdr.game.theseus.Difficulty;
import kdr.game.theseus.Player;
import kdr.game.theseus.StatValue;
import kdr.game.theseus.Stats;
import kdr.game.theseus.model.Proficiencies;

public class LoginViewController extends ViewController {

	@FXML
	private TextField name;
	@FXML
	private Label welcomeLabel;
	@FXML
	private RadioButton difficultyEasy;
	@FXML
	private RadioButton difficultyNormal;
	@FXML
	private RadioButton difficultyHard;
	@FXML
	private Label difficultyLabel;
	@FXML
	private ToggleGroup difficultyToggleGroup;
	@FXML
	private CheckBox ghostModeCheckBox;

	@SuppressWarnings("unused") // TODO: remove this
	@FXML
	private void check() {
		boolean ok = false;

		if (name.getText().length() < 3) {
			name.getStyleClass().add("wrong_user");
		} else {
			name.getStyleClass().remove("wrong_user");
			if (true) { // TODO: check character from database
				name.getStyleClass().remove("wrong_user");
				ok = true;
			} else {
				name.getStyleClass().add("wrong_user");
			}
		}

		if (ok) {
			done();	
		} else { // TODO: this is only for development
			done();
		}
	}

	/**
	 * The login is okay, setting up the player, switching to game window.
	 */
	private void done() {
		Difficulty difficulty = Difficulty.Normal;
		if (difficultyToggleGroup.getSelectedToggle().equals(difficultyEasy)) {
			difficulty = Difficulty.Easy;
		} else if (difficultyToggleGroup.getSelectedToggle().equals(difficultyNormal)) {
			difficulty = Difficulty.Normal;
		} else if (difficultyToggleGroup.getSelectedToggle().equals(difficultyHard)) {
			difficulty = Difficulty.Hard;
		}
		boolean ghostMode = ghostModeCheckBox.isSelected();
		
		Player currentPlayer = new Player(name.getText(), difficulty, ghostMode);
		currentPlayer.setStats(new Stats(150, StatValue.E, StatValue.E, StatValue.E));
		currentPlayer.setProficiencies(new Proficiencies(StatValue.E, StatValue.E, StatValue.E));

		mainApp.setPlayer(currentPlayer);
		mainApp.showGame();
	}

	@FXML
	private void quit() {
		Platform.exit();
	}

}
