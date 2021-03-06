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
 * Constants used in the program to avoid "magic numbers".
 *
 */
public class Constants {
	public static final int ButtonSize = 48;
	public static final int ObservableMapSize = 11;
	public static final int MapSize = 64;
	public static final int MapSizeBoss = 24;
	public static final int XpLevels[] = 
		{50, 75, 100, 140, 180, 
		 225, 280, 350, 430, 525,
		 625, 750, 875, 1000, 1150,
		 1300, 1500, 1700, 1950, 2200,
		 2500, 3000, 4000, 5000, 6000,
		 7000, 8000, 9000, 10000, 11000,
		 12000, 13000, 14000, 15000, 16000};
	public static final String playerImage = "player.png";
	public static final String MonstersLocation = "/image/monsters/";
	public static final String ArmorsLocation = "/image/armors/";
	public static final String WeaponsLocation = "/image/weapons/";
	public static final String ShieldsLocation = "/image/weapons/shields/";
}
