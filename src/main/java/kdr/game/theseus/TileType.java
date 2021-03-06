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
 * Different types that a {@link kdr.game.theseus.Tile} can have. Each tile can have 
 * different role than wall or floor, so in order to let the map have an entrance
 * or doors, these types are used.
 */
public enum TileType {
	
	/**
	 * You can freely walk on this, monsters can spawn here.
	 */
	Floor,
	
	/**
	 * The player can't pass through a wall, monsters can't spawn here. 
	 */
	Wall,
	
	/**
	 * Not implemented yet. Maybe it will do some bad things 
	 * to the creatures which step on this tile.
	 */
	Trap,
	
	/**
	 * Only for graphics for now.
	 */
	Door,
	
	/**
	 * This is the margin of the map, nothing can happen here.
	 */
	Margin,
	
	/**
	 * The player spawns here.
	 */
	Entrance,
	
	/**
	 * This type is only used while generating the map, 
	 * alongside with {@link kdr.game.theseus.Passage}
	 */
	PotentialDoor,
	
	/**
	 * The exit of the map. If a player steps on this tile, a 
	 * {@link kdr.game.theseus.ExitReachedException} is thrown.
	 */
	Exit;
	
	public boolean isFree() {
		switch(this) {
		case Floor:
		case Entrance:
		case Exit:
			return true;

		default:
			return false;
		}
	}
}
