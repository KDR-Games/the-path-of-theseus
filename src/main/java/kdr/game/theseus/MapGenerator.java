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
import java.util.Random;

import static kdr.game.theseus.view.Main.logger;

/**
 * A class, containing only static functions, which generate 
 * a randomized tile-based map.
 */
public class MapGenerator {

	/**
	 * Set this number to change the number of the rooms generated.
	 * Note: this number is NOT the number of how many rooms will be generated,
	 * this marks only the attempts of room generations, so if a room overlaps 
	 * another, then that room is dropped and a new attempt to put down a room will be used.
	 */
	static public int roomAttempts = 200;
	
	/**
	 * The number of free tiles to leave in which the character can move.
	 * Use this to control the sparseness of the map.
	 * {@code rows*cols - tilesToLeave - numberOfGeneratedWalls} 
	 * of dead ends will be deleted.
	 */
	static public int tilesToLeave = 1000;

	/**
	 * Marks the different types of neighbors a tile can have.
	 */
	private enum NeighborWallType {
		Top, Left, Bottom, Right, Margin
	}

	/**
	 * A helper function to get the neighboring walls of a Tile.
	 * @param map - the matrix of tiles
	 * @param posX - the X coordinate of the tile which is checked
	 * @param posY - the Y coordinate of the tile which is checked
	 * @return a list of the neighbored walls. An empty list is return if the tile has no neighbored walls.
	 */
	static private ArrayList<NeighborWallType> getNeighboredWalls(Tile[][] map, int posX, int posY) {
		ArrayList<NeighborWallType> neighbors = new ArrayList<NeighborWallType>();
		if((posY > 0) && (posY < map.length-1)) {
			if(map[posY+1][posX].getType() == TileType.Wall) {
				neighbors.add(NeighborWallType.Bottom);
			}
			if(map[posY-1][posX].getType() == TileType.Wall) {
				neighbors.add(NeighborWallType.Top);
			}			
		} else {
			neighbors.add(NeighborWallType.Margin);
		}
		if((posX > 0) && (posX < map[posY].length-1)) {
			if(map[posY][posX+1].getType() == TileType.Wall) {
				neighbors.add(NeighborWallType.Right);
			}
			if(map[posY][posX-1].getType() == TileType.Wall) {
				neighbors.add(NeighborWallType.Left);
			}			
		} else {
			neighbors.add(NeighborWallType.Margin);
		}

		return neighbors;
	}

	/**
	 * A recursive function to generate a simple labyrinth.
	 * @param labyrinth - a new region
	 * @param map - the matrix of the tiles
	 * @param posX - current position X
	 * @param posY - current position Y
	 */
	static private void generateLabyrinth(Region labyrinth, Tile[][] map, int posX, int posY) {
		map[posY][posX].setType(TileType.Floor);
		map[posY][posX].setContainerRegion(labyrinth);
		ArrayList<NeighborWallType> potentialNext = new ArrayList<NeighborWallType>();
		Random rd = new Random();
		do {
			potentialNext.clear();
			for(NeighborWallType n : getNeighboredWalls(map, posX, posY)) {
				switch (n) {
				case Top:
					if (getNeighboredWalls(map, posX, posY-1).size() == 3) {
						potentialNext.add(NeighborWallType.Top);
					}
					break;
				case Left:
					if (getNeighboredWalls(map, posX-1, posY).size() == 3) {
						potentialNext.add(NeighborWallType.Left);
					}
					break;
				case Bottom:
					if (getNeighboredWalls(map, posX, posY+1).size() == 3) {
						potentialNext.add(NeighborWallType.Bottom);
					}
					break;
				case Right:
					if (getNeighboredWalls(map, posX+1, posY).size() == 3) {
						potentialNext.add(NeighborWallType.Right);
					}
					break;

				default:
					break;
				}
			}

			if (!potentialNext.isEmpty()) {
				int randomNext = rd.nextInt(potentialNext.size());
				switch (potentialNext.get(randomNext)) {
				case Top:
					generateLabyrinth(labyrinth, map, posX, posY-1);
					break;

				case Left:
					generateLabyrinth(labyrinth, map, posX-1, posY);
					break;

				case Bottom:
					generateLabyrinth(labyrinth, map, posX, posY+1);
					break;

				case Right:
					generateLabyrinth(labyrinth, map, posX+1, posY);
					break;

				default: 
					break;
				}
			}

		} while(!potentialNext.isEmpty());
	}

