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

import java.util.Random;

import javafx.scene.image.Image;
import kdr.game.theseus.model.ChestArmor;
import kdr.game.theseus.model.Equipment;
import kdr.game.theseus.model.HandArmor;
import kdr.game.theseus.model.HeadArmor;
import kdr.game.theseus.model.LegArmor;
import kdr.game.theseus.model.Monster;
import kdr.game.theseus.model.OneHanded;
import kdr.game.theseus.model.Weapon;

/**
 * Enemies can drop xp, when they die.
 * 
 * @see kdr.game.theseus.Creature
 * @see kdr.game.theseus.Player
 */
public class Enemy extends Creature {

	private int droppedXp;

	/**
	 * @param name - the name of the monster
	 */
	public Enemy(String name, Image image) {
		super(name, image);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param monster - the Monster from which an enemy is randomly generated
	 */
	public Enemy(Monster monster) {
		super(monster.getName(), monster.getImageSmall());
		this.droppedXp = monster.getDroppedXP();
		this.stats = monster.getStats();
		this.proficiencies = monster.getProficiency();
		this.setHealthToMax();
		Equipment equipment = new Equipment();
		Random rd = new Random();
		if(monster.isDoubleHanded()) {
			if(!monster.getDoubleHand().isEmpty()) {
				int index = rd.nextInt(monster.getDoubleHand().size());
				Weapon doubleHandedWeapon = monster.getDoubleHand().get(index);
				equipment.setMainHandWeapon(doubleHandedWeapon);
			}
		} else {
			if(!monster.getMainHand().isEmpty()) {
				int index = rd.nextInt(monster.getMainHand().size());
				Weapon mainHandWeapon = monster.getMainHand().get(index);
				equipment.setMainHandWeapon(mainHandWeapon);
			}
			if(!monster.getOffHand().isEmpty()) {
				int index = rd.nextInt(monster.getOffHand().size());
				Weapon offHandWeapon = monster.getOffHand().get(index);
				equipment.setOffHandWeapon(offHandWeapon);
			}
		}
		if(!monster.getHeadArmors().isEmpty()) {
			int index = rd.nextInt(monster.getHeadArmors().size());
			HeadArmor headArmor = monster.getHeadArmors().get(index);
			equipment.setHeadArmor(headArmor);
		}
		if(!monster.getChestArmors().isEmpty()) {
			int index = rd.nextInt(monster.getChestArmors().size());
			ChestArmor chestArmor = monster.getChestArmors().get(index);
			equipment.setChestArmor(chestArmor);
		}
		if(!monster.getLegArmors().isEmpty()) {
			int index = rd.nextInt(monster.getLegArmors().size());
			LegArmor legArmor = monster.getLegArmors().get(index);
			equipment.setLegArmor(legArmor);
		}
		if(!monster.getHandArmors().isEmpty()) {
			int index = rd.nextInt(monster.getHandArmors().size());
			HandArmor handArmor = monster.getHandArmors().get(index);
			equipment.setHandArmor(handArmor);
		}
		this.equipment = equipment;
	}

	/**
	 * @return the droppedXp
	 */
	public int getDroppedXp() {
		return droppedXp;
	}

	/**
	 * @param droppedXp - the droppedXp to set
	 */
	public void setDroppedXp(int droppedXp) {
		this.droppedXp = droppedXp;
	}

}
