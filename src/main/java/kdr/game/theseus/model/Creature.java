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

public class Creature {
	protected String name;
	protected int health;
	protected int stamina;
	protected Stats stats;
	protected Equipment equipment;
	protected int XP;

	/**
	 * @param name
	 */
	protected Creature(String name) {
		super();
		this.name = name;
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

	public void setHealthToMax() {
		this.health = stats.getMaxHealth();
	}
	public void decreaseHealth(int value) throws CreatureDeadException {
		health -= value;
		if(health <= 0) {
			throw new CreatureDeadException(this);
		}
	}

	/**
	 * @return the stamina
	 */
	public int getStamina() {
		return stamina;
	}

	/**
	 * @param stamina the stamina to set
	 */
	public void decreaseStamina() {
		
	}
	
	public void increaseStamina() {
		
	}

	/**
	 * @return the stats
	 */
	public Stats getStats() {
		return stats;
	}

	/**
	 * @param stats the stats to set
	 */
	public void setStats(Stats stats) {
		this.stats = stats;
	}

	/**
	 * @return the xP
	 */
	public int getXP() {
		return XP;
	}

	/**
	 * @param xP the xP to set
	 */
	public void setXP(int xP) {
		XP = xP;
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
	 * @return the speed
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

	public double getHealthInPercent() {
		return ((double)health / (double)stats.getMaxHealth()) * 100.0;
	}
}
