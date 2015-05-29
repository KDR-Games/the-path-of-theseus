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

import java.util.Map;

import kdr.game.theseus.model.Armor;
import kdr.game.theseus.model.Weapon;
import kdr.game.theseus.view.GameViewController;
import kdr.game.theseus.view.Main;
import static kdr.game.theseus.view.Main.logger;

/**
 * The main controller class. This holds everything. 
 */
public class GameController {
	private GameViewController view;
	private Main mainApp;
	private Player player;
	private WorldMap world;
	private ObservableMap map;
	private Map<String, Armor> armors;
	private Map<String, Weapon> weapons;
	
	/**
	 * 
	 * @param mainApp - reference to {@link kdr.game.theseus.view.Main}
	 * @param player - reference to the player 
	 */
	public GameController(Main mainApp, Player player) {
		this.mainApp = mainApp;
		this.player = player;
		world = new WorldMap();
		map = new ObservableMap();
		try {
			map.setCurrentLevel(world.getFirstLevel());
		} catch (ExitReachedException e) {
			e.printStackTrace();
		}
		player.setMap(map);
		
		armors = new ArmorsDAO("/xml/Armors.xml").getArmors();
		weapons = new WeaponsDAO("/xml/Weapons.xml").getWeapons();
		logger.info("GameController succesfully created.");
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * @param view - reference to the JavaFx view-controller
	 */
	public void setView(GameViewController view) {
		this.view = view;
		player.setView(view);
		map.updateMap();
	}

}
