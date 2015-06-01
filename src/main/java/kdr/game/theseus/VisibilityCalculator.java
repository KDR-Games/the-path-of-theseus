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

import javafx.geometry.Point2D;

/**
 * VisibilityCalculator 
 */
public class VisibilityCalculator {

	/**
	 * Calculates the visibility of the tiles, based on the location of the character
	 * and the difficulty of the game.
	 */
	
	public static void updateVisibility(Tile[][] tiles, Difficulty difficulty) {
		int center = (int)Constants.ObservableMapSize/2;
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				boolean isVisible = true;
				ArrayList<Tile> line = traceLineFromTo(tiles, center, center, j, i);
				for(int k = line.size()-1; k > 0; k--) {
					if(!line.get(k).isFreeTile() && (k != line.size()-1)) {
						isVisible = false;
					} else {
						int quadrant = 0;
						Tile tileA = line.get(k);
						Tile tileB = line.get(k-1);						
						Tile t  = tileB.getNeighbors().getTop();
						Tile l  = tileB.getNeighbors().getLeft();
						Tile b  = tileB.getNeighbors().getBottom();
						Tile r  = tileB.getNeighbors().getRight();

						if((tileA.x() > tileB.x()) && (tileA.y() < tileB.y())) {
							quadrant = 1;
						} else if((tileA.x() < tileB.x()) && (tileA.y() < tileB.y())) {
							quadrant = 2;
						} else if((tileA.x() < tileB.x()) && (tileA.y() > tileB.y())) {
							quadrant = 3;
						} else if((tileA.x() > tileB.x()) && (tileA.y() > tileB.y())) {
							quadrant = 4;
						}

						switch (quadrant) {
						case 1:
							if (!t.isFreeTile() && !r.isFreeTile()) {
								isVisible = false;
							}
							break;
						case 2:
							if (!t.isFreeTile() && !l.isFreeTile()) {
								isVisible = false;
							}
							break;
						case 3:
							if (!b.isFreeTile() && !l.isFreeTile()) {
								isVisible = false;
							}
							break;
						case 4:
							if (!b.isFreeTile() && !r.isFreeTile()) {
								isVisible = false;
							}
							break;

						default:
							break;
						}
					}
				}

				switch (difficulty) {
				case Easy:
					tiles[i][j].setVisibility(Visibility.Visible);
					break;

				case Normal:
					if(tiles[i][j].getVisibility() == Visibility.Visible) {
						if(!isVisible) {
							tiles[i][j].setVisibility(Visibility.Dim);
						}
					} else {
						if(isVisible) {
							tiles[i][j].setVisibility(Visibility.Visible);	
						}
					}
					break;

				case Hard:
					if(isVisible) {
						tiles[i][j].setVisibility(Visibility.Visible);	
					} else {
						tiles[i][j].setVisibility(Visibility.NotVisible);
					}
					break;

				default:
					break;
				}
			}
		}
	}
	
	/**
	 * Draws a line with Bresanham's line algorithm from the location of the character,
	 * to the given coordinates.
	 * @param x1 - the X coordinate of the tile
	 * @param y1 - the Y coordinate of the tile
	 * @return a list of {@link kdr.game.theseus.Tile} which represents the drawn line.
	 */
	private static ArrayList<Tile> traceLineFromTo(Tile[][] tiles, int fromX, int fromY, int x1, int y1) {
		int x0 = 0;
		int y0 = 0;

		// invert Y axis
		y1 = (Constants.ObservableMapSize-1) - y1;
		// translate center to (0,0)
		x1 = x1 - fromX;
		y1 = y1 - fromY;

		//		 Octants:
		//			  \2|1/
		//			  3\|/0
		//			 ---+---
		//			  4/|\7
		//			  /5|6\

		int octant = 0;
		if((x1 >= 0) && (y1 >= 0)) {
			if(x1 >= y1) {
				octant = 0;
			} else {
				octant = 1;
			}
		} else if((x1 < 0) && (y1 >= 0)) {
			if(-x1 < y1) {
				octant = 2;
			} else {
				octant = 3;
			}
		} else if((x1 < 0) && (y1 < 0)) {
			if(-x1 >= -y1) {
				octant = 4;
			} else {
				octant = 5;
			}
		} else if((x1 >= 0) && (y1 < 0)) {
			if(x1 < -y1) {
				octant = 6;
			} else {
				octant = 7;
			}
		}

		Point2D p = switchOctantToZeroFrom(octant, new Point2D(x1, y1));
		x1 = (int)p.getX();
		y1 = (int)p.getY();

		int dx = x1 - x0;
		int dy = y1 - y0;

		int D = 2*dy - dx;

		ArrayList<Tile> ret = new ArrayList<Tile>();
		p = switchOctantFromZeroTo(0, new Point2D(x0, y0));
		int row = (Constants.ObservableMapSize-1) - ((int)p.getY() + fromY);
		int col = (int)p.getX()+ fromX;
		ret.add(tiles[row][col]);

		int y = y0;		
		for(int x = x0 + 1; x <= x1; x++) {
			if(D > 0) {
				y++;
				D = D + (2*dy - 2*dx);
			} else {
				D = D + 2*dy;
			}

			p = switchOctantFromZeroTo(octant, new Point2D(x, y));
			row = (Constants.ObservableMapSize-1) - ((int)p.getY() + fromY);
			col = (int)p.getX()+ fromX;
			ret.add(tiles[row][col]);
		}

		return ret;
	}
	
	/**
	 * Converts {@code p} from the given octant to octant zero.
	 * Octants:
	 * <pre>
	 *  \2|1/
	 *  3\|/0
	 * ---+---
	 *  4/|\7
	 *  /5|6\
	 * </pre>
	 * @param octant - the octant from which to convert
	 * @param p - the coordinates of the point
	 * @return a {@link javafx.geometry.Point2D} object, which contains the 
	 * coordinates of the transformed point
	 */
	private static Point2D switchOctantToZeroFrom(int octant, Point2D p) {
		int sw;
		int x = (int)p.getX();
		int y = (int)p.getY();
		switch (octant) {
		case 0:
			// x = x;
			// y = y;
			break;
		case 1:
			// (y,x)
			sw = x;
			x = y;
			y = sw;
			break;
		case 2:
			// (y, -x)
			sw = -x;
			x = y;
			y = sw;
			break;
		case 3:
			// (-x, y)
			x = -x;
			break;
		case 4:
			// (-x, -y)
			x = -x;
			y = -y;
			break;
		case 5:
			// (-y, -x)
			sw = -x;
			x = -y;
			y = sw;
			break;
		case 6:
			// (-y, x)
			sw = x;
			x = -y;
			y = sw;
			break;
		case 7:
			// (x, -y)
			y = -y;
			break;

		default:
			break;
		}

		return new Point2D(x,y);
	}
	
	/**
	 * Converts {@code p} from octant zero to the given octant.
	 * Octants:
	 * <pre>
	 *  \2|1/
	 *  3\|/0
	 * ---+---
	 *  4/|\7
	 *  /5|6\
	 * </pre>
	 * @param octant - the octant in which to convert
	 * @param p - the coordinates of the point
	 * @return a {@link javafx.geometry.Point2D} object, which contains the 
	 * coordinates of the transformed point
	 */
	private static Point2D switchOctantFromZeroTo(int octant, Point2D p) {
		int sw;
		int x = (int)p.getX();
		int y = (int)p.getY();
		switch (octant) {
		case 0:
			// x = x;
			// y = y;
			break;
		case 1:
			// (y,x)
			sw = x;
			x = y;
			y = sw;
			break;
		case 6:
			// (y, -x)
			sw = -x;
			x = y;
			y = sw;
			break;
		case 3:
			// (-x, y)
			x = -x;
			break;
		case 4:
			// (-x, -y)
			x = -x;
			y = -y;
			break;
		case 5:
			// (-y, -x)
			sw = -x;
			x = -y;
			y = sw;
			break;
		case 2:
			// (-y, x)
			sw = x;
			x = -y;
			y = sw;
			break;
		case 7:
			// (x, -y)
			y = -y;
			break;

		default:
			break;
		}

		return new Point2D(x,y);
	}

}
