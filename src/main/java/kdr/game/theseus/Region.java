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

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

/**
 * @author koldavid
 *
 */
public class Region {
	private ArrayList<Tile> tiles;
	
	/**
	 * 
	 */
	public Region() {
		super();
		tiles = new ArrayList<Tile>();
	}

	/**
	 * @return the tiles
	 */
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	public void addTile(Tile tile) {
		tiles.add(tile);
	}
	
	public void removeTileAt(int x, int y) throws NoSuchObjectException {
		Tile tileToRemove = null;
		for(Tile tile : tiles) {
			if((tile.x() == x) && (tile.y() == y)) {
				tileToRemove = tile;
			}
		}
		
		if(tileToRemove != null) {
			tiles.remove(tileToRemove);
		} else {
			throw new NoSuchObjectException("There is no tile with index x(" + x + "), y(" + y + ")");
		}
	}
}
