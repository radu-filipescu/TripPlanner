package com.company;

import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.sql.*;
import java.util.Properties;

public class JDBCgeneric {
    Connection connection;

    // this is the generic class that will be inherited to
    // create services for each of the objects used
    public JDBCgeneric() {
        Properties connectionProps = new Properties();

        // here we add mySql instance credentials to an object properties
        connectionProps.put("user", "tripplanner_user");
        connectionProps.put("password", "developing");

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tripplanner", connectionProps);
        } catch (SQLException e) {
            // here we print the error to the user
            System.out.println(e.getSQLState());
        }
    }

    // wrapper for executing SQL queries which return stuff
    public ResultSet executeSQLquery(String query) {
        ResultSet results = null;

        try {
            Statement statement = connection.createStatement();
            results = statement.executeQuery(query);
        }
        catch (SQLException e) {
            System.out.println(e.getSQLState());
        }

        return results;
    }

    // wrapper for executing SQL data queries (update/delete)
    public void executeSQLupdate(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }
}
