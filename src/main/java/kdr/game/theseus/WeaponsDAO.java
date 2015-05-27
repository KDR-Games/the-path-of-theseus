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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import kdr.game.theseus.model.DamageType;
import kdr.game.theseus.model.Monster;
import kdr.game.theseus.model.OneHanded;
import kdr.game.theseus.model.Weapon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * WeaponsDAO 
 */
public class WeaponsDAO {
private String inputXml;
	
	/**
	 * 
	 */
	public WeaponsDAO(String inputXml) {
		this.inputXml = inputXml;
	}
	
	public Map<String, OneHanded> getOneHandedWeapons() {
		Map<String, OneHanded> weapons = new HashMap<String, OneHanded>();
		
		try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder;
            dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(this.getClass().getResourceAsStream(inputXml));
            
            NodeList mainElementList = doc.getElementsByTagName("one-handed");
            for (int i = 0; i < mainElementList.getLength(); i++) {
                if (mainElementList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) mainElementList.item(i);
                    
                    NodeList nList = e.getElementsByTagName("weapon");
                    
                    for (int j = 0; j < nList.getLength(); j++) {
                    	NodeList weapon = nList.item(j).getChildNodes();

						String weaponName = null;
						String imageName = null;
						DamageType damageType = null;
						int damage = 0;
						double speed = 0.0;
						
                    	for (int k = 0; k < weapon.getLength(); k++) {
							Node node = weapon.item(k);
							
							if(node.getNodeName().equals("name")) {
								weaponName = node.getTextContent();
							} else if(node.getNodeName().equals("image")) {
								weaponName = node.getTextContent();
							} else if(node.getNodeName().equals("damage-type")) {
								switch (node.getTextContent()) {
								case "Slashing":
									damageType = DamageType.Slashing;
									break;
								case "Piercing":
									damageType = DamageType.Piercing;
									break;
								case "Blunt":
									damageType = DamageType.Blunt;
									break;

								default:
									break;
								}
								
							} else if(node.getNodeName().equals("damage")) {
								damage = Integer.parseInt(node.getTextContent());
							} else if(node.getNodeName().equals("speed")) {
								speed = Integer.parseInt(node.getTextContent());
							}
						}
                    	
                    	
						
						
					}                                        
                }
            }
            
            System.out.println(doc.getDocumentElement().getNodeName());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return weapons;
	}
	
	public ArrayList<Weapon> getWeapons() {
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		
		try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder;
            dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(this.getClass().getResourceAsStream(inputXml));
            
            NodeList nList = doc.getElementsByTagName("Monster");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    Weapon weapon = null;
                    
                    if(e.getNodeName() == "one-handed") {
                    	
                    } else if(e.getNodeName() == "two-handed") {
                    	
                    } else if(e.getNodeName() == "shield") {
                    	
                    }
                    
                    weapons.add(weapon);
                }
            }
            
            System.out.println(doc.getDocumentElement().getNodeName());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return weapons;
	}
}
