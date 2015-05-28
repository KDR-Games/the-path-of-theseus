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

/**
 * This class holds different attributes of a {@link kdr.game.theseus.model.Creature},
 * based on it's level.
 * 
 * @see kdr.game.theseus.model.Creature
 * @see kdr.game.theseus.model.Player
 * @see kdr.game.theseus.model.Monster
 */
public class Stats {
	/**
	 * Member variable to hold the maximum health.
	 */
	private int maxHealth;
	
	/**
	 * Member variable to hold the strength stat.
	 */
	private StatValue strength;
	
	/**
	 * Member variable to hold the agility stat.
	 */
	private StatValue agility;
	
	/**
	 * Member variable to hold the endurance stat.
	 */
	private StatValue endurance;
	
	/**
	 * Creates a new {@link #Stats} object, based on the member variables.
	 * @param maxHealth - sets the maximum health
	 * @param strength - sets the strength stat
	 * @param agility - sets the agility stat
	 * @param endurance - sets the endurance stat
	 */
	public Stats(int maxHealth, StatValue strength, StatValue agility, StatValue endurance) {
		super();
		this.maxHealth = maxHealth;
		this.strength = strength;
		this.agility = agility;
		this.endurance = endurance;
	}
	
	/**
	 * The <b>maximum health</b> is an upper limit of the health 
	 * points of a {@link kdr.game.theseus.model.Creature}.
	 * This is mostly determined by the <b>endurance</b> stat.
	 * @return the maximum health
	 * @see kdr.game.theseus.model.Creature#getHealth()
	 * @see #getEndurance()
	 * @see #getMaxHealthNextValue()
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * The <b>strength</b> stat is used to determine and modify the <b>damage</b> of a 
	 * {@link kdr.game.theseus.model.Creature}, which is an essential attribute
	 * in combat.
	 * @return the strength
	 */
	public StatValue getStrength() {
		return strength;
	}
	
	/**
	 * The <b>agility</b> stat is used to determine and modify the <b>speed</b> of a 
	 * {@link kdr.game.theseus.model.Creature}, which is an essential attribute
	 * in combat.
	 * @return the agility
	 */
	public StatValue getAgility() {
		return agility;
	}

	/**
	 * The <b>endurance</b> stat is used to determine and modify the 
	 * <b>maximum health</b> and the <b>decrease value of the stamina</b>
	 * (higher endurance - higher increase in stamina, lower decrease).
	 * @return the endurance
	 * @see kdr.game.theseus.model.Creature#getStamina()
	 * @see #getMaxHealth()
	 */
	public StatValue getEndurance() {
		return endurance;
	}
	
	/**
	 * Depending on <b>agility</b>, it calculates the speed modifier
	 * used to determine the combat speed of a {@link kdr.game.theseus.model.Creature}.
	 * @return the speed modifier, depending on agility
	 */
	public double getSpeedModifier() {
		switch (agility) {
		case E:
			return 1.0;
		case D:
			return 1.1;
		case C:
			return 1.2;
		case B:
			return 1.3;
		case A:
			return 1.5;
		case S:
			return 2.0;
		default:
			return 0.0;
		}
	}
	
	/**
	 * Upgrades the maximum health, based on the current level of endurance.
	 * 
	 * @see #getMaxHealth()
	 */
	public void upgradeMaxHealth() {
		maxHealth = getMaxHealthNextValue();
	}
	
	/**
	 * Upgrades the strength to the next level. 
	 * If it is already at maximum level, then nothing happens.
	 * 
	 * @see #getStrength()
	 */
	public void upgradeStrength() {
		strength = StatValue.getNextStatValue(strength);
	}
	
	/**
	 * Upgrades the agility to the next level. 
	 * If it is already at maximum level, then nothing happens.
	 * 
	 * @see #getAgility()
	 */
	public void upgradeAgility() {
		agility = StatValue.getNextStatValue(agility);
	}
	
	/**
	 * Upgrades the endurance to the next level. 
	 * If it is already at maximum level, then nothing happens.
	 * 
	 * @see #getEndurance()
	 */
	public void upgradeEndurance() {
		endurance = StatValue.getNextStatValue(endurance);
		upgradeMaxHealth();
	}
	
	/**
	 * Getter to help calculate the next value of the maximum health.
	 * This can be used to show this information without upgrading the maximum health.
	 * @return Returns the value of which {@link #maxHealth} will be upgraded with.
	 * @see #getMaxHealth()
	 * @see #upgradeMaxHealth()
	 */
	public int getMaxHealthNextValue() {
		int upgradeValue = 0;
		switch (endurance) {
		case E:
			upgradeValue = 10;
			break;
		case D:
			upgradeValue = 20;
			break;
		case C:
			upgradeValue = 30;
			break;
		case B:
			upgradeValue = 40;
			break;
		case A:
			upgradeValue = 50;
			break;
		case S:
			upgradeValue = 100;
			break;
		default:
			break;
		}
		return maxHealth + upgradeValue;
	}
}
