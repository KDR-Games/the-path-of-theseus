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

import kdr.game.theseus.model.Proficiencies;
import kdr.game.theseus.model.StatValue;
import kdr.game.theseus.model.Stats;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestPlayer {
	
	@Test
	public void testPlayerConstructor() {
		Player player = new Player("name", null, null, true);
		player.setDifficulty(Difficulty.Hard);
		player.setGhostMode(false);
		player.setStats(new Stats(100, StatValue.E, StatValue.E, StatValue.S));
		player.setProficiencies(new Proficiencies(StatValue.E, StatValue.E, StatValue.E));
		
		assertEquals(StatValue.S, StatValue.getNextStatValue(player.getStats().getEndurance()));
		assertEquals(false, player.isGhostMode());
		assertEquals(100, player.getHealth());
		assertEquals(1.0, player.getHealthInPercent()/100.0, 0.001);
		assertEquals(Difficulty.Hard, player.getDifficulty());
		assertEquals(0, player.getDamage());
		assertEquals(16., player.getSpeed(), 0.001);
		assertEquals(0, player.getDistanceTravelled());
		assertEquals(StatValue.E, player.getProficiencies().getSlashing());
		assertEquals(1, player.getFreePoints());
		
		player.upgradeProficiencySlashing();
		
		assertEquals(StatValue.D, player.getProficiencies().getSlashing());
		assertEquals(0, player.getFreePoints());
		assertEquals(0, player.getKills());
		
		player.incrementKills();
		
		assertEquals(1, player.getKills());
		
		player.addToExperience(Constants.XpLevels[1]);
		
		assertEquals(2, player.getLevel());
		assertEquals(1, player.getFreePoints());
	}
}
