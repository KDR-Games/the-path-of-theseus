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
 * Marks the main types of weapon in the game.
 * @see kdr.game.theseus.model.Weapon
 */
public enum WeaponType {
	/**
	 * It can be equipped in the main hand and off hand too.
	 * @see kdr.game.theseus.model.OneHanded
	 */
	OneHanded, 
	
	/**
	 * It takes each hand to use. You can't use another weapon in the off hand.
	 * @see kdr.game.theseus.model.TwoHanded
	 */
	TwoHanded, 
	
	/**
	 * It can be equipped only in the off hand. You can't use a two-handed weapon with a shield.
	 * @see kdr.game.theseus.model.Shield
	 */
	Shield
}
