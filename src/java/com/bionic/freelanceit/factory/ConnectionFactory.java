
package com.bionic.freelanceit.factory;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qz
 */
public class ConnectionFactory {
    String driverClassName = "org.gjt.mm.mysql.Driver";
//    String driverClassName = "com.mysql.jdbc.Driver";
    String connection = "jdbc:mysql://localhost:3306/projectX";
    String dbUser = "root";
    String dbPwd = "root";

    private static ConnectionFactory connectionFactory = null;
    
    private ConnectionFactory() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connectionUrl = null;
        connectionUrl = DriverManager.getConnection(connection, dbUser, dbPwd);
        return connectionUrl;
    }
    
    public static ConnectionFactory getInstance() {
        if(connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
    
}
