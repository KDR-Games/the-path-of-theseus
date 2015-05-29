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

package kdr.game.theseus;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kdr.game.theseus.view.GameViewController;

import static kdr.game.theseus.view.Main.logger;

/**
 * This is the main controller for input and player interaction.
 */
public class Player extends Creature {
	
	private GameViewController view;
	
	private ObservableMap map;
	private Difficulty difficulty;
	private boolean ghostMode;

	private int level;
	private int freePoints;
	private int experience;
	
	private int kills;
	private int distanceTravelled;
	
	/**
	 * Creates a new player with the given parameters.
	 * Stats and proficiencies are added later.
	 * @param name - the name of the player
	 * @param difficulty - the difficulty of the game
	 * @param ghostMode - whether ghost mode is turned on or not
	 */
	public Player(String name, Difficulty difficulty, boolean ghostMode) {
		super(name);
		level = 0;
		freePoints = 1;
		experience = 0;
		kills = 0;
		distanceTravelled = 0;
		this.difficulty = ((difficulty != null) ? difficulty : Difficulty.Normal);
		this.ghostMode = ghostMode;
		
		logger.info("New player created." + 
		"\nName: " + name + 
		"\nDifficulty: " + this.difficulty.toString() + 
		"\nGhost mode: " + (ghostMode ? "true" : "false"));
	}
	
	public void setView(GameViewController view) {
		this.view = view;
		for(int i = 0; i < Constants.ObservableMapSize; i++) {
			for(int j = 0; j < Constants.ObservableMapSize; j++) {
				view.getButtons()[i][j].setOnKeyPressed((KeyEvent ke) -> {
					if(map.canMove(ke.getCode())) {
						try {
							move(ke.getCode());
						} catch (ExitReachedException e) {
							view.gameOver();
						}
					}
				});
			}
		}
		map.setButtons(view.getButtons());
		logger.info("Map set up properly.");
	}
	
	/**
	 * @return the ghostMode
	 */
	public boolean isGhostMode() {
		return ghostMode;
	}

	/**
	 * @param ghostMode - the ghostMode to set
	 */
	public void setGhostMode(boolean ghostMode) {
		this.ghostMode = ghostMode;
	}

	/**
	 * @return the freePoints
	 */
	public int getFreePoints() {
		return freePoints;
	}

	/**
	 * @return the experience
	 */
	public int getExperience() {
		return experience;
	}
	
	/**
	 * @return the experience
	 */
	public double getExperienceInPercent() {
		return (double)experience / (double)Constants.XpLevels[level];
	}

	/**
	 * @param xp - added to the experience points
	 */
	public void addToExperience(int xp) {
		this.experience += xp;
		while(this.experience > Constants.XpLevels[level]) {
			freePoints++;
			level++;
			logger.info("Level up! New level: " + level + ".");
		}
	}

	/**
	 * @return the kills
	 */
	public int getKills() {
		return kills;
	}
	
	/**
	 * The player killed a monster.
	 */
	public void incrementKills() {
		kills++;
	}
	
	/**
	 * @return the distanceTravelled
	 */
	public int getDistanceTravelled() {
		return distanceTravelled;
	}

	/**
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty - the difficulty to set
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(ObservableMap map) {
		this.map = map;
		map.setDifficulty(difficulty);
		map.setGhostMode(ghostMode);
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * If the player has unspent points, 
	 * this function upgrades it's strength value.
	 */
	public void upgradeStrength() {
		if(freePoints > 0 && !stats.getStrength().isMax()) {
			stats.upgradeStrength();
			freePoints--;
			logger.info("Strength upgraded.");
		}
	}
	
	/**
	 * If the player has unspent points, 
	 * this function upgrades it's agility value.
	 */
	public void upgradeAgility() {
		if(freePoints > 0 && !stats.getAgility().isMax()) {
			stats.upgradeAgility();
			freePoints--;
			logger.info("Agility upgraded.");
		}
	}
	
	/**
	 * If the player has unspent points, 
	 * this function upgrades it's endurance value.
	 */
	public void upgradeEndurance() {
		if(freePoints > 0 && !stats.getEndurance().isMax()) {
			stats.upgradeEndurance();
			freePoints--;
			logger.info("Endurance upgraded.");
		}
	}
	
	/**
	 * If the player has unspent points, 
	 * this function upgrades it's maximum health value.
	 */
	public void upgradeMaxHealth() {
		if(freePoints > 0) {
			stats.upgradeMaxHealth();
			freePoints--;
			logger.info("Maximum health upgraded.");
		}
	}
	
	/**
	 * If the player has unspent points, 
	 * this function upgrades it's slasing proficiency value.
	 */
	public void upgradeProficiencySlashing() {
		if(freePoints > 0 && !proficiencies.getSlashing().isMax()) {
			proficiencies.upgradeSlashing();
			freePoints--;
			logger.info("Slashing upgraded.");
		}
	}
	
	/**
	 * If the player has unspent points, 
	 * this function upgrades it's piercing proficiency value.
	 */
	public void upgradeProficiencyPiercing() {
		if(freePoints > 0 && !proficiencies.getPiercing().isMax()) {
			proficiencies.upgradePiercing();
			freePoints--;
			logger.info("Piercing upgraded.");
		}
	}
	
	/**
	 * If the player has unspent points, 
	 * this function upgrades it's blunt proficiency value.
	 */
	public void upgradeProficiencyBlunt() {
		if(freePoints > 0 && !proficiencies.getBlunt().isMax()) {
			proficiencies.upgradeBlunt();
			freePoints--;
			logger.info("Blunt upgraded.");
		}
	}
	
	/**
	 * Moves the player(the map?) in the given direction.
	 * @param code - the code of the button which is pressed.
	 * @throws ExitReachedException throws this if the exit is reached.
	 */
	public void move(KeyCode code) throws ExitReachedException {
		switch (code) {
		case LEFT:
			logger.info("Move LEFT.");
			map.setCenterTile(map.getCenterTile().getNeighbors().getLeft());
			distanceTravelled++;
			break;

		case RIGHT:
			logger.info("Move RIGHT.");
			map.setCenterTile(map.getCenterTile().getNeighbors().getRight());
			distanceTravelled++;
			break;

		case UP:
			logger.info("Move UP.");
			map.setCenterTile(map.getCenterTile().getNeighbors().getTop());
			distanceTravelled++;
			break;

		case DOWN:
			logger.info("Move DOWN.");
			map.setCenterTile(map.getCenterTile().getNeighbors().getBottom());
			distanceTravelled++;
			break;

		default:
			break;
		}
		map.updateMap();
	}
}