	/**
	 * A recursive function to fill the connected tile with the same
	 * {@link kdr.game.theseus.Region}.
	 * @param region - the region
	 * @param tile - the tile to fill
	 */
	static private void floodFill(Region region, Tile tile) {
		tile.setContainerRegion(region);
		if(tile.getNeighbors().getTop() != null)
		{
			if(tile.getNeighbors().getTop().getType() == TileType.Floor && 
					tile.getNeighbors().getTop().getContainerRegion() != region) {
				floodFill(region, tile.getNeighbors().getTop());
			}
		}

		if(tile.getNeighbors().getBottom() != null)
		{
			if(tile.getNeighbors().getBottom().getType() == TileType.Floor && 
					tile.getNeighbors().getBottom().getContainerRegion() != region) {
				floodFill(region, tile.getNeighbors().getBottom());
			}
		}

		if(tile.getNeighbors().getLeft() != null)
		{
			if(tile.getNeighbors().getLeft().getType() == TileType.Floor && 
					tile.getNeighbors().getLeft().getContainerRegion() != region) {
				floodFill(region, tile.getNeighbors().getLeft());
			}
		}

		if(tile.getNeighbors().getRight() != null)
		{
			if(tile.getNeighbors().getRight().getType() == TileType.Floor && 
					tile.getNeighbors().getRight().getContainerRegion() != region) {
				floodFill(region, tile.getNeighbors().getRight());
			}
		}
	}

	/**
	 * Static function which generates a new, randomized map.
	 * @param rows - the height of the map
	 * @param cols - the width of the map
	 * @return the newly generated map.
	 */
	static public Tile[][] getNewMap(int rows, int cols) {
		long startupTime = System.nanoTime();
		logger.info("Generating new map..." +
				"\nWidth: " + cols +
				"\nHeight: " + rows);
		Tile[][] map = new Tile[rows][cols];
		long startTime = System.nanoTime();

		for(int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Tile(TileType.Wall, j, i);
			}
		}

