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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kdr.game.theseus.model.HighScore;
import static kdr.game.theseus.view.Main.logger;

/**
 * HighScoresDAO 
 */
public class HighScoresDAO {
	
	public ArrayList<HighScore> getHighScores() {
		ArrayList<HighScore> highScores = new ArrayList<HighScore>();
		
		try (Connection connection = ConnectionFactory.getConnection()) {
			Statement st = connection.createStatement();
			ResultSet rset = st.executeQuery("select * from characters");

			while (rset.next()) {
				String name = rset.getString("name");
				int kills = rset.getInt("kills");
				int distance = rset.getInt("distance");
				
				highScores.add(new HighScore(name, kills, distance));
			}
		} catch (SQLException e) {
			logger.warn("Couldn't connect to the database.");
			e.printStackTrace();
		} catch (IOException e1) {
			logger.warn("Couldn't find the database properties file.");
			e1.printStackTrace();
		}
		
		return highScores;
	}
	
	public ArrayList<String> getCharacterNames() {
		ArrayList<String> characterNames = new ArrayList<String>();
		
		try (Connection connection = ConnectionFactory.getConnection()) {
			Statement st = connection.createStatement();
			ResultSet rset = st.executeQuery("select * from characters");

			while (rset.next()) {
				String name = rset.getString("name");
				
				characterNames.add(name);
			}
		} catch (SQLException e) {
			logger.warn("Couldn't connect to the database.");
			e.printStackTrace();
		} catch (IOException e1) {
			logger.warn("Couldn't find the database properties file.");
			logger.warn("Starting in offline mode.");
		}
		
		return characterNames;
	}
	
	public void addNewHighScore(HighScore score) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			Statement st = connection.createStatement();

			boolean ok = !this.getCharacterNames().contains(score.getPlayerName());
			if(ok) {
				st.executeUpdate(String.format(
						"insert into characters values('%s', '%d', '%d')", 
						score.getPlayerName(), score.getKills(), score.getDistanceTravelled()));
				logger.info("Player registered into the database.");
			} else {
				logger.info("Player already registered into the database. Further progress is not saved.");
			}
		} catch (SQLException e) {
			logger.warn("Couldn't connect to the database.");
			e.printStackTrace();
		} catch (IOException e1) {
			logger.warn("Couldn't find the database properties file.");
			logger.warn("Starting in offline mode.");
		}
	}
}
