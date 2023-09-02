package hu.neuron.mentoring.zooapp.service.Connection;

import java.sql.*;

public class ConnectionManager {

    private final String DB_URL = "jdbc:mysql://localhost:3306/zoo";
    private final String USER = "root";
    private final String PASS = "Xbox11223344";

    private Connection myConn = null;

    public ConnectionManager(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            myConn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getMyConn() {
        return myConn;
    }

    public void closeConnection(){
        try {
            myConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
