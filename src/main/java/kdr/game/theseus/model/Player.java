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

import kdr.game.theseus.ObservableMap;


public class Player extends Creature {
	
	private ObservableMap map;
	
	private int kills;
	private int distanceTravelled;
	private Difficulty difficulty;
	private int level;
	private int freePoints;
	
	/**
	 * @param name
	 */
	public Player(String name) {
		super(name);
		level = 0;
		freePoints = 1;
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
	
	public void upgradeStrength() {
		if(freePoints > 0) {
			stats.upgradeStrength();
		}
	}
}
