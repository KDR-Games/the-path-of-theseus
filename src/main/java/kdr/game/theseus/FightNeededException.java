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

/**
 * FightNeededException.
 */
public class FightNeededException extends Exception {
	
	private static final long serialVersionUID = 7829883505463652907L;
	private Creature creatureA;
	private Creature creatureB;
	private Tile tile;
	
	public FightNeededException(Creature A, Creature B, Tile tile) {
		this.creatureA = A;
		this.creatureB = B;
		this.tile = tile;
	}

	/**
	 * @return the creatureA
	 */
	public Creature getCreatureA() {
		return creatureA;
	}

	/**
	 * @return the creatureB
	 */
	public Creature getCreatureB() {
		return creatureB;
	}

	/**
	 * @return the tile
	 */
	public Tile getTile() {
		return tile;
	}
}
