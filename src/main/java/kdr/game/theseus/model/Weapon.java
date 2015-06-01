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
 * The main class for weapons. This class can't be used directly.
 * Other classes are derived from this.
 * @see kdr.game.theseus.model.OneHanded
 * @see kdr.game.theseus.model.TwoHanded
 * @see kdr.game.theseus.model.Shield
 * @see kdr.game.theseus.model.WeaponType
 */
public class Weapon extends Wearable {
	protected WeaponType weaponType;
	protected int damage;

	/**
	 * The constructor is protected. You can't use this 
	 * class directly. It is used only for type-casting.
	 * @param name - name
	 * @param image - image
	 * @param damage - damage
	 * @param speed - speed
	 * @see kdr.game.theseus.model.OneHanded
	 * @see kdr.game.theseus.model.TwoHanded
	 * @see kdr.game.theseus.model.Shield
	 * @see kdr.game.theseus.model.WeaponType
	 */
	protected Weapon(String name, Image image, int damage, double speed) {
		super(name, image, speed);
		this.damage = damage;
		this.wearableType = WearableType.Weapon;
	}

	/**
	 * @return the weapon type
	 */
	public WeaponType getWeaponType() {
		return weaponType;
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

}
