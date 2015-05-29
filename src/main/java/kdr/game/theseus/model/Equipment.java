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

public class Equipment {
	private boolean isTwoHanded;
	private boolean hasShield;
	private Weapon mainHandWeapon;
	private Weapon offHandWeapon;
	private HeadArmor headArmor;
	private ChestArmor chestArmor;
	private LegArmor legArmor;
	private HandArmor handArmor;
	
	/**
	 * Every {@link kdr.game.theseus.Creature} starts
	 * with an empty equipment. {@link kdr.game.theseus.model.Wearable}s
	 * are added later through the setter functions.
	 */
	public Equipment() {
		super();
		isTwoHanded = false;
		hasShield = false;
		mainHandWeapon = null;
		offHandWeapon = null;
		headArmor = null;
		chestArmor = null;
		legArmor = null;
		handArmor = null;
	}
	
	/**
	 * @return the hasShield
	 */
	public boolean hasShield() {
		return hasShield;
	}
	
	/**
	 * @return the isDoubleHanded
	 */
	public boolean isTwoHanded() {
		return isTwoHanded;
	}
	
	/**
	 * @return the mainHandWeapon
	 */
	public Weapon getMainHandWeapon() {
		return mainHandWeapon;
	}
	
	/**
	 * @param mainHandWeapon the mainHandWeapon to set
	 * @return the unequipped weapon
	 */
	public Weapon setMainHandWeapon(Weapon mainHandWeapon) {
		Weapon previous = this.mainHandWeapon;
		this.mainHandWeapon = mainHandWeapon;
		if(mainHandWeapon != null) {
			isTwoHanded = mainHandWeapon.getWeaponType() == WeaponType.TwoHanded;
		} else {
			isTwoHanded = false;
		}
		return previous;
	}
	
	/**
	 * @return the offHandWeapon
	 */
	public Weapon getOffHandWeapon() {
		return offHandWeapon;
	}
	
	/**
	 * @param offHandWeapon the offHandWeapon to set
	 * @return the unequipped weapon
	 */
	public Weapon setOffHandWeapon(Weapon offHandWeapon) {
		Weapon previous = this.offHandWeapon;
		this.offHandWeapon = offHandWeapon;
		if(offHandWeapon != null) {
			hasShield = offHandWeapon.getWeaponType() == WeaponType.Shield;
		} else {
			hasShield = false;
		}
		return previous;
	}
	
	/**
	 * @return the headArmor
	 */
	public HeadArmor getHeadArmor() {
		return headArmor;
	}
	
	/**
	 * @param headArmor the headArmor to set
	 * @return the unequipped armor
	 */
	public HeadArmor setHeadArmor(HeadArmor headArmor) {
		HeadArmor previous = this.headArmor;
		this.headArmor = headArmor;
		return previous;
	}
	
	/**
	 * @return the chestArmor
	 */
	public ChestArmor getChestArmor() {
		return chestArmor;
	}
	
	/**
	 * @param chestArmor the chestArmor to set
	 * @return the unequipped armor
	 */
	public ChestArmor setChestArmor(ChestArmor chestArmor) {
		ChestArmor previous = this.chestArmor;
		this.chestArmor = chestArmor;
		return previous;
	}
	
	/**
	 * @return the legArmor
	 */
	public LegArmor getLegArmor() {
		return legArmor;
	}
	
	/**
	 * @param legArmor the legArmor to set
	 * @return the unequipped armor
	 */
	public LegArmor setLegArmor(LegArmor legArmor) {
		LegArmor previous = this.legArmor;
		this.legArmor = legArmor;
		return previous;
	}
	
	/**
	 * @return the handArmor
	 */
	public HandArmor getHandArmor() {
		return handArmor;
	}
	
	/**
	 * @param handArmor the handArmor to set
	 * @return the unequipped armor
	 */
	public HandArmor setHandArmor(HandArmor handArmor) {
		HandArmor previous = this.handArmor;
		this.handArmor = handArmor;
		return previous;
	}

}
