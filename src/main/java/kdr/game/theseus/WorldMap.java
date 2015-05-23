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

/**
 * @author koldavid
 *
 */
public class WorldMap {
	private LevelMap firstLevel;
	private LevelMap secondLevel;
	private LevelMap thirdLevel;
	
	
	
	/**
	 * @param firstLevel
	 * @param secondLevel
	 * @param thirdLevel
	 */
	public WorldMap(LevelMap firstLevel, LevelMap secondLevel, LevelMap thirdLevel) {
		super();
		if(firstLevel == null && secondLevel == null && thirdLevel == null) {
			generateNew();
			System.out.println("New map generated.");
		} else {
			this.firstLevel = firstLevel;
			this.secondLevel = secondLevel;
			this.thirdLevel = thirdLevel;			
		}
	}

	/**
	 * @return the first
	 */
	public LevelMap getFirstLevel() {
		return firstLevel;
	}
	
	/**
	 * @param first the first to set
	 */
	public void setFirstLevel(LevelMap firstLevel) {
		this.firstLevel = firstLevel;
	}
	
	/**
	 * @return the second
	 */
	public LevelMap getSecondLevel() {
		return secondLevel;
	}
	
	/**
	 * @param second the second to set
	 */
	public void setSecondLevel(LevelMap secondLevel) {
		this.secondLevel = secondLevel;
	}
	
	/**
	 * @return the third
	 */
	public LevelMap getThirdLevel() {
		return thirdLevel;
	}
	
	/**
	 * @param third the third to set
	 */
	public void setThirdLevel(LevelMap thirdLevel) {
		this.thirdLevel = thirdLevel;
	}
	
	private void generateNew() {
		firstLevel = new LevelMap(Constants.MapSize_1);
		secondLevel = new LevelMap(Constants.MapSize_2);
		thirdLevel = new LevelMap(Constants.MapSize_3);
	}
}
