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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import kdr.game.theseus.Difficulty;
import kdr.game.theseus.Player;

public class LoginViewController extends ViewController {

	@FXML
	private TextField name;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField confirmPassword;
	@FXML
	private CheckBox isNewCharacter;
	@FXML
	private Label welcomeLabel;
	@FXML
	private Label confirmPasswordLabel;
	@FXML
	private RadioButton difficultyEasy;
	@FXML
	private RadioButton difficultyNormal;
	@FXML
	private RadioButton difficultyHard;
	@FXML
	private Label difficultyLabel;

	@SuppressWarnings("unused") // TODO: remove this
	@FXML
	private void check() {
		boolean ok = false;
		if (isNewCharacter.isSelected()) { 
			// Register a new character
			if (name.getText().length() < 3) {
				name.getStyleClass().add("wrong_user_or_pass");
			} else {
				name.getStyleClass().remove("wrong_user_or_pass");
				if(true) { // TODO: check character from database
					if (password.getText().length() > 3 &&
							password.getText().equals(confirmPassword.getText())) {
						password.getStyleClass().remove("wrong_user_or_pass");
						confirmPassword.getStyleClass().remove("wrong_user_or_pass");
						ok = true;
					} else {
						password.getStyleClass().add("wrong_user_or_pass");
						confirmPassword.getStyleClass().add("wrong_user_or_pass");
					}
				} else {
					name.getStyleClass().add("wrong_user_or_pass");					
				}
			}
		} else { 
			// Continue a previously saved game
			if (name.getText().length() < 3) {
				name.getStyleClass().add("wrong_user_or_pass");
			} else {
				name.getStyleClass().remove("wrong_user_or_pass");
				if (true) { // TODO: check character from database
					name.getStyleClass().remove("wrong_user_or_pass");
					password.getStyleClass().remove("wrong_user_or_pass");
					ok = true;
				} else {
					name.getStyleClass().add("wrong_user_or_pass");
					password.getStyleClass().add("wrong_user_or_pass");
				}
			}
		}

		if (ok) {
			done();	
		} else { // TODO: this is only for development
			done();
		}
	}

	private void done() {
		Player currentPlayer = new Player(name.getText());
		if (isNewCharacter.isSelected()) {
			// TODO: set up new character stats
			if (difficultyEasy.isSelected()) {
				currentPlayer.setDifficulty(Difficulty.Easy);
			} else if (difficultyNormal.isSelected()) {
				currentPlayer.setDifficulty(Difficulty.Normal);
			} else if (difficultyHard.isSelected()) {
				currentPlayer.setDifficulty(Difficulty.Hard);
			}
		} else {
			// TODO: load save data
		}
		mainApp.setPlayer(currentPlayer);
		mainApp.showGame();
	}

	@FXML
	private void switchLoginRegister() {
		if (isNewCharacter.isSelected()) {
			confirmPassword.setVisible(true);
			confirmPasswordLabel.setVisible(true);
			confirmPassword.clear();
			password.clear();
			difficultyEasy.setVisible(true);
			difficultyNormal.setVisible(true);
			difficultyHard.setVisible(true);
			difficultyLabel.setVisible(true);
		} else {
			confirmPassword.setVisible(false);
			confirmPasswordLabel.setVisible(false);
			confirmPassword.clear();
			password.clear();
			difficultyEasy.setVisible(false);
			difficultyNormal.setVisible(false);
			difficultyHard.setVisible(false);
			difficultyLabel.setVisible(false);
		}
	}

	@FXML
	private void quit() {
		Platform.exit();
	}

}
