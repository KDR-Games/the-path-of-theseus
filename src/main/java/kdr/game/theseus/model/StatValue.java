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
 * Stat values in a predefined order:<br>
 * {@code E < D < C < B < A < S}.<br>
 * This enum is mainly used by {@link kdr.game.theseus.model.Stats}.
 * 
 * @see kdr.game.theseus.model.Stats
 */
public enum StatValue {
	E, D, C, B, A, S;
	
	/**
	 * Static helper function to determine the next value of a stat,
	 * depending on this enum. It doesn't wrap around, so if <br>{@code stat == StatValue.S}<br> then 
	 * {@link StatValue#S} is returned.
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
}
