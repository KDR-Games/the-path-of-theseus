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

import kdr.game.theseus.model.Proficiencies;

/**
 * Stat values in a predefined order:<br>
 * {@code E < D < C < B < A < S}.<br>
 * 
 * @see kdr.game.theseus.Stats
 * @see kdr.game.theseus.model.Proficiencies
 */
public enum StatValue {
	/**
	 * No value.
	 */
	None,
	
	/**
	 * Lowest possible value, that has an effect. 
	 * Starting value for stats.
	 */
	E, 
	
	/**
	 * A little better then E.
	 */
	D, 
	
	/**
	 * Higher than E and D.
	 */
	C, 
	
	/**
	 * Higher than E, D and C.
	 */
	B,
	
	/**
	 * Higher than E, D, C and B.
	 */
	A, 
	
	/**
	 * The highest possible value for now. 
	 * Maybe an SS and SSS will be introduced in the future.
	 */
	S;
	
	/**
	 * Static helper function to determine the next value of a stat,
	 * depending on this enum. It doesn't wrap around, so if <br>{@code stat == StatValue.S}<br> then 
	 * the highest value is returned.
	 * @param stat - the stat value
	 * @return the next stat value
	 */
	public static StatValue getNextStatValue(StatValue stat) {
		switch (stat) {
		case E:
			return StatValue.D;
		case D:
			return StatValue.C;
		case C:
			return StatValue.B;
		case B:
			return StatValue.A;
		case A:
		case S:
		default:
			return StatValue.S;
		}
	}
	
	/**
	 * 
	 * @return true if this stat is at maximum level, 
	 * else false.
	 */
	public boolean isMax() {
		return this.equals(S);
	}
}
