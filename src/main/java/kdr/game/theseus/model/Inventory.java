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
 * This class is used to store the different {@link kdr.game.theseus.Item}s that the player can have.
 */
public class Inventory {
	private Item[] items;
	private int inventorySize;
	
	/**
	 * @param inventorySize
	 */
	public Inventory(int inventorySize) {
		super();
		this.inventorySize = inventorySize;
	}

	/**
	 * @return the items
	 */
	public Item[] getItems() {
		return items;
	}

	/**
	 * @return the inventorySize
	 */
	public int getInventorySize() {
		return inventorySize;
	}
	
	public Item getItemAt(int index) {
		return items[index];
	}
	
	public void setItemAt(Item item, int index) {
		items[index] = item;
	}
}
