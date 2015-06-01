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

import kdr.game.theseus.model.ChestArmor;
import kdr.game.theseus.model.DamageType;
import kdr.game.theseus.model.HandArmor;
import kdr.game.theseus.model.HeadArmor;
import kdr.game.theseus.model.Item;
import kdr.game.theseus.model.LegArmor;
import kdr.game.theseus.model.OneHanded;
import kdr.game.theseus.model.Shield;
import kdr.game.theseus.model.ShieldType;
import kdr.game.theseus.model.TwoHanded;

import org.junit.Test;
import static org.junit.Assert.*;

public class TileTest {
	
	@Test
	public void testTileConstructor() {
		Tile tile = new Tile(null, 1, 0);
		assertEquals(TileType.Floor, tile.getType());
		assertEquals(1, tile.x());
		assertEquals(0, tile.y());
		assertEquals(Visibility.NotVisible, tile.getVisibility());
		
		tile.setVisibility(Visibility.Visible);
		assertEquals(Visibility.Visible, tile.getVisibility());
		
		Creature player = new Player("asd",null, Difficulty.Easy, false);
		tile.setCreature(player);
		assertEquals(player, tile.getCreature());
		
		Shield shield = new Shield("shield", null, ShieldType.Medium, 1, 1.0);
		OneHanded oneHanded = new OneHanded("one-handed", null, DamageType.Slashing, 1, 1.0);
		TwoHanded twoHanded = new TwoHanded("two-handed", null, DamageType.Blunt, 1, 1.0);
		
		HeadArmor head = new HeadArmor("helmet", null, 1.0, 1);
		ChestArmor chest = new ChestArmor("chest", null, 1.0, 1);
		LegArmor leg = new LegArmor("leggings", null, 1.0, 1);
		HandArmor hand = new HandArmor("gloves", null, 1.0, 1);
		
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(shield);
		items.add(oneHanded);
		items.add(twoHanded);
		items.add(head);
		items.add(chest);
		items.add(leg);
		items.add(hand);
		
		tile.setItems(items);
		
		assertArrayEquals(items.toArray(), tile.getItems().toArray());
	}
	
	@Test
	public void testSetContainerRegion() {
		Tile tile = new Tile(TileType.Floor, 0, 0);
		Region region1 = new Region();
		Region region2 = new Region();
		
		tile.setContainerRegion(region1);
		assertEquals(region1, tile.getContainerRegion());
		
		tile.setContainerRegion(region2);
		assertEquals(region2, tile.getContainerRegion());
		
		tile.setContainerRegion(null);
		assertEquals(null, tile.getContainerRegion());
	}
	
	@Test
	public void testGetNeighbors() {
		Tile tile = new Tile(TileType.Floor, 1, 1);
		Tile tileLeft = new Tile(TileType.Wall, 1, 0);
		Tile tileRight = new Tile(TileType.Wall, 1, 2);
		Tile tileTop = new Tile(TileType.Wall, 0, 1);
		Tile tileBottom = new Tile(TileType.Wall, 2, 1);
		Tile floor = new Tile(TileType.Floor, 0, 0);
		
		tile.setNeighbors(new Neighbors(floor, floor, floor, floor));
		assertEquals(0, tile.getNeighbourWallNum());
		
		tile.setNeighbors(new Neighbors(floor, floor, floor, tileRight));
		assertEquals(1, tile.getNeighbourWallNum());
		
		tile.setNeighbors(new Neighbors(floor, floor, tileLeft, tileRight));
		assertEquals(2, tile.getNeighbourWallNum());
		
		tile.setNeighbors(new Neighbors(floor, tileBottom, tileLeft, tileRight));
		assertEquals(3, tile.getNeighbourWallNum());
		
		tile.setNeighbors(new Neighbors(tileTop, tileBottom, tileLeft, tileRight));
		assertEquals(4, tile.getNeighbourWallNum());
	}
}
