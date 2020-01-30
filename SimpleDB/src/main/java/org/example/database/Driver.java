package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Driver {
    private String path = "jdbc:mysql://localhost:3306/library?useUnicode=true&serverTimezone=UTC";
    private Connection connection;

    public void setConnection(List<String> connectionDetails){
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection( path, connectionDetails.get( 0 ), connectionDetails.get( 1 ) );
            System.out.println("Successful connection!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setConnection(String login, String password) throws SQLException {
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection( path, login, password );
            System.out.println("Successful connection!");
        } catch ( ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
