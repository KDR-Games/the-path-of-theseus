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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import kdr.game.theseus.model.Monster;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	public ArrayList<Monster> getMonsters() {
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		
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
                    
                    Monster monster = new Monster(e.getElementsByTagName("name").item(0).getTextContent());
                    
                    System.out.println("id: " + e.getAttribute("id"));
                    System.out.println("name: " + e.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("age: " + e.getElementsByTagName("age").item(0).getTextContent());
                    System.out.println("gender: " + e.getElementsByTagName("gender").item(0).getTextContent());
                    System.out.println("role: " + e.getElementsByTagName("role").item(0).getTextContent());
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
		
		return monsters;
	}
}
