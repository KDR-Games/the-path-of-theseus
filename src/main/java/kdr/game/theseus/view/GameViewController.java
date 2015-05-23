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
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import kdr.game.theseus.Constants;
import kdr.game.theseus.ObservableMap;
import kdr.game.theseus.Player;
import kdr.game.theseus.WorldMap;

public class GameViewController extends ViewController {

	private Button[][] buttons;
	private WorldMap world;
	private ObservableMap map;
	private Player player;
	
	@FXML
	TilePane pane;

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
						map.move(ke.getCode());
					}
				});
				pane.getChildren().add(b);
			}
		}
		map.setButtons(buttons);
	}

	@SuppressWarnings("unused") // TODO: remove this
	public void setUp() {
		player = mainApp.getPlayer();
		if(true) { // Generate new map
			world = new WorldMap(null, null, null);
			map.setCurrentLevel(world.getFirstLevel());
		} else { // Load saved game
			// TODO: search for the saved game state
		}
		player.setMap(map);
	}
	
	@FXML
	private void saveAndClose() {
		Platform.exit();
	}
}
