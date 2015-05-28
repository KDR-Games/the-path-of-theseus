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
 * The neighbors of a {@link kdr.game.theseus.model.Tile}.
 * This can be interpreted as a 4-way linked list.
 * 
 * @see kdr.game.theseus.model.Tile
 */
public class Neighbors {
	private Tile top;
	private Tile bottom;
	private Tile left;
	private Tile right;
	
	/**
	 * Sets the neighbors of a {@link kdr.game.theseus.model.Tile}.
	 * @param top - the upper neighbored tile
	 * @param bottom - the bottom neighbored tile
	 * @param left - the left neighbored tile
	 * @param right - the right neighbored tile
	 */
	public Neighbors(Tile top, Tile bottom, Tile left, Tile right) {
		super();
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * @return the top
	 */
	public Tile getTop() {
		return top;
	}
	/**
	 * @return the bottom
	 */
	public Tile getBottom() {
		return bottom;
	}
	/**
	 * @return the left
	 */
	public Tile getLeft() {
		return left;
	}
	/**
	 * @return the right
	 */
	public Tile getRight() {
		return right;
	}
}
