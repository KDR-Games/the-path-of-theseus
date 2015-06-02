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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.OracleConnection;

import static kdr.game.theseus.view.Main.logger;

/**
 * The <code>ConnectionFactory</code> class provides access to the game
 * database, which can contain saved games for registered users.
 * 
 * @author gergo
 *
 */
public class ConnectionFactory {

    /**
     * A static instance of the {@code ConnectionFactory} class.
     */
    private static ConnectionFactory factory = new ConnectionFactory();

    /**
     * Registers the Oracle driver.
     */
    private ConnectionFactory() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            logger.info("Oracle JDBC driver successfully registered.");
        } catch (SQLException e) {
            logger.error("Couldn't register JDBC driver.");
            e.printStackTrace();
        }
    }

    /**
     * Reads a property file containing the user name and password for the
     * Oracle server, and sets up the connection.
     * 
     * @return an {@code OracleConnection} object representing the connection to
     *         the database
     * @throws IOException
     *             when the property file containing the user name and password
     *             is missing
     */
    private OracleConnection createConnection() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream(
                "/oracleUser.properties");

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException();
        }

        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String passwd = prop.getProperty("passwd");

        Connection connection = null;
        OracleConnection oraConn = null;
        try {
            connection = DriverManager.getConnection(url, user, passwd);
            oraConn = connection.unwrap(OracleConnection.class);
            logger.info("Connection to the database created.");
        } catch (SQLException e) {
            logger.error(String.format(
                    "Failed to create connection to the database: ",
                    e.getMessage()));
            e.printStackTrace();
        }
        return oraConn;
    }

    /**
     * Returns a new connection of type <code>OracleConnection</code>. All
     * communication with the game database is performed through connections
     * returned by this method.
     * <p>
     * Access to the Oracle server can be gained with a user name and a
     * password. A separate property file contains these. If the file cannot be
     * found, an exception will be thrown.
     * 
     * @return an <code>OracleConnection</code> object providing the
     *         communication channel with the game server
     * @throws IOException
     *             when the file containing the database user name and password
     *             is missing
     */
    public static OracleConnection getConnection() throws IOException {
        return factory.createConnection();
    }
}
