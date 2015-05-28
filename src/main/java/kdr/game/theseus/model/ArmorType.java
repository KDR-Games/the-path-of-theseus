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
 * Marks the different types an {@link kdr.game.theseus.model.Armor} can have,
 * which class needs to be inherited by classes that describe these different types. 
 */
public enum ArmorType {
	/**
	 * Type name defining a {@link kdr.game.theseus.model.HeadArmor}.
	 */
	Head,
	
	/**
	 * Type name defining a {@link kdr.game.theseus.model.ChestArmor}.
	 */
	Chest,
	
	/**
	 * Type name defining a {@link kdr.game.theseus.model.LegArmor}.
	 */
	Leg,
	
	/**
	 * Type name defining a {@link kdr.game.theseus.model.HandArmor}.
	 */
	Hand;
}