		// Set neighbors
		for(int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(i == 0) {
					if(j == 0) { // TopLeftCorner
						map[i][j].setNeighbors(new Neighbors(map[i][j], map[i+1][j], null, map[i][j+1]));
					} else if(j == map[i].length-1) { // TopRightCorner
						map[i][j].setNeighbors(new Neighbors(map[i][j], map[i+1][j], map[i][j-1], null));
					} else { // TopEdge
						map[i][j].setNeighbors(new Neighbors(null, map[i+1][j], map[i][j-1], map[i][j+1]));
					}
				} else if(i == map.length-1) {
					if(j == 0) { // BottomLeftCorner
						map[i][j].setNeighbors(new Neighbors(map[i-1][j], null, null, map[i][j+1]));
					} else if(j == map[i].length-1) { //BottomRightCorner
						map[i][j].setNeighbors(new Neighbors(map[i-1][j], null, map[i][j-1], null));
					} else { //BottomEdge
						map[i][j].setNeighbors(new Neighbors(map[i-1][j], null, map[i][j-1], map[i][j+1]));							
					}
				} else {
					if(j == 0) { // LeftEdge
						map[i][j].setNeighbors(new Neighbors(map[i-1][j], map[i+1][j], null, map[i][j+1]));
					} else if(j == map[i].length-1) { // RightEdge
						map[i][j].setNeighbors(new Neighbors(map[i-1][j], map[i+1][j], map[i][j-1], null));
					} else { // Middle
						map[i][j].setNeighbors(new Neighbors(map[i-1][j], map[i+1][j], map[i][j-1], map[i][j+1]));						
					}
				}
			}
		}
		
		long endTime = System.nanoTime();
		logger.info("Map initialized. " + ((double)(endTime - startTime)/1000000.0) + "ms");
		startTime = System.nanoTime();

		// Generate rooms
		ArrayList<Region> regions = new ArrayList<Region>();
		Random rd = new Random();
		ArrayList<Room> rooms = new ArrayList<Room>();
		Room mainRoom = null;
		for (int i = 0; i < roomAttempts || rooms.size() < 2; i++) {
			int top = rd.nextInt(rows);
			int left = rd.nextInt(cols);
			int width = rd.nextInt(5) + 2;
			int height = rd. nextInt(5) + 2;
			Room room = new Room(top, left, width, height);
			if((room.getRight() > cols) || (room.getBottom() > rows)) {
				continue;
			} else if(rooms.isEmpty()) {
				rooms.add(room);
				mainRoom = room;
				regions.add(room);
			} else {
				boolean overlaps = false;
				for(Room r : rooms) {
					if(room.overlaps(r)) {
						overlaps = true;
						break;
					}
				}
				if(!overlaps) {
					rooms.add(room);
					regions.add(room);
				}
			}
		}

		// Add generated rooms to the map
		for(Room r : rooms) {
			for (int i = r.getTop(); i < r.getBottom(); i++) {
				for (int j = r.getLeft(); j < r.getRight(); j++) {
					map[i][j].setType(TileType.Floor);
					map[i][j].setContainerRegion(r);
				}
			}
		}

		endTime = System.nanoTime();
		logger.info("Rooms added. "  + ((double)(endTime - startTime)/1000000.0) + "ms" + 
				"\nNumber of rooms: " + rooms.size());
		startTime = System.nanoTime();
		
		// Generate labyrinths
		boolean foundNext = false;
		do {
			foundNext = false;
			int startX = 1, startY = 1;
			for (int i = 1; i < rows - 1; i++) {
				for (int j = 1; j < cols - 1; j++) {
					if (map[i][j].getType() == TileType.Wall && map[i][j].getNeighbourWallNum() == 4) {
						startX = j;
						startY = i;
						foundNext = true;
						break;
					}
				}
				if(foundNext) {
					break;
				}
			}

			Region labyrinth = new Region();
			generateLabyrinth(labyrinth, map, startX, startY);
			regions.add(labyrinth);
		} while(foundNext);

		endTime = System.nanoTime();
		logger.info("Remaining walls carved into labyrinths. " + ((double)(endTime - startTime)/1000000.0) + "ms");
		startTime = System.nanoTime();

		// Look for potential doors and mark them
		ArrayList<Passage> passages = new ArrayList<Passage>();
		for (int i = 1; i < rows - 1; i++) {
			for (int j = 1; j < cols - 1; j++) {
				if ((map[i][j].getType() == TileType.Wall)) {
					if((map[i-1][j].getType() == TileType.Floor) && 
							(map[i+1][j].getType() == TileType.Floor) && 
							(map[i-1][j].getContainerRegion() != map[i+1][j].getContainerRegion())) {
						map[i][j].setType(TileType.PotentialDoor);
						passages.add(new Passage(map[i][j], false));
					} else if ((map[i][j-1].getType() == TileType.Floor) && 
							(map[i][j+1].getType() == TileType.Floor) && 
							(map[i][j-1].getContainerRegion() != map[i][j+1].getContainerRegion())) {
						map[i][j].setType(TileType.PotentialDoor);
						passages.add(new Passage(map[i][j], true));
					}
				}
			}
		}

		endTime = System.nanoTime();
		logger.info("Possible doors found. " + ((double)(endTime - startTime)/1000000.0) + "ms");
		startTime = System.nanoTime();
		
		// Connect regions
		Region mainRegion = mainRoom;
		while(!passages.isEmpty()) {
			ArrayList<Passage> passagesToMainRegion = new ArrayList<Passage>();

			for(Passage p : passages) {
				if((p.getRegionA() == mainRegion) || (p.getRegionB() == mainRegion)) {
					passagesToMainRegion.add(p);
				}
			}
			
			int randIndex = rd.nextInt(passagesToMainRegion.size());
			Passage passage = passagesToMainRegion.get(randIndex);
			Tile door = passagesToMainRegion.get(randIndex).getTile();
			door.setType(TileType.Floor);
			door.setContainerRegion(mainRegion);
			
			floodFill(mainRegion, door);
			
			passages.remove(passage);
			ArrayList<Passage> passagesToRemove = new ArrayList<Passage>();
			for(int i = 0; i < passages.size(); i++) {
				if(passages.get(i).getRegionA() == passages.get(i).getRegionB()) {
					passagesToRemove.add(passages.get(i));
				}
			}
			for(Passage p : passagesToRemove) {
				p.getTile().setType(TileType.Wall);
				passages.remove(p);
			}
		}
		
		endTime = System.nanoTime();
		logger.info("Regions connected. " + ((double)(endTime - startTime)/1000000.0) + "ms");
		startTime = System.nanoTime();
		
		// delete some of the dead ends
		for(int k = 0; k < rows*cols - tilesToLeave; k++) {
			ArrayList<Tile> deadEnds = new ArrayList<Tile>();
			for(int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if((map[i][j].getType() == TileType.Floor) && (map[i][j].getNeighbourWallNum() == 3)) {
						deadEnds.add(map[i][j]);
					}
				}
			}
			
			if(!deadEnds.isEmpty()) {
				int indexToRemove = rd.nextInt(deadEnds.size());
				deadEnds.get(indexToRemove).setType(TileType.Wall);
				deadEnds.get(indexToRemove).setContainerRegion(null);
			} else {
				break;
			}
		}
		
		endTime = System.nanoTime();
		logger.info("Dead ends uncarved. " + ((double)(endTime - startTime)/1000000.0) + "ms");
		startTime = System.nanoTime();
		
		// look for an entrance and exit
		ArrayList<Tile> potentialEntrances = new ArrayList<Tile>();
		int k = 0;
		while(potentialEntrances.isEmpty()) {
			for(int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if(i == k || i == map.length-1-k || j==k || j==map[i].length-1-k)
					{
						if((map[i][j].getType() == TileType.Floor) && (map[i][j].getNeighbourWallNum() == 1)) {
							potentialEntrances.add(map[i][j]);						
						}
					}
				}
			}
			k++;
		}
		int entranceIndex = rd.nextInt(potentialEntrances.size());
		potentialEntrances.get(entranceIndex).setType(TileType.Entrance);
		Tile entranceTile = potentialEntrances.get(entranceIndex);
		
		endTime = System.nanoTime();
		logger.info("Entrance found. " + ((double)(endTime - startTime)/1000000.0) + "ms");
		startTime = System.nanoTime();
		
		int exitIndex = 0;
		Tile exitTile = null;
		do {
			logger.info("Looking for exit...");
			exitIndex = rd.nextInt(potentialEntrances.size());
			exitTile = potentialEntrances.get(exitIndex);
		} while((entranceTile.x() == exitTile.x()) || 
				(entranceTile.y() == exitTile.y()));
		potentialEntrances.get(exitIndex).setType(TileType.Exit);
		
		endTime = System.nanoTime();
		logger.info("Exit found. " + ((double)(endTime - startTime)/1000000.0) + "ms");
		logger.info("Map is succesfully generated." + 
				"\nElapsed time: " + ((double)(endTime - startupTime)/1000000.0) + "ms");

		return map;
	}
}
