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

import javafx.scene.image.Image;

/**
 * Shield 
 */
public class Shield extends Weapon {
	private ShieldType shieldType;
	private int defense;
	
	
	/**
	 * @param name - name
	 * @param image - image
	 * @param speed - speed
	 * @param shieldType - the type of the shield
	 * @param defense - defense
	 */
	public Shield(String name, Image image, ShieldType shieldType,
			int defense, double speed) {
		super(name, image, 0, speed);
		this.shieldType = shieldType;
		this.defense = defense;
		this.weaponType = WeaponType.Shield;
	}
	
	/**
	 * @return the shieldType
	 */
	public ShieldType getShieldType() {
		return shieldType;
	}
	
	/**
	 * @return the defense
	 */
	public int getDefense() {
		return defense;
	}
	
	
}
