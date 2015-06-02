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

import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * Monster 
 */
public class Monster {
	private String name;
	private boolean isBoss;
	private Image imageSmall;
	private Image imageBig;
	private Stats stats;
	private Proficiencies proficiency;
	private boolean doubleHanded;
	private ArrayList<Weapon> mainHand = new ArrayList<>();
	private ArrayList<Weapon> offHand = new ArrayList<>();
	private ArrayList<Weapon> doubleHand = new ArrayList<>();
	private ArrayList<HeadArmor> headArmors = new ArrayList<>();
	private ArrayList<ChestArmor> chestArmors = new ArrayList<>();
	private ArrayList<LegArmor> legArmors = new ArrayList<>();
	private ArrayList<HandArmor> handArmors = new ArrayList<>();
	private int droppedXP;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the isBoss
	 */
	public boolean isBoss() {
		return isBoss;
	}
	/**
	 * @param isBoss the isBoss to set
	 */
	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}
	/**
	 * @return the image
	 */
	public Image getImageSmall() {
		return imageSmall;
	}
	/**
	 * @param image the image to set
	 */
	public void setImageSmall(Image imageSmall) {
		this.imageSmall = imageSmall;
	}
	/**
	 * @return the imageBig
	 */
	public Image getImageBig() {
		return imageBig;
	}
	/**
	 * @param imageBig the imageBig to set
	 */
	public void setImageBig(Image imageBig) {
		this.imageBig = imageBig;
	}
	/**
	 * @return the stats
	 */
	public Stats getStats() {
		return stats;
	}
	/**
	 * @param stats the stats to set
	 */
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	/**
	 * @return the proficiency
	 */
	public Proficiencies getProficiency() {
		return proficiency;
	}
	/**
	 * @param proficiency the proficiency to set
	 */
	public void setProficiency(Proficiencies proficiency) {
		this.proficiency = proficiency;
	}
	/**
	 * @return the doubleHanded
	 */
	public boolean isDoubleHanded() {
		return doubleHanded;
	}
	/**
	 * @param doubleHanded the doubleHanded to set
	 */
	public void setDoubleHanded(boolean doubleHanded) {
		this.doubleHanded = doubleHanded;
	}
	/**
	 * @return the mainHand
	 */
	public ArrayList<Weapon> getMainHand() {
		return mainHand;
	}
	/**
	 * @param mainHand the mainHand to set
	 */
	public void setMainHand(ArrayList<Weapon> mainHand) {
		this.mainHand = mainHand;
	}
	/**
	 * @return the offHand
	 */
	public ArrayList<Weapon> getOffHand() {
		return offHand;
	}
	/**
	 * @param offHand the offHand to set
	 */
	public void setOffHand(ArrayList<Weapon> offHand) {
		this.offHand = offHand;
	}
	/**
	 * @return the doubleHand
	 */
	public ArrayList<Weapon> getDoubleHand() {
		return doubleHand;
	}
	/**
	 * @param doubleHand the doubleHand to set
	 */
	public void setDoubleHand(ArrayList<Weapon> doubleHand) {
		this.doubleHand = doubleHand;
	}
	/**
	 * @return the headArmors
	 */
	public ArrayList<HeadArmor> getHeadArmors() {
		return headArmors;
	}
	/**
	 * @param headArmors the headArmors to set
	 */
	public void setHeadArmors(ArrayList<HeadArmor> headArmors) {
		this.headArmors = headArmors;
	}
	/**
	 * @return the chestArmors
	 */
	public ArrayList<ChestArmor> getChestArmors() {
		return chestArmors;
	}
	/**
	 * @param chestArmors the chestArmors to set
	 */
	public void setChestArmors(ArrayList<ChestArmor> chestArmors) {
		this.chestArmors = chestArmors;
	}
	/**
	 * @return the legArmors
	 */
	public ArrayList<LegArmor> getLegArmors() {
		return legArmors;
	}
	/**
	 * @param legArmors the legArmors to set
	 */
	public void setLegArmors(ArrayList<LegArmor> legArmors) {
		this.legArmors = legArmors;
	}
	/**
	 * @return the handArmors
	 */
	public ArrayList<HandArmor> getHandArmors() {
		return handArmors;
	}
	/**
	 * @param handArmors the handArmors to set
	 */
	public void setHandArmors(ArrayList<HandArmor> handArmors) {
		this.handArmors = handArmors;
	}
	/**
	 * @return the droppedXP
	 */
	public int getDroppedXP() {
		return droppedXP;
	}
	/**
	 * @param droppedXP the droppedXP to set
	 */
	public void setDroppedXP(int droppedXP) {
		this.droppedXP = droppedXP;
	}
	
	
}
