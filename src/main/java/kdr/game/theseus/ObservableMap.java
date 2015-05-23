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
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

/**
 * @author koldavid
 *
 */
public class ObservableMap {
	private LevelMap currentLevel;
	private Tile[][] tiles;
	private Button[][] buttons;
	private Tile centerTile;
	private Difficulty difficulty;
	private final int center = (int)(Constants.ObservableMapSize/2);

	public ObservableMap() {
		super();
		tiles = new Tile[Constants.ObservableMapSize][Constants.ObservableMapSize];
		buttons = new Button[Constants.ObservableMapSize][Constants.ObservableMapSize];
		difficulty = Difficulty.Normal;
	}

	private void updateMap() {
		for(int i = 0; i < Constants.ObservableMapSize; i++) {
			for(int j = 0; j < Constants.ObservableMapSize; j++) {
				int col = centerTile.x() - Constants.ObservableMapSize/2 + j;
				int row = centerTile.y() - Constants.ObservableMapSize/2 + i;
				tiles[i][j] = currentLevel.getTiles()[row][col];
			}
		}
		updateVisibility();
		updateButtons();
	}

	private void updateButtons() {
		for(int i = 0; i < Constants.ObservableMapSize; i++) {
			for(int j = 0; j < Constants.ObservableMapSize; j++) {
				buttons[i][j].getStyleClass().clear();
				buttons[i][j].getStyleClass().add("tile");
				switch (tiles[i][j].getType()) {
				case Floor:
					buttons[i][j].getStyleClass().add("tile-floor");
					break;

				case Wall:
					buttons[i][j].getStyleClass().add("tile-wall");
					break;

				case Door:
					buttons[i][j].getStyleClass().add("tile-door");
					break;

				case Margin:
					buttons[i][j].getStyleClass().add("tile-margin");
					break;

				case Trap:
					buttons[i][j].getStyleClass().add("tile-trap");
					break;

				case Entrance:
					buttons[i][j].getStyleClass().add("tile-entrance");
					break;

				case PotentialDoor:
					buttons[i][j].getStyleClass().add("tile-door");
					break;

				default:
					break;
				}

				switch (tiles[i][j].getVisibility()) {
				case Visible:
					buttons[i][j].getStyleClass().add("tile-visibility-visible");
					break;
				case Dim:
					buttons[i][j].getStyleClass().add("tile-visibility-dim");
					break;
				case NotVisible:
					buttons[i][j].getStyleClass().clear();
					buttons[i][j].getStyleClass().add("tile");
					buttons[i][j].getStyleClass().add("tile-visibility-not_visible");
					break;
				default:
					break;
				}

			}
		}
	}

	private void updateVisibility() {
		for(int i = 0; i < Constants.ObservableMapSize; i++) {
			for(int j = 0; j < Constants.ObservableMapSize; j++) {
				boolean isVisible = true;
				ArrayList<Tile> line = traceLineFromCenterTo(j, i);
				for(int k = line.size()-1; k > 0; k--) {
					if(!isFreeTile(line.get(k)) && (k != line.size()-1)) {
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
							if ((!isFreeTile(t) && !isFreeTile(r))) {
								isVisible = false;
							}
							break;
						case 2:
							if ((!isFreeTile(t) && !isFreeTile(l))) {
								isVisible = false;
							}
							break;
						case 3:
							if ((!isFreeTile(b) && !isFreeTile(l))) {
								isVisible = false;
							}
							break;
						case 4:
							if ((!isFreeTile(b) && !isFreeTile(r))) {
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

	private ArrayList<Tile> traceLineFromCenterTo(int x1, int y1) {
		int x0 = 0;
		int y0 = 0;

		// invert Y axis
		y1 = (Constants.ObservableMapSize-1) - y1;
		// translate center to (0,0)
		x1 = x1 - center;
		y1 = y1 - center;

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
		int row = (Constants.ObservableMapSize-1) - ((int)p.getY() + center);
		int col = (int)p.getX()+ center;
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
			row = (Constants.ObservableMapSize-1) - ((int)p.getY() + center);
			col = (int)p.getX()+ center;
			ret.add(tiles[row][col]);
		}

		return ret;
	}

	private Point2D switchOctantToZeroFrom(int octant, Point2D p) {
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

	private Point2D switchOctantFromZeroTo(int octant, Point2D p) {
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

	/**
	 * @return the currentLevel
	 */
	public LevelMap getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * @param currentLevel the currentLevel to set
	 */
	public void setCurrentLevel(LevelMap currentLevel) {
		this.currentLevel = currentLevel;
		setCenterTile(currentLevel.getEntrance());
		updateMap();
	}

	/**
	 * @return the buttons
	 */
	public Button[][] getButtons() {
		return buttons;
	}

	/**
	 * @param buttons the buttons to set
	 */
	public void setButtons(Button[][] buttons) {
		this.buttons = buttons;
		for(int i = 0; i < Constants.ObservableMapSize; i++) {
			for(int j = 0; j < Constants.ObservableMapSize; j++) {
				this.buttons[i][j] = buttons[i][j];
			}
		}
		buttons[center][center].setId("player");
		// updateButtons();
	}

	/**
	 * @return the centerTile
	 */
	public Tile getCenterTile() {
		return centerTile;
	}

	/**
	 * @param centerTile the centerTile to set
	 */
	public void setCenterTile(Tile centerTile) {
		this.centerTile = centerTile;
	}

	/**
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty the difficulty to set
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public void move(KeyCode code) {
		switch (code) {
		case LEFT:
			setCenterTile(centerTile.getNeighbors().getLeft());
			System.out.println("left");
			break;

		case RIGHT:
			setCenterTile(centerTile.getNeighbors().getRight());
			System.out.println("right");
			break;

		case UP:
			setCenterTile(centerTile.getNeighbors().getTop());
			System.out.println("up");
			break;

		case DOWN:
			setCenterTile(centerTile.getNeighbors().getBottom());
			System.out.println("down");
			break;

		default:
			break;
		}
		updateMap();
	}

	public boolean canMove(KeyCode code) {		
		switch (code) {
		case LEFT:
			return isFreeTile(centerTile.getNeighbors().getLeft());
		case RIGHT:
			return isFreeTile(centerTile.getNeighbors().getRight());
		case UP:
			return isFreeTile(centerTile.getNeighbors().getTop());
		case DOWN:
			return isFreeTile(centerTile.getNeighbors().getBottom());
		default:
			break;
		}

		return false;
	}

	private boolean isFreeTile(Tile tile) {
		return (tile.getType() == TileType.Floor) || (tile.getType() == TileType.Entrance);
	}
}
