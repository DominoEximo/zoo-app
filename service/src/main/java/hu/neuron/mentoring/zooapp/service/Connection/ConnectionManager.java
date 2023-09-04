package hu.neuron.mentoring.zooapp.service.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
@Component
public class ConnectionManager {

    private final String DB_URL = "jdbc:mysql://localhost:3306/zoo";
    private final String USER = "root";
    private final String PASS = "Xbox11223344";

    private Connection myConn = null;
    @Autowired
    public ConnectionManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            myConn = DriverManager.getConnection(DB_URL, USER, PASS);
            myConn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getMyConn() {
        return myConn;
    }

    public void closeConnection() {
        try {
            myConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
