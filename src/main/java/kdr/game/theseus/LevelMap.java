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
 * @author koldavid
 *
 */
public class LevelMap {

	private Tile[][] tiles;
	private Tile entrance;
	private int size;
	
	/**
	 * 
	 */
	public LevelMap(int size) {
		super();
		this.size = size;
		int marginSize = (int)(Constants.ObservableMapSize/2);
		tiles = new Tile[size + marginSize*2][size + marginSize*2];
		
		Tile map[][] = MapGenerator.getNewMap(size, size);
		
		// copy the generated map and surround it with a margin
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				if (i < marginSize || i >= size + marginSize ||
				    j < marginSize || j >= size + marginSize) {
					tiles[i][j] = new Tile(TileType.Margin, j, i);
				} else {
					tiles[i][j] = new Tile(map[i-marginSize][j-marginSize].getType(), j, i);
					if(tiles[i][j].getType() == TileType.Entrance) {
						entrance = tiles[i][j];
					}
				}
			}
		}
		
		// set the neighbors
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j <tiles[i].length; j++) {
				if(i == 0) {
					if(j == 0) { // TopLeftCorner
						tiles[i][j].setNeighbors(new Neighbors(tiles[i][j], tiles[i+1][j], null, tiles[i][j+1]));
					} else if(j == tiles[i].length-1) { // TopRightCorner
						tiles[i][j].setNeighbors(new Neighbors(tiles[i][j], tiles[i+1][j], tiles[i][j-1], null));
					} else { // TopEdge
						tiles[i][j].setNeighbors(new Neighbors(null, tiles[i+1][j], tiles[i][j-1], tiles[i][j+1]));
					}
				} else if(i == tiles.length-1) {
					if(j == 0) { // BottomLeftCorner
						tiles[i][j].setNeighbors(new Neighbors(tiles[i-1][j], null, null, tiles[i][j+1]));
					} else if(j == tiles[i].length-1) { //BottomRightCorner
						tiles[i][j].setNeighbors(new Neighbors(tiles[i-1][j], null, tiles[i][j-1], null));
					} else { //BottomEdge
						tiles[i][j].setNeighbors(new Neighbors(tiles[i-1][j], null, tiles[i][j-1], tiles[i][j+1]));							
					}				
				} else {
					if(j == 0) { // LeftEdge
						tiles[i][j].setNeighbors(new Neighbors(tiles[i-1][j], tiles[i+1][j], null, tiles[i][j+1]));
					} else if(j == tiles[i].length-1) { // RightEdge
						tiles[i][j].setNeighbors(new Neighbors(tiles[i-1][j], tiles[i+1][j], tiles[i][j-1], null));
					} else { // Middle
						tiles[i][j].setNeighbors(new Neighbors(tiles[i-1][j], tiles[i+1][j], tiles[i][j-1], tiles[i][j+1]));						
					}				
				}
			}
		}
	}

	/**
	 * @return the tiles
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * @param tiles the tiles to set
	 */
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the entrance
	 */
	public Tile getEntrance() {
		return entrance;
	}

	/**
	 * @param entrance the entrance to set
	 */
	public void setEntrance(Tile entrance) {
		this.entrance = entrance;
	}

	
}
