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

import java.util.ArrayList;

import kdr.game.theseus.model.Creature;
import kdr.game.theseus.model.Item;

/**
 * @author koldavid
 *
 */
public class Tile {
	private Neighbors neighbors;
	private Creature creature;
	private ArrayList<Item> items;
	private TileType type;
	private int posX;
	private int posY;
	private Region containerRegion;
	private Visibility visibility;

	/**
	 * 
	 * @param type - the type of the tile
	 * @param posX - the X coordinate of the tile
	 * @param posY - te Y coordinate of the tile
	 */
	public Tile(TileType type, int posX, int posY) {
		super();
		if(type == null) {
			type = TileType.Floor;
		}
		this.type = type;
		this.posX = posX;
		this.posY = posY;
		setVisibility(Visibility.NotVisible);
	}
	
	/**
	 * @return the type
	 */
	public TileType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TileType type) {
		this.type = type;
	}
	
	/**
	 * @return the creature
	 */
	public Creature getCreature() {
		return creature;
	}
	
	/**
	 * @param creature the creature to set
	 */
	public void setCreature(Creature creature) {
		this.creature = creature;
	}
	
	/**
	 * @return the items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	/**
	 * @return the posX
	 */
	public int x() {
		return posX;
	}

	/**
	 * @return the posY
	 */
	public int y() {
		return posY;
	}

	/**
	 * @return the neighbors
	 */
	public Neighbors getNeighbors() {
		return neighbors;
	}

	/**
	 * @param neighbors - the neighbors to set
	 */
	public void setNeighbors(Neighbors neighbors) {
		this.neighbors = neighbors;
	}
	
	/**
	 * @return the containerRegion
	 */
	public Region getContainerRegion() {
		return containerRegion;
	}

	/**
	 * @param containerRegion - the containerRegion to set
	 */
	public void setContainerRegion(Region containerRegion) {
		if(this.containerRegion != null || containerRegion == null) {
			this.containerRegion.getTiles().remove(this);
		}
		if(containerRegion != null) {
			this.containerRegion = containerRegion;
			containerRegion.addTile(this);			
		}
	}

	/**
	 * @return the visibility
	 */
	public Visibility getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility - the visibility to set
	 */
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	/**
	 * Determines the number of the neighboring walls to this tile.
	 * @return a number between 0 and 4
	 */
	public int getNeighbourWallNum() {
		int num = 0;
		if(neighbors.getTop() != null) {
			if(neighbors.getTop().getType() == TileType.Wall) {
				num++;
			}
		} else {
			num++;
		}
		if(neighbors.getBottom() != null) {
			if(neighbors.getBottom().getType() == TileType.Wall) {
				num++;
			}
		} else {
			num++;
		}
		if(neighbors.getLeft() != null) {
			if(neighbors.getLeft().getType() == TileType.Wall) {
				num++;
			}
		} else {
			num++;
		}
		if(neighbors.getRight() != null) {
			if(neighbors.getRight().getType() == TileType.Wall) {
				num++;
			}
		} else {
			num++;
		}
		
		return num;
	}

}
