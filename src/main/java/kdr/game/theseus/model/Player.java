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

package kdr.game.theseus.model;

import kdr.game.theseus.Difficulty;
import kdr.game.theseus.ObservableMap;


public class Player extends Creature {
	
	private ObservableMap map;
	
	private int kills;
	private int distanceTravelled;
	private Difficulty difficulty;
	private int level;
	private int freePoints;
	private boolean ghostMode;
	
	/**
	 * @param name - the name of the character
	 */
	public Player(String name) {
		super(name);
		level = 0;
		freePoints = 1;
		setGhostMode(false);
	}
	
	/**
	 * @return the ghostMode
	 */
	public boolean isGhostMode() {
		return ghostMode;
	}

	/**
	 * @param ghostMode the ghostMode to set
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
	 * @param freePoints - the freePoints to set
	 */
	public void setFreePoints(int freePoints) {
		this.freePoints = freePoints;
	}

	/**
	 * @return the kills
	 */
	public int getKills() {
		return kills;
	}
	
	/**
	 * @param kills the kills to set
	 */
	public void setKills(int kills) {
		this.kills = kills;
	}
	
	/**
	 * @return the distanceTravelled
	 */
	public int getDistanceTravelled() {
		return distanceTravelled;
	}
	
	/**
	 * @param distanceTravelled the distanceTravelled to set
	 */
	public void setDistanceTravelled(int distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}
	
	/**
	 * @return the map
	 */
	public ObservableMap getMap() {
		return map;
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
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty the difficulty to set
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
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
		}
	}
}
