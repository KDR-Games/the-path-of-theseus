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


import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import kdr.game.theseus.Constants;
import kdr.game.theseus.Difficulty;
import kdr.game.theseus.ExitReachedException;
import kdr.game.theseus.LevelMap;
import kdr.game.theseus.Tile;
import kdr.game.theseus.TileType;
import kdr.game.theseus.VisibilityCalculator;
import static kdr.game.theseus.view.Main.logger;

/**
 * This class is the controller of the character's movement and the
 * visibility of the map.
 */
public class ObservableMap {
	private LevelMap currentLevel;
	private Tile[][] tiles;
	private Button[][] buttons;
	private Tile centerTile;
	private Difficulty difficulty;
	private final int center = (int)(Constants.ObservableMapSize/2);

	/**
	 * Creates a new instance of this class.
	 */
	public ObservableMap() {
		super();
		tiles = new Tile[Constants.ObservableMapSize][Constants.ObservableMapSize];
		buttons = new Button[Constants.ObservableMapSize][Constants.ObservableMapSize];
		difficulty = Difficulty.Normal;
	}

	/**
	 * Updates the map and the buttons corresponding to the actual
	 * location of the character({@code centerTile}).
	 */
	public void updateMap() {
		for(int i = 0; i < Constants.ObservableMapSize; i++) {
			for(int j = 0; j < Constants.ObservableMapSize; j++) {
				int col = centerTile.x() - center + j;
				int row = centerTile.y() - center + i;
				tiles[i][j] = currentLevel.getTiles()[row][col];
			}
		}
		VisibilityCalculator.updateVisibility(tiles, difficulty);
		updateButtons();
		logger.info("Map updated.");
	}

	/**
	 * Updates the styles of the buttons, corresponding to the 
	 * type and visibility of the tile which it belongs to.
	 */
	private void updateButtons() {
		for(int i = 0; i < Constants.ObservableMapSize; i++) {
			for(int j = 0; j < Constants.ObservableMapSize; j++) {
				buttons[i][j].getStyleClass().clear();
				buttons[i][j].getStyleClass().add("tile");
				switch (tiles[i][j].getType()) {
				case Floor:
					buttons[i][j].getStyleClass().add("tile-floor");
					break;

				case Wall:
					buttons[i][j].getStyleClass().add("tile-wall");
					break;

				case Door:
					buttons[i][j].getStyleClass().add("tile-door");
					break;

				case Margin:
					buttons[i][j].getStyleClass().add("tile-margin");
					break;

				case Trap:
					buttons[i][j].getStyleClass().add("tile-trap");
					break;

				case Entrance:
					buttons[i][j].getStyleClass().add("tile-entrance");
					break;

				case PotentialDoor:
					buttons[i][j].getStyleClass().add("tile-door");
					break;
					
				case Exit:
					buttons[i][j].getStyleClass().add("tile-exit");
					break;

				default:
					break;
				}

				buttons[i][j].setGraphic(null);
				switch (tiles[i][j].getVisibility()) {
				case Visible:
					buttons[i][j].getStyleClass().add("tile-visibility-visible");
					if(tiles[i][j].getCreature() != null) {
						buttons[i][j].setGraphic(new ImageView(tiles[i][j].getCreature().getGraphic()));
					}
					break;
				case Dim:
					buttons[i][j].getStyleClass().add("tile-visibility-dim");
					break;
				case NotVisible:
					buttons[i][j].getStyleClass().clear();
					buttons[i][j].getStyleClass().add("tile");
					buttons[i][j].getStyleClass().add("tile-visibility-not_visible");
					break;
				default:
					break;
				}
				buttons[i][j].applyCss();
			}
		}
		logger.info("Tiles updated");
	}

	/**
	 * @return the currentLevel
	 */
	public LevelMap getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * @param currentLevel - the currentLevel to set
	 * @throws ExitReachedException - if the entrance overlaps with the exit(this shouldn't even happen)
	 */
	public void setCurrentLevel(LevelMap currentLevel) throws ExitReachedException {
		this.currentLevel = currentLevel;
		setCenterTile(currentLevel.getEntrance());
		logger.info("New level set.");
	}

	/**
	 * @return the buttons
	 */
	public Button[][] getButtons() {
		return buttons;
	}

	/**
	 * This function is called from the view.
	 * @param buttons - the buttons to set
	 */
	public void setButtons(Button[][] buttons) {
		this.buttons = buttons;
		for(int i = 0; i < Constants.ObservableMapSize; i++) {
			for(int j = 0; j < Constants.ObservableMapSize; j++) {
				this.buttons[i][j] = buttons[i][j];
			}
		}
		buttons[center][center].setId("player");
		logger.info("Buttons set.");
	}

	/**
	 * @return the centerTile, the location of the {@link kdr.game.theseus.Player}.
	 */
	public Tile getCenterTile() {
		return centerTile;
	}

	/**
	 * This is used to move the player. If the player reaches the exit tile, 
	 * then an {@code ExitReachedException} is thrown.
	 * @param centerTile - the centerTile to set
	 * @throws ExitReachedException if {@code centerTile} is the exit
	 */
	public void setCenterTile(Tile centerTile) throws ExitReachedException {
		this.centerTile = centerTile;
		if(this.centerTile.getType() == TileType.Exit) {
			updateMap();
			logger.info("Exit reached!");
			throw new ExitReachedException();
		}
	}

	/**
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * Sets the difficulty of the map, which affects it's visibility.
	 * @param difficulty - the difficulty to set
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
}
