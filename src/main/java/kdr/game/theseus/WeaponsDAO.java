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

import static kdr.game.theseus.view.Main.logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import kdr.game.theseus.model.DamageType;
import kdr.game.theseus.model.OneHanded;
import kdr.game.theseus.model.Shield;
import kdr.game.theseus.model.ShieldType;
import kdr.game.theseus.model.TwoHanded;
import kdr.game.theseus.model.Weapon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A parser for {@link kdr.game.theseus.model.Weapon} from
 * an XML database.
 */
public class WeaponsDAO {
private String inputXml;
	
	/**
	 * Creates a new instance of this parser, with the specified database as parameter.
	 * @param inputXml - the file name of the input XML
	 */
	public WeaponsDAO(String inputXml) {
		this.inputXml = inputXml;
	}
	
	/**
	 * Reads {@link kdr.game.theseus.model.Weapon}s from the input database.
	 * @return the list of weapons
	 */
	public Map<String, Weapon> getWeapons() {
		Map<String, Weapon> weapons = new HashMap<String, Weapon>();
		
		weapons.putAll(getOneHandedWeapons());
		weapons.putAll(getTwoHandedWeapons());
		weapons.putAll(getShields());
		
		return weapons;
	}
	
	/**
	 * Reads {@link kdr.game.theseus.model.OneHanded}
	 * weapons from the input database.
	 * @return the list of one-handed weapons
	 */
	public Map<String, OneHanded> getOneHandedWeapons() {
		Map<String, OneHanded> weapons = new HashMap<String, OneHanded>();
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			dbFactory.setValidating(false);
			DocumentBuilder dbBuilder;
			dbBuilder = dbFactory.newDocumentBuilder();
			dbBuilder.setEntityResolver(new EntityManager());
			Document doc = dbBuilder.parse(this.getClass().getResourceAsStream(inputXml));

			logger.info("One handed weapons.");
            
            NodeList oneHanded = doc.getElementsByTagName("one-handed");
            for (int i = 0; i < oneHanded.getLength(); i++) {
                if (oneHanded.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element weapon = (Element)oneHanded.item(i);

					String name = weapon.getElementsByTagName("name").item(0).getTextContent();
					String imageSrc = Constants.WeaponsLocation + weapon.getElementsByTagName("image").item(0).getTextContent();
					logger.info(imageSrc);
					String type = weapon.getElementsByTagName("damage-type").item(0).getTextContent();
					DamageType damageType = null;
					switch (type) {
					case "slashing":
						damageType = DamageType.Slashing;
						break;
					case "piercing":
						damageType = DamageType.Piercing;
						break;
					case "blunt":
						damageType = DamageType.Blunt;
						break;

					default:
						break;
					}
					int damage = Integer.parseInt(weapon.getElementsByTagName("damage").item(0).getTextContent());
					double speed = Double.parseDouble(weapon.getElementsByTagName("speed").item(0).getTextContent());
                	Image image = new Image(this.getClass().getResourceAsStream(imageSrc));
					
                	weapons.put(name, new OneHanded(name, image, damageType, damage, speed));
                	logger.info("New weapon added:\n" + 
                	name + " " + image + " " + damageType.toString() + " "+ speed + " " + damage);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		logger.info("One handed weapons succesfully parsed from database Weapons.xml.");
		return weapons;
	}
	
	/**
	 * Reads {@link kdr.game.theseus.model.TwoHanded}
	 * weapons from the input database.
	 * @return the list of two-handed weapons
	 */
	public Map<String, TwoHanded> getTwoHandedWeapons() {
		Map<String, TwoHanded> weapons = new HashMap<String, TwoHanded>();
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			dbFactory.setValidating(false);
			DocumentBuilder dbBuilder;
			dbBuilder = dbFactory.newDocumentBuilder();
			dbBuilder.setEntityResolver(new EntityManager());
			Document doc = dbBuilder.parse(this.getClass().getResourceAsStream(inputXml));

			logger.info("Two handed weapons.");
            
			NodeList twoHanded = doc.getElementsByTagName("two-handed");
            for (int i = 0; i < twoHanded.getLength(); i++) {
                if (twoHanded.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element weapon = (Element)twoHanded.item(i);

					String name = weapon.getElementsByTagName("name").item(0).getTextContent();
					String imageSrc = Constants.WeaponsLocation + weapon.getElementsByTagName("image").item(0).getTextContent();
					logger.info(imageSrc);
					String type = weapon.getElementsByTagName("damage-type").item(0).getTextContent();
					DamageType damageType = null;
					switch (type) {
					case "slashing":
						damageType = DamageType.Slashing;
						break;
					case "piercing":
						damageType = DamageType.Piercing;
						break;
					case "blunt":
						damageType = DamageType.Blunt;
						break;

					default:
						break;
					}
					int damage = Integer.parseInt(weapon.getElementsByTagName("damage").item(0).getTextContent());
					double speed = Double.parseDouble(weapon.getElementsByTagName("speed").item(0).getTextContent());
					Image image = new Image(this.getClass().getResourceAsStream(imageSrc));
					
                	weapons.put(name, new TwoHanded(name, image, damageType, damage, speed));
                	logger.info("New weapon added:\n" + 
                	name + " " + image + " " + damageType.toString() + " "+ speed + " " + damage);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		logger.info("Two handed weapons succesfully parsed from database Weapons.xml.");
		return weapons;
	}

	/**
	 * Reads {@link kdr.game.theseus.model.Shield}
	 * weapons from the input database.
	 * @return the list of shields
	 */
	public Map<String, Shield> getShields() {
		Map<String, Shield> shields = new HashMap<String, Shield>();
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			dbFactory.setValidating(false);
			DocumentBuilder dbBuilder;
			dbBuilder = dbFactory.newDocumentBuilder();
			dbBuilder.setEntityResolver(new EntityManager());
			Document doc = dbBuilder.parse(this.getClass().getResourceAsStream(inputXml));

			logger.info("Shields.");
            
			NodeList shieldList = doc.getElementsByTagName("shields");
            for (int i = 0; i < shieldList.getLength(); i++) {
                if (shieldList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element shield = (Element)shieldList.item(i);

					String name = shield.getElementsByTagName("name").item(0).getTextContent();
					String imageSrc = Constants.ShieldsLocation + shield.getElementsByTagName("image").item(0).getTextContent();
					logger.info(imageSrc);
					String type = shield.getElementsByTagName("defense-type").item(0).getTextContent();
					ShieldType defenseType = null;
					switch (type) {
					case "small-shield":
						defenseType = ShieldType.Small;
						break;
					case "medium-shield":
						defenseType = ShieldType.Medium;
						break;
					case "tower-shield":
						defenseType = ShieldType.Tower;
						break;

					default:
						break;
					}
					int defense = Integer.parseInt(shield.getElementsByTagName("defense").item(0).getTextContent());
					double speed = Double.parseDouble(shield.getElementsByTagName("speed").item(0).getTextContent());
					Image image = new Image(this.getClass().getResourceAsStream(imageSrc));
					
                	shields.put(name, new Shield(name, image, defenseType, defense, speed));
                	logger.info("New shield added:\n" + 
                	name + " " + image + " " + defenseType.toString() + " "+ speed + " " + defense);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		logger.info("Shields succesfully parsed from database Weapons.xml.");
		return shields;
	}
	
	
}
