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
 * Class representing the different stats in proficiencies.
 * @see kdr.game.theseus.model.Stats
 * @see kdr.game.theseus.model.StatValue
 */
public class Proficiencies {
	private StatValue slashing;
	private StatValue piercing;
	private StatValue blunt;
	
	/**
	 * Creates a new instance of {@link Proficiencies} with the given parameters.
	 * If a parameter is <code>null</code>, then 
	 * {@link kdr.game.theseus.model.StatValue#None} is assigned to it.
	 * @param slashing - proficiency in slashing
	 * @param piercing - proficiency in piercing
	 * @param blunt - proficiency in blunt
	 */
	public Proficiencies(StatValue slashing, StatValue piercing, StatValue blunt) {
		super();
		if(slashing != null) {
			this.slashing = slashing;
		} else {
			this.slashing = StatValue.None;
		}
		
		if(piercing != null) {
			this.piercing = piercing;
		} else {
			this.piercing = StatValue.None;
		}
		
		if(blunt != null) {
			this.blunt = blunt;
		} else {
			this.blunt = StatValue.None;
		}
	}
	
	/**
	 * Proficiency in weapons with type of 
	 * {@link kdr.game.theseus.model.DamageType#Slashing}.
	 * @return the slashing
	 */
	public StatValue getSlashing() {
		return slashing;
	}
	
	/**
	 * Proficiency in weapons with type of 
	 * {@link kdr.game.theseus.model.DamageType#Piercing}.
	 * @return the piercing
	 */
	public StatValue getPiercing() {
		return piercing;
	}
	
	/**
	 * Proficiency in weapons with type of 
	 * {@link kdr.game.theseus.model.DamageType#Blunt}.
	 * @return the blunt
	 */
	public StatValue getBlunt() {
		return blunt;
	}
	
	/**
	 * Upgrades proficiency in slashing.
	 * If it is already at maximum level, then nothing happens.
	 * @see #getSlashing()
	 */
	public void upgradeSlashing(){
		slashing = StatValue.getNextStatValue(slashing);
	}
	
	/**
	 * Upgrades proficiency in piercing.
	 * If it is already at maximum level, then nothing happens.
	 * @see #getPiercing()
	 */
	public void upgradePiercing(){
		piercing = StatValue.getNextStatValue(piercing);
	}
	
	/**
	 * Upgrades proficiency in blunt.
	 * If it is already at maximum level, then nothing happens.
	 * @see #getBlunt()
	 */
	public void upgradeBlunt(){
		blunt = StatValue.getNextStatValue(blunt);
	}
}
