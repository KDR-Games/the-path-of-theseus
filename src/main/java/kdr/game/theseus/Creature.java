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

import static kdr.game.theseus.view.Main.logger;
import javafx.scene.image.Image;
import kdr.game.theseus.model.CreatureDeadException;
import kdr.game.theseus.model.Equipment;
import kdr.game.theseus.model.Proficiencies;
import kdr.game.theseus.model.Stats;

/**
 * The main class for creatures. This class can't be used directly.
 * Other classes are derived from this.
 * @see kdr.game.theseus.Enemy
 * @see kdr.game.theseus.Player
 */
public class Creature {
	protected Tile containerTile;
	protected String name;
	protected Image image;
	protected int health;
	protected Stats stats;
	protected Proficiencies proficiencies;
	protected Equipment equipment;

	/**
	 * The constructor is protected. You can't use this 
	 * class directly. It is used only for type-casting.
	 * @param name - the name
	 * @param image - the image
	 * @see kdr.game.theseus.Enemy
	 * @see kdr.game.theseus.Player
	 */
	protected Creature(String name, Image image) {
		super();
		this.name = name;
		this.image = image;
		equipment = new Equipment();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Sets the health to maximum.
	 */
	public void setHealthToMax() {
		this.health = stats.getMaxHealth();
		logger.info("Health set to max.");
	}
	
	/**
	 * 
	 * @param value - the value
	 * @throws CreatureDeadException when this creature's health reaches zero or below	
	 */
	public void decreaseHealth(int value) throws CreatureDeadException {
		health -= value;
		if(health <= 0) {
			logger.info("Creature died.");
			throw new CreatureDeadException(this);
		}
	}

	/**
	 * @return the stats
	 */
	public Stats getStats() {
		return stats;
	}

	/**
	 * @param stats - the stats
	 */
	public void setStats(Stats stats) {
		this.stats = stats;
		setHealthToMax();
	}

	/**
	 * @return the proficiencies
	 */
	public Proficiencies getProficiencies() {
		return proficiencies;
	}

	/**
	 * @param proficiencies the proficiencies to set
	 */
	public void setProficiencies(Proficiencies proficiencies) {
		this.proficiencies = proficiencies;
	}
	
	/**
	 * @return the image
	 */
	public Image getGraphic() {
		return image;
	}

	/**
	 * @param image - the image to set
	 */
	public void setGraphic(Image image) {
		this.image = image;
	}

	/**
	 * @return the equipment
	 */
	public Equipment getEquipment() {
		return equipment;
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		int damage = 0;
		if(equipment.getMainHandWeapon() != null) {
			damage += equipment.getMainHandWeapon().getDamage();
		} 
		if(equipment.getOffHandWeapon() != null) {
			damage += equipment.getOffHandWeapon().getDamage();
		}
		return damage;
	}

	/**
	 * @return the speed based on the burden of the equipment
	 */
	public double getSpeed() {
		double speed = 16.0;

		double armorBurden = 0.0;
		double weaponBurden = 0.0;

		if(equipment.getMainHandWeapon() != null) {
			if(equipment.isTwoHanded()) {
				weaponBurden += 2.0*( 2.0 - equipment.getMainHandWeapon().getSpeed());
			} else {
				weaponBurden += 2.0 - equipment.getMainHandWeapon().getSpeed();
			}
		} 
		if(equipment.getOffHandWeapon() != null) {
			weaponBurden += 2.0 - equipment.getOffHandWeapon().getSpeed();
		}

		if(equipment.getHeadArmor() != null) {
			armorBurden += 2.0 - equipment.getHeadArmor().getSpeed();
		}
		if(equipment.getChestArmor() != null) {
			armorBurden += 2.0 - equipment.getChestArmor().getSpeed();
		}
		if(equipment.getLegArmor() != null) {
			armorBurden += 2.0 - equipment.getLegArmor().getSpeed();
		}
		if(equipment.getHandArmor() != null) {
			armorBurden += 2.0 - equipment.getHandArmor().getSpeed();
		}

		return speed*stats.getSpeedModifier() - weaponBurden*2 - armorBurden;
	}	

	/**
	 * 
	 * @return the current health in percentage
	 */
	public double getHealthInPercent() {
		return ((double)health / (double)stats.getMaxHealth()) * 100.0;
	}

	/**
	 * @return the containerTile
	 */
	public Tile getContainerTile() {
		return containerTile;
	}

	/**
	 * @param containerTile - the containerTile to set
	 */
	public void setContainerTile(Tile containerTile) {
		if(this.containerTile != null) {
			this.containerTile.setCreature(null);
		}
		containerTile.setCreature(this);
		this.containerTile = containerTile;
	}
}
