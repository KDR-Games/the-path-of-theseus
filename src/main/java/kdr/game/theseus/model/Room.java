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
 * @author koldavid
 *
 */
public class Room extends Region{
	private int top;
	private int left;
	private int bottom;
	private int right;
	private double centerX;
	private double centerY;
	private int width;
	private int height;
			
	/**
	 * @param top
	 * @param left
	 * @param bottom
	 * @param right
	 */
	public Room(int top, int left, int width, int height) {
		super();
		this.top = top;
		this.left = left;
		this.width = width;
		this.height = height;
		bottom = top + height;
		right = left + width;
		centerX = (left+right)/2.0;
		centerY = (top+bottom)/2.0;
	}

	/**
	 * @return the top
	 */
	public int getTop() {
		return top;
	}

	/**
	 * @return the left
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * @return the bottom
	 */
	public int getBottom() {
		return bottom;
	}

	/**
	 * @return the right
	 */
	public int getRight() {
		return right;
	}
	
	/**
	 * @return the centerX
	 */
	public double getCenterX() {
		return centerX;
	}

	/**
	 * @return the centerY
	 */
	public double getCenterY() {
		return centerY;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	public boolean overlaps(Room room) {
		double minDistY = this.height/2.0 + room.getHeight()/2.0;
		double mindistX = this.width/2.0 + room.getWidth()/2.0;
		double distX = Math.abs(this.centerX - room.getCenterX());
		double distY = Math.abs(this.centerY - room.getCenterY());
		
		return (distX-1 < mindistX) && (distY-1 < minDistY);
	}
	
}
