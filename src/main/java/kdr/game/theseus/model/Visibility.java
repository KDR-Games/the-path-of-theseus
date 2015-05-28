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
 * Visibility of the observable tiles from the player's point of view.
 * Visibility is determined by the difficulty of the game.
 * 
 * @see kdr.game.theseus.ObservableMap
 * @see kdr.game.theseus.model.Player
 * @see kdr.game.theseus.model.Difficulty
 */
public enum Visibility {
	/**
	 * The tile is fully visible.
	 */
	Visible, 
	
	/**
	 * The tile was explored before, but monsters are not visible there.
	 * Not too much functionality for now, but pretty.
	 */
	Dim, 
	
	/**
	 * This tile is not visible at all. At difficulty hard, 
	 * there is no tile with {@link #Dim} visibility, 
	 * only {@link #Visible} and {@link #NotVisible}.
	 */
	NotVisible
}
