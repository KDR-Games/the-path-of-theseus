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
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import kdr.game.theseus.model.Armor;
import kdr.game.theseus.model.ChestArmor;
import kdr.game.theseus.model.HandArmor;
import kdr.game.theseus.model.HeadArmor;
import kdr.game.theseus.model.LegArmor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static kdr.game.theseus.view.Main.logger;

/**
 * ArmorsDAO 
 */
public class ArmorsDAO {
	private String inputXml;

	/**
	 * 
	 * @param inputXml - the file name of the input XML
	 */
	public ArmorsDAO(String inputXml) {
		this.inputXml = inputXml;
	}

	/**
	 * Reads {@link kdr.game.theseus.model.Armor}s from the input database.
	 * @return the list of armors
	 */
	public Map<String, Armor> getArmors() {
		Map<String, Armor> armors = new HashMap<String, Armor>();
		logger.info("Reading from database Armors.xml.");

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			dbFactory.setValidating(false);
			DocumentBuilder dbBuilder;
			dbBuilder = dbFactory.newDocumentBuilder();
			dbBuilder.setEntityResolver(new EntityManager());
			Document doc = dbBuilder.parse(this.getClass().getResourceAsStream(inputXml));

			logger.info("Head armors.");
			NodeList headArmors = doc.getElementsByTagName("head");
			for (int i = 0; i < headArmors.getLength(); i++) {
				Node node = headArmors.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;
					
					NodeList nList = e.getElementsByTagName("armor");
					for (int j = 0; j < nList.getLength(); j++) {
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element armor = (Element) nList.item(j);
							String name = armor.getElementsByTagName("name").item(0).getTextContent();
							String image = armor.getElementsByTagName("image").item(0).getTextContent();
							int defense = Integer.parseInt(armor.getElementsByTagName("defense").item(0).getTextContent());
							double speed = Double.parseDouble(armor.getElementsByTagName("speed").item(0).getTextContent());
							
							armors.put(name, new HeadArmor(name, image, speed, defense));
							
							logger.info("New armor added:\n" + name + " " + image + " " + speed + " " + defense);
						}
					}
				}
			}

			logger.info("Chest armors.");
			NodeList chestArmors = doc.getElementsByTagName("chest");
			for (int i = 0; i < chestArmors.getLength(); i++) {
				Node node = chestArmors.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;
					
					NodeList nList = e.getElementsByTagName("armor");
					for (int j = 0; j < nList.getLength(); j++) {
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element armor = (Element) nList.item(j);
							String name = armor.getElementsByTagName("name").item(0).getTextContent();
							String image = armor.getElementsByTagName("image").item(0).getTextContent();
							int defense = Integer.parseInt(armor.getElementsByTagName("defense").item(0).getTextContent());
							double speed = Double.parseDouble(armor.getElementsByTagName("speed").item(0).getTextContent());
							
							armors.put(name, new ChestArmor(name, image, speed, defense));
							
							logger.info("New armor added:\n" + name + " " + image + " " + speed + " " + defense);
						}
					}
				}
			}
			
			logger.info("Leg armors.");
			NodeList legArmors = doc.getElementsByTagName("leg");
			for (int i = 0; i < legArmors.getLength(); i++) {
				Node node = legArmors.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;
					
					NodeList nList = e.getElementsByTagName("armor");
					for (int j = 0; j < nList.getLength(); j++) {
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element armor = (Element) nList.item(j);
							String name = armor.getElementsByTagName("name").item(0).getTextContent();
							String image = armor.getElementsByTagName("image").item(0).getTextContent();
							int defense = Integer.parseInt(armor.getElementsByTagName("defense").item(0).getTextContent());
							double speed = Double.parseDouble(armor.getElementsByTagName("speed").item(0).getTextContent());
							
							armors.put(name, new LegArmor(name, image, speed, defense));

							logger.info("New armor added:\n" + name + " " + image + " " + speed + " " + defense);
						}
					}
				}
			}
			
			logger.info("Hand armors.");
			NodeList handArmors = doc.getElementsByTagName("hand");
			for (int i = 0; i < handArmors.getLength(); i++) {
				Node node = handArmors.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;
					
					NodeList nList = e.getElementsByTagName("armor");
					for (int j = 0; j < nList.getLength(); j++) {
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element armor = (Element) nList.item(j);
							String name = armor.getElementsByTagName("name").item(0).getTextContent();
							String image = armor.getElementsByTagName("image").item(0).getTextContent();
							int defense = Integer.parseInt(armor.getElementsByTagName("defense").item(0).getTextContent());
							double speed = Double.parseDouble(armor.getElementsByTagName("speed").item(0).getTextContent());
							
							armors.put(name, new HandArmor(name, image, speed, defense));

							logger.info("New armor added:\n" + name + " " + image + " " + speed + " " + defense);
						}
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("Database Armors.xml succsefully parsed.");
		return armors;
	}
}
