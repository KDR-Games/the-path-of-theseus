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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import kdr.game.theseus.model.Armor;
import kdr.game.theseus.model.ChestArmor;
import kdr.game.theseus.model.HandArmor;
import kdr.game.theseus.model.HeadArmor;
import kdr.game.theseus.model.LegArmor;
import kdr.game.theseus.model.Monster;
import kdr.game.theseus.model.Proficiencies;
import kdr.game.theseus.model.StatValue;
import kdr.game.theseus.model.Stats;
import kdr.game.theseus.model.Weapon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static kdr.game.theseus.view.Main.logger;

/**
 * MonstersDom 
 */
public class MonstersDAO {
	
	private String inputXml;
	
	/**
	 * 
	 * @param inputXml - the file name of the input XML
	 */
	public MonstersDAO(String inputXml) {
		this.inputXml = inputXml;
	}
	
	/**
	 * Reads {@link kdr.game.theseus.model.Monster}s from the input database.
	 * @return the list of monsters
	 */
	public Map<String, Monster> getMonsters() {
		Map<String, Monster> monsters = new HashMap<String, Monster>();
		Map<String,Weapon> weapons = (new WeaponsDAO("/xml/Weapons.xml")).getWeapons();
		Map<String,Armor> armors = (new ArmorsDAO("/xml/Armors.xml")).getArmors();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			dbFactory.setValidating(false);
			DocumentBuilder dbBuilder;
			dbBuilder = dbFactory.newDocumentBuilder();
			dbBuilder.setEntityResolver(new EntityManager());
			Document doc = dbBuilder.parse(this.getClass().getResourceAsStream(inputXml));
            
            logger.info("Parsing {}.", inputXml);
            
            NodeList nList = doc.getElementsByTagName("Monster");
            for (int i = 0; i < nList.getLength(); i++) {
                NodeList mList = nList.item(i).getChildNodes();
                Monster monster = new Monster();
                logger.info("Parsing new monster");
                for (int j = 0; j < mList.getLength(); j++) {
                	Node node = mList.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) node;
                        switch(e.getNodeName()) {
                        case "name":
                        	monster.setName(e.getTextContent());
                        	logger.info("Name: {}", monster.getName());
                        	break;
                        	
                        case "boss":
                        	monster.setBoss(true);
                        	logger.info("This is a boss monster.");
                        	break;
                        	
                        case "image":
                        	NodeList images = e.getChildNodes();
                        	for (int k = 0; k < images.getLength(); k++) {
								switch(images.item(k).getNodeName()) {
								case "full":
									String imageFullSrc = Constants.MonstersLocation + images.item(k).getTextContent();
									logger.info("image-full: {}", imageFullSrc);
									Image imageFull = new Image(this.getClass().getResourceAsStream(imageFullSrc));
									monster.setImageBig(imageFull);
									break;
								case "tile":
									String imageTileSrc = Constants.MonstersLocation + images.item(k).getTextContent();
									logger.info("image-tile: {}", imageTileSrc);
									Image imageTile = new Image(this.getClass().getResourceAsStream(imageTileSrc));
									monster.setImageSmall(imageTile);
									break;
								default:
									break;
								}
							}
                        	break;
                        	
                        case "XP":
                        	int droppedXP = Integer.parseInt(e.getTextContent());
                        	logger.info("dropped XP: {}", droppedXP);
                        	monster.setDroppedXP(droppedXP);
                        	break;
                        	
                        case "attributes":
                        	NodeList attributes = e.getChildNodes();
                        	logger.info("Parsing attributes of this monster.");
                        	for (int k = 0; k < attributes.getLength(); k++) {
								switch(attributes.item(k).getNodeName()) {
								case "stats":
									NodeList stats = attributes.item(k).getChildNodes();
									int health = 0;
									StatValue strength = null;
									StatValue agility = null;
									StatValue endurance = null;
									for (int l = 0; l < stats.getLength(); l++) {
										Node s = stats.item(l);
										switch(stats.item(l).getNodeName()) {
										case "health":
											health = Integer.parseInt(s.getTextContent());
											logger.info("health: {}", health);
											break;
										case "strength":
											strength = StatValue.parseStatValue(s.getTextContent());
											logger.info("strength: {}", strength.toString());
											break;
										case "agility":
											agility = StatValue.parseStatValue(s.getTextContent());
											logger.info("agility: {}", agility.toString());
											break;
										case "endurance":
											endurance = StatValue.parseStatValue(s.getTextContent());
											logger.info("endurance: {}", endurance.toString());
											break;
										default:
											break;
										}
									}
									monster.setStats(new Stats(health, strength, agility, endurance));
									break;
									
								case "proficiency":
									NodeList proficiency = attributes.item(k).getChildNodes();
									for (int l = 0; l < proficiency.getLength(); l++) {
										switch(proficiency.item(l).getNodeName()) {
										case "slashing":
											StatValue slashing = StatValue.parseStatValue(proficiency.item(l).getTextContent());
											logger.info("slashing: {}", slashing.toString());
											monster.setProficiency(new Proficiencies(slashing, null, null));
											break;
										case "piercing":
											StatValue piercing = StatValue.parseStatValue(proficiency.item(l).getTextContent());
											logger.info("piercing: {}", piercing.toString());
											monster.setProficiency(new Proficiencies(null, piercing, null));
											break;
										case "blunt":
											StatValue blunt = StatValue.parseStatValue(proficiency.item(l).getTextContent());
											logger.info("blunt: {}", blunt.toString());
											monster.setProficiency(new Proficiencies(null, null, blunt));
											break;
										case "none":
											logger.info("proficiency none");
											monster.setProficiency(new Proficiencies(null, null, null));
											break;
										default:
											break;
										}
									}
									break;
								default:
									break;
								}
							}
                        	break;
                        	
                        case "equipment":
                        	NodeList wList = e.getElementsByTagName("weapons");
                        	logger.info("Parsing weapons.");
                        	for (int k = 0; k < wList.getLength(); k++) {
								switch(wList.item(k).getNodeName()) {
								case "main-hand":
									NodeList mainHandList = wList.item(k).getChildNodes();
									ArrayList<Weapon> mainHand = new ArrayList<Weapon>();
									for (int l = 0; l < mainHandList.getLength(); l++) {
										String name = mainHandList.item(l).getTextContent();
										mainHand.add(weapons.get(name));
										logger.info("main-hand: {}", name);
									}
									monster.setMainHand(mainHand);
									monster.setDoubleHanded(false);
									break;
								case "off-hand":
									NodeList offHandList = wList.item(k).getChildNodes();
									ArrayList<Weapon> offHand = new ArrayList<Weapon>();
									for (int l = 0; l < offHandList.getLength(); l++) {
										String name = offHandList.item(l).getTextContent();
										offHand.add(weapons.get(name));
										logger.info("off-hand: {}", name);
									}
									monster.setOffHand(offHand);
									break;
								case "double-hand":
									NodeList doubleHandList = wList.item(k).getChildNodes();
									ArrayList<Weapon> doubleHand = new ArrayList<Weapon>();
									for (int l = 0; l < doubleHandList.getLength(); l++) {
										String name = doubleHandList.item(l).getTextContent();
										doubleHand.add(weapons.get(name));
										logger.info("double-hand: {}", name);
									}
									monster.setDoubleHand(doubleHand);
									monster.setDoubleHanded(true);
									break;
								default:
									break;
								}
							}
                        	
                        	NodeList aList = e.getElementsByTagName("armors");
                        	logger.info("Parsing armors.");
                        	for (int k = 0; k < aList.getLength(); k++) {
                        		switch(wList.item(k).getNodeName()) {
								case "head":
									NodeList headList = wList.item(k).getChildNodes();
									ArrayList<HeadArmor> headArmors = new ArrayList<HeadArmor>();
									for (int l = 0; l < headList.getLength(); l++) {
										String name = headList.item(l).getTextContent();
										headArmors.add((HeadArmor)armors.get(name));
										logger.info("head-armor: {}", name);
									}
									monster.setHeadArmors(headArmors);
									break;
								case "chest":
									NodeList chestList = wList.item(k).getChildNodes();
									ArrayList<ChestArmor> chestArmors = new ArrayList<ChestArmor>();
									for (int l = 0; l < chestList.getLength(); l++) {
										String name = chestList.item(l).getTextContent();
										chestArmors.add((ChestArmor)armors.get(name));
										logger.info("chest-armor: {}", name);
									}
									monster.setChestArmors(chestArmors);
									break;
								case "legs":
									NodeList legList = wList.item(k).getChildNodes();
									ArrayList<LegArmor> legArmors = new ArrayList<LegArmor>();
									for (int l = 0; l < legList.getLength(); l++) {
										String name = legList.item(l).getTextContent();
										legArmors.add((LegArmor)armors.get(name));
										logger.info("leg-armor: {}", name);
									}
									monster.setLegArmors(legArmors);
									break;
								case "hands":
									NodeList handList = wList.item(k).getChildNodes();
									ArrayList<HandArmor> handArmors = new ArrayList<HandArmor>();
									for (int l = 0; l < handList.getLength(); l++) {
										String name = handList.item(l).getTextContent();
										handArmors.add((HandArmor)armors.get(name));
										logger.info("hand-armor: {}", name);
									}
									monster.setHandArmors(handArmors);
									break;
								default:
									break;
								}
							}
                        	break;
                        	
                        default:
                        	break;
                        }
                    }
				}
                monsters.put(monster.getName(), monster);
                logger.info("New monster({}) parsed.", monster.getName());
            }
            
            logger.info("{} successfully parsed.", inputXml);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return monsters;
	}
}
